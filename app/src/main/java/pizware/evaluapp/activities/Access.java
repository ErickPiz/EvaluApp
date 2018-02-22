package pizware.evaluapp.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import pizware.evaluapp.Models.User;
import pizware.evaluapp.R;
import pizware.evaluapp.adapters.UserAdapter;


public class Access extends AppCompatActivity implements RealmChangeListener<RealmResults<User>> {

    private EditText txtUser;
    private EditText txtPassword;
    private Button btnAccess;
    private Button btnForgetSomething;
    private FloatingActionButton fabAdd;
    private Realm realm;
    private UserAdapter userAdapter;
    private RealmResults<User> users;
    private ListView lvUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        realm = Realm.getDefaultInstance();
        users = realm.where(User.class).findAll();
        users.addChangeListener(this);


        userAdapter = new UserAdapter(this, users, R.layout.list_view_users);
        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnAccess = (Button) findViewById(R.id.btnAccess);
        btnForgetSomething = (Button) findViewById(R.id.btnForgetSomething);
        lvUsers = (ListView) findViewById(R.id.lvUsers);
        lvUsers.setAdapter(userAdapter);
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);

        fabAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                fabAddAction();
            }
        });
        btnForgetSomething.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                btnForgotSomethingAction();
            }
        });
        btnAccess.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAccessAction();
            }
        });
    }

    private void btnForgotSomethingAction() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);//an instancie of AlertDialog is created
        final View viewInflated = LayoutInflater.from(this).inflate(R.layout.forgot_something_layout, null);// a view is created, from this context inflate dialog_create_user
        builder.setView(viewInflated);//add de view to AlertDialog

        //the link to the components is generated
        final EditText txtUserName = (EditText) viewInflated.findViewById(R.id.txtUserName);
        final TextView lblHint= (TextView) viewInflated.findViewById(R.id.lblHint);
        final Button btnSend = (Button) viewInflated.findViewById(R.id.btnSend);
        final Button btnStillNotRemember = (Button) viewInflated.findViewById(R.id.btnStillNotRemember);

        btnSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtUserName.getHint().toString().equals("User Name")) {
                    if ( userExist(txtUserName.getText().toString())) {
                       lblHint.setText(realm.where(User.class).equalTo("user", txtUserName.getText().toString().toUpperCase()).findFirst().getHint());
                    }else{
                        toast("user doesn't exist");
                    }
                } else {
                    if (emailExist(txtUserName.getText().toString())) {
                        toast("Email sended");
                    } else {
                        toast("Email desn't exist");
                    }
                }
            }
        });
        btnStillNotRemember.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                txtUserName.setText("");
                txtUserName.setHint("Email");
                btnSend.setText("Send me an Email");
            }
        });
        builder.create().show();//Create and show the AlertDialog
    }

    private boolean emailExist(String email) {
        return realm.where(User.class).equalTo("email",email.trim()).findAll().size()>0;
    }

    private void fabAddAction() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);//an instancie of AlertDialog is created
        final View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_user, null);// a view is created, from this context inflate dialog_create_user
        builder.setView(viewInflated);//add de view to AlertDialog

        //the link to the components is generated
        final EditText txtUser = (EditText) viewInflated.findViewById(R.id.txtUser);
        final EditText txtPassword = (EditText) viewInflated.findViewById(R.id.txtPassword);
        final EditText txtConfPassword = (EditText) viewInflated.findViewById(R.id.txtConfPassword);
        final EditText txtEmail = (EditText) viewInflated.findViewById(R.id.txtEmail);
        final CheckBox chbAdmin = (CheckBox) viewInflated.findViewById(R.id.chbAdmin);
        final EditText txtHint = (EditText) viewInflated.findViewById(R.id.txtHint);
        final Button btnCreate = (Button) viewInflated.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCreateAction(txtUser,txtPassword,txtConfPassword,txtEmail,chbAdmin,txtHint);

            }
        });
        builder.create().show();//Create and show the AlertDialog
    }

    private void createNewUser(final String user, final String password, final String email, final boolean admin, final String hint) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User newUser = realm.createObject(User.class, user.toUpperCase());
                newUser.setPassword(password);
                newUser.setEmail(email);
                newUser.setAdmin(admin);
                newUser.setHint(hint);
            }
        });
        Toast.makeText(this, "User " + user + " created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChange(RealmResults<User> users) {
        userAdapter.notifyDataSetChanged();
    }

    private void btnAccessAction(){

        if (isEmptyAccessFields()) {
            if (userExist(txtUser.getText().toString())) {
                if (isValidPassword()) {
                    Intent intent = new Intent(Access.this, Main.class);
                    intent.putExtra("user", txtUser.getText().toString().trim().toUpperCase());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    toast("incorrect Password from "+txtUser.getText().toString());
                }
            } else {
                toast("User doesn't exist");
            }
        } else {
            toast("Fields empty");
        }

    }

    private void btnCreateAction(EditText txtUser, EditText txtPassword, EditText txtConfPassword, EditText txtEmail, CheckBox chbAdmin, EditText txtHint) {

        //the text of the components is added to variables
        String user = txtUser.getText().toString().trim().toUpperCase();
        String password = txtPassword.getText().toString();
        String confPassword = txtConfPassword.getText().toString();
        String email = txtEmail.getText().toString().trim();
        boolean admin = chbAdmin.isChecked();
        String hint = txtHint.getText().toString();

        //if the number of characters is more than 0 then check if the password is ok. If Any afirmation is true then send an error message
        if (user.length() > 0 || password.length() > 0 || confPassword.length() > 0 || email.length() > 0 || hint.length() > 0) {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && !emailExist(email)) {
                if (password.equals(confPassword)) {
                    if (!userExist(user)) {
                        createNewUser(user, password, email, admin, hint);
                    } else {
                        toast("User "+user+" already exist");
                        txtUser.requestFocus();
                    }
                } else {
                    toast("Check password");
                }
            } else {
                toast("Email invalid");
            }
        } else {
            toast("Any field have been empty");
        }
    }

    private boolean isEmptyAccessFields(){
        return txtUser.getText().toString().length()>0 && txtPassword.getText().toString().length()>0;
    }

    public boolean userExist(String user){
        return realm.where(User.class).equalTo("user",user.toUpperCase()).findAll().size()>0;
    }

    public boolean isValidPassword(){
        return realm.where(User.class).equalTo("user", txtUser.getText().toString().toUpperCase()).findFirst().getPassword().equals(txtPassword.getText().toString());
    }

    public void toast(String text){
        Toast.makeText(Access.this, text, Toast.LENGTH_SHORT).show();
    }

}