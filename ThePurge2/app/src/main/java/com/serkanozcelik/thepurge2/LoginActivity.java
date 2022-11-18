package com.serkanozcelik.thepurge2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView btn;
    EditText editTextEmail,editUserPassword;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        btn=(TextView) findViewById(R.id.textViewKayıtOl);
        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editUserPassword=(EditText)findViewById(R.id.editUserPassword);
        btnLogin=(Button)findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              kontrolBilgiler();
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }
    private void kontrolBilgiler() {


        String email=editTextEmail.getText().toString();
        String sifre=editUserPassword.getText().toString();




         if (email.isEmpty()|| !email.contains("@")){
            hataGöster(editTextEmail,"Email geçerli değil!! ");
        } else if (sifre.isEmpty()|| sifre.length()<7) {
            hataGöster(editUserPassword,"Şifre 7 karakterli olmalı");
        }
        else{
            Toast.makeText(this,"Kayıt başarılı",Toast.LENGTH_SHORT).show();
        }
    }

    private void hataGöster(EditText editUserEditText,String s) {


        editUserEditText.setError(s);
        editUserEditText.requestFocus();

    }
}