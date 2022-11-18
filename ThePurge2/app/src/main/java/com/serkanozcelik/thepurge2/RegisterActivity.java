package com.serkanozcelik.thepurge2;

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
    TextView btn;

    private EditText editUserName,editTextEmail,editTextPassword,editTextConfirmPassword;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;
    Button btnKayıtOl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn=findViewById(R.id.textViewHesabınVarmı);
        editUserName=findViewById(R.id.editUserPassword);
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextConfirmPassword=findViewById(R.id.editTextConfirmPassword);
        btnKayıtOl=findViewById(R.id.btnRegister);

        mAuth=(FirebaseAuth)FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(RegisterActivity.this);

        btnKayıtOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kontrolBilgiler();
            }
        });


        kontrolBilgiler();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }

    private void kontrolBilgiler() {

        String kullanıcıAdı=editUserName.getText().toString();
        String email=editTextEmail.getText().toString();
        String sifre=editTextPassword.getText().toString();
        String sifreTekrar=editTextConfirmPassword.getText().toString();


        if (kullanıcıAdı.isEmpty()|| kullanıcıAdı.length()<7){
            hataGöster(editUserName,"Kullanıcı adı geçerli değil!!");
        }else if (email.isEmpty()|| !email.contains("@")){
            hataGöster(editTextEmail,"Email geçerli değil!! ");
        } else if (sifre.isEmpty()|| sifre.length()<7) {
            hataGöster(editTextPassword,"Şifre 7 karakterli olmalı");
        }else if (sifreTekrar.isEmpty()|| !sifreTekrar.equals(sifre)){
            hataGöster(editTextConfirmPassword,"Şifre Eşleşmedi!");
        }else{
           mLoadingBar.setTitle("Kayıt");
           mLoadingBar.setMessage("Kayıt oluşturuluyor");
           mLoadingBar.setCanceledOnTouchOutside(true);
           mLoadingBar.show();


           mAuth.createUserWithEmailAndPassword(kullanıcıAdı,sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if (task.isSuccessful()){
                       Toast.makeText(RegisterActivity.this,"Kayıt başarılı",Toast.LENGTH_SHORT);

                       Intent intent =new Intent(RegisterActivity.this,MainActivity.class);
                       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                       startActivity(intent);

                   }else{
                       Toast.makeText(RegisterActivity.this,task.getException().toString(),Toast.LENGTH_SHORT);
                   }

               }
           });
        }
    }

    private void hataGöster(EditText editUserEditText,String s) {


         editUserEditText.setError(s);
        editUserEditText.requestFocus();

    }
}