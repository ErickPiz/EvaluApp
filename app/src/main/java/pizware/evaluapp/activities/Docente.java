package pizware.evaluapp.activities;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import pizware.evaluapp.R;

public class Docente extends AppCompatActivity {

    ListView lvGrupos;
    FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showAlertForCreatingGroup("hola","mundo");

        lvGrupos=(ListView) findViewById(R.id.lvGrupos);
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);

        Bundle bundle=getIntent().getExtras();
        if(bundle != null){
            String str = bundle.getString("hola");
            Toast.makeText(this,str, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
        }
    }
    private void showAlertForCreatingGroup(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(title !=null) builder.setTitle(title);
        if(message !=null) builder.setMessage(message);
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_user,null);
        builder.setView(viewInflated);

        final EditText input = (EditText) viewInflated.findViewById(R.id.txtUser);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String groupName= input.getText().toString().trim();
                if(groupName.length()>0)
                    createNewGroup(groupName);
                else
                    Toast.makeText(Docente.this, "Please Writhe a Group name", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    private void createNewGroup(String groupName){

    }
}
