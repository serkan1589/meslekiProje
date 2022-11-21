package com.serkanozcelik.thepurge5;

import static com.serkanozcelik.thepurge5.R.id.textViewLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private TextView btn;
   private EditText editUserEmail,editUserName,editUserPassword,editUserConfirmPassword;

   Button buttonRegister;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn=findViewById(R.id.textViewLogin);
        editUserName=findViewById(R.id.editUserName);
        editUserEmail=findViewById(R.id.editUserEmail);
        editUserPassword=findViewById(R.id.editUserPassword);
        editUserConfirmPassword=findViewById(R.id.editUserConfirmPassword);
        buttonRegister=findViewById(R.id.buttonRegister);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               checkInformation();
            }
        });



    }



    private void checkInformation() {

        String userName=editUserName.getText().toString();
        String userEmail=editUserEmail.getText().toString();
        String userPassword=editUserPassword.getText().toString();
        String userConfirmPassword=editUserConfirmPassword.getText().toString();


        if (userName.isEmpty()|| userName.length()<7){
            warningShow(editUserName,"Kullanıcı adı geçerli değil!!");
        }else if (userEmail.isEmpty()|| !userEmail.contains("@")){
            warningShow(editUserEmail,"Email geçerli değil!! ");
        } else if (userPassword.isEmpty()|| userPassword.length()<7) {
            warningShow(editUserPassword,"Şifre 7 karakterli olmalı");
        }else if (userConfirmPassword.isEmpty()|| !userConfirmPassword.equals(userPassword)){
            warningShow(editUserConfirmPassword,"Şifre Eşleşmedi!");
        }else{



        }

    }
    private void warningShow(EditText userEdit,String mesaj) {
        userEdit.setError(mesaj);
        userEdit.requestFocus();
    }

}