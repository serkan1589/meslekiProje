package com.serkanozcelik.thepurge4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    TextView btn;
    private EditText editUserEmail,editUserName,editUserPasswordRegister,editUserPasswordKayitConfirm;
    Button btnregister;
    FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn=findViewById(R.id.textViewHesapVarsa);
        editUserName=findViewById(R.id.editUserName);
        editUserEmail=findViewById(R.id.editUserEmail);
        editUserPasswordRegister=findViewById(R.id.editUserPasswordRegister);
        editUserPasswordKayitConfirm=findViewById(R.id.editUserPasswordKayıtConfirm);

        mAuth=FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(RegisterActivity.this);

        btnregister=findViewById(R.id.buttonRegister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkInformation();
            }
        });





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });





            }
    private void checkInformation() {

        String userName=editUserName.getText().toString();
        String userEmail=editUserEmail.getText().toString();
        String userPassword=editUserPasswordRegister.getText().toString();
        String userPasswordConfirm=editUserPasswordKayitConfirm.getText().toString();


        if (userName.isEmpty()|| userName.length()<7){
            warningShow(editUserName,"Kullanıcı adı geçersiz!!");
        }else if (userEmail.isEmpty()|| !userEmail.contains("@")){
            warningShow(editUserName,"Email geçerli değil!! ");
        } else if (userPassword.isEmpty()|| userPassword.length()<7) {
            warningShow(editUserPasswordKayitConfirm,"Şifre 7 karakterli olmalı");
        }else if (userPasswordConfirm.isEmpty()|| !userPasswordConfirm.equals(userPassword)){
            warningShow(editUserPasswordKayitConfirm,"Şifre Eşleşmedi!");
        }else{
            mLoadingBar.setTitle("Kayıt");
            mLoadingBar.setMessage("Kayıt oluşturuluyor");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();


            mAuth.createUserWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this,"Kayıt başarılı",Toast.LENGTH_SHORT);

                        Intent intent =new Intent(RegisterActivity.this,LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    }else{
                        Toast.makeText(RegisterActivity.this,task.getException().toString(),Toast.LENGTH_SHORT);
                        mLoadingBar.dismiss();

                    }

                }
            });
        }
    }

    private void warningShow(EditText userEditText,String s) {
        userEditText.setError(s);
        userEditText.requestFocus();
    }
}