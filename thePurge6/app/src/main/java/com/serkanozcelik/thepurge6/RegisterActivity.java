package com.serkanozcelik.thepurge6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private TextView btn;
    private EditText editUserEmail,editUserName,editUserPassword,editUserConfirmPassword;

    Button buttonRegister;

    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;



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

        mAuth=FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(RegisterActivity.this);



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
            mLoadingBar.setTitle("Kayıt");
            mLoadingBar.setMessage("Kayıt oluşturuluyor");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this,"Succesfully registration",Toast.LENGTH_SHORT);
                        Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);


                    }
                    else{
                        Toast.makeText(RegisterActivity.this,task.getException().toString(),Toast.LENGTH_SHORT);
                       mLoadingBar.dismiss();
                    }
                }
            });
        }


    }
    private void warningShow(EditText userEdit,String mesaj) {
        userEdit.setError(mesaj);
        userEdit.requestFocus();
    }

}