package pizware.evaluapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import pizware.evaluapp.R;

public class Acceso extends AppCompatActivity {
    private EditText txtUsuariio;
    private EditText txtContra;
    private Button btnAcceso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);

        showAlertForCreatingGroup(null,null);

        txtUsuariio=(EditText) findViewById(R.id.txtUsuario);
        txtContra=(EditText) findViewById(R.id.txtContra);
        btnAcceso=(Button) findViewById(R.id.btnAcceso);

        btnAcceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(Acceso.this,Docente.class);
                inte.putExtra("hola","mundo");
                startActivity(inte);
            }
        });
    }

    private void showAlertForCreatingGroup(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(title !=null) builder.setTitle(title);
        if(message !=null) builder.setMessage(message);
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_user,null);
        builder.setView(viewInflated);

        final EditText txtUser = (EditText) viewInflated.findViewById(R.id.txtUser);
        final EditText txtPassword = (EditText) viewInflated.findViewById(R.id.txtPassword);
        final EditText txtConfPassword = (EditText) viewInflated.findViewById(R.id.txtConfPassword);
        final EditText txtEmail = (EditText) viewInflated.findViewById(R.id.txtEmail);
        final CheckBox chbAdmin = (CheckBox) viewInflated.findViewById(R.id.chbAdmin);
        final EditText txtHint = (EditText) viewInflated.findViewById(R.id.txtHint);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String user= txtUser.getText().toString().trim();
                String password= txtPassword.getText().toString();
                String confPassword= txtConfPassword.getText().toString();
                String email= txtEmail.getText().toString().trim();
                boolean admin= chbAdmin.isChecked();
                String hint= txtHint.getText().toString();

                if(user.length()>0||password.length()>0||confPassword.length()>0||email.length()>0||hint.length()>0)
                    createNewUser(user,password,confPassword,email,admin,hint);
                else
                    Toast.makeText(Acceso.this, "Please Writhe a Group name", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    private void createNewUser(String user, String password, String confPassword, String email, boolean admin, String hint) {
        Toast.makeText(this,"User created",Toast.LENGTH_SHORT).show();
    }
}
