package com.jepsolucoes.organizzeclone.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.jepsolucoes.organizzeclone.R;
import com.jepsolucoes.organizzeclone.config.ConfigFirebase;
import com.jepsolucoes.organizzeclone.model.User;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private Button buttonLogin;
    private User user;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editEmail = findViewById(R.id.textEmailLogin);
        editPassword = findViewById(R.id.textPasswordLogin);
        buttonLogin = findViewById(R.id.buttonLogin);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = editEmail.getText().toString();
                String textPassword = editPassword.getText().toString();

                if (!textEmail.isEmpty()) {
                    if (!textPassword.isEmpty()) {

                        user = new User();
                        user.setEmail(textEmail);
                        user.setPassword(textPassword);
                        checkLogin();

                    } else {
                        Toast.makeText(LoginActivity.this, "Digite a senha!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Digite o email!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void checkLogin(){

        auth = ConfigFirebase.getAuth();
        auth.signInWithEmailAndPassword(
          user.getEmail(),
          user.getPassword()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Sucesso ao fazer login", Toast.LENGTH_SHORT).show();
                    openMainActivity();
                }else{
                    String exception = "";
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        exception = "E-mail e senha não correspondem a um usuário cadastrado";
                    }catch (FirebaseAuthInvalidUserException e){
                        exception = "Usuario não está cadastrado";
                    }
                    catch (Exception e){
                        exception = "Erro ao cadastrar Usuário";
                        e.printStackTrace();
                    }

                    Toast.makeText(LoginActivity.this, exception, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void openMainActivity(){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

}
