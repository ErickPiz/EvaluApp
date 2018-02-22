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

public class Main extends AppCompatActivity {

    private ListView lvGroups;
    private FloatingActionButton fabAdd;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente);

        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);

        if(getIntent().getExtras() != null){
            userName =getIntent().getExtras().getString("user");
            Toast.makeText(this,userName, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
        }
    }
}
