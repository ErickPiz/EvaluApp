package pizware.evaluapp.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import pizware.evaluapp.R;

public class ImplicitActivity extends AppCompatActivity {
    private EditText txtPhone;
    private EditText txtWeb;
    private ImageButton ibtnPhone;
    private ImageButton ibtnWeb;
    private ImageButton ibtnCamera;

    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);

        txtPhone = (EditText) findViewById(R.id.txtPhone);
        txtWeb = (EditText) findViewById(R.id.txtWeb);
        ibtnPhone = (ImageButton) findViewById(R.id.ibtnPhone);
        ibtnWeb = (ImageButton) findViewById(R.id.ibtnWeb);
        ibtnCamera = (ImageButton) findViewById(R.id.ibtnCamera);

        ibtnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtPhone.getText().toString().isEmpty()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 100);
                    } else {
                        olderVersions();
                    }
                }else{
                    Toast.makeText(ImplicitActivity.this, "Phone empty", Toast.LENGTH_SHORT).show();
                }
            }

            private void olderVersions() {
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + txtPhone.getText().toString()));
                if (checkPermission(Manifest.permission.CALL_PHONE)) {
                    startActivity(callIntent);
                } else {
                    Toast.makeText(ImplicitActivity.this, "Sin Permiso", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ibtnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtWeb.getText().toString().isEmpty()){
                    Intent i =new Intent(Intent.ACTION_VIEW,Uri.parse("http://"+txtWeb.getText().toString()));
                    Intent mail = new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:erickpiizz@gmail.com"));
                    startActivity(mail);
                }else{
                    Toast.makeText(ImplicitActivity.this, "Web empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PHONE_CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals(Manifest.permission.CALL_PHONE)) {//Compara que el permiso enviado se encentra en el manifest
                    if(result == PackageManager.PERMISSION_GRANTED){
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + txtPhone.getText().toString()));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                            return;//comprueba si se acepto el permiso
                        startActivity(intentCall);
                    }else{
                        Toast.makeText(this, "Permission declined", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }

    }

    private boolean checkPermission(String permission){
        return this.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }
}
