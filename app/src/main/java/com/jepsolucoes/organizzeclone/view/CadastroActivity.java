package com.jepsolucoes.organizzeclone.view;

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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.jepsolucoes.organizzeclone.R;
import com.jepsolucoes.organizzeclone.config.ConfigFirebase;
import com.jepsolucoes.organizzeclone.model.User;

public class CadastroActivity extends AppCompatActivity {

    private EditText editName, editEmail, editPassword;
    private Button btSignup;
    private FirebaseAuth auth;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editName = findViewById(R.id.editNameCadastro);
        editEmail = findViewById(R.id.editEmailCadastro);
        editPassword = findViewById(R.id.editPasswordCadastro);
        btSignup = findViewById(R.id.buttonSignup);

        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textNome = editName.getText().toString();
                String textEmail = editEmail.getText().toString();
                String textPassword = editPassword.getText().toString();

                //validar se campos foram preenchidos
                if (!textNome.isEmpty()) {
                    if (!textEmail.isEmpty()) {
                        if (!textPassword.isEmpty()) {
                            user = new User();
                            user.setName(textNome);
                            user.setEmail(textEmail);
                            user.setPassword(textPassword);
                            registerUser();

                        } else {
                            Toast.makeText(CadastroActivity.this,
                                    "Preencha a senha!",
                                    Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(CadastroActivity.this,
                                "Preencha o Email!",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(CadastroActivity.this,
                            "Preencha o nome!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void registerUser() {
        auth = ConfigFirebase.getAuth();
        auth.createUserWithEmailAndPassword(
            user.getEmail(),
                user.getPassword()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this,
                            "Sucesso ao cadastrar!",
                            Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    String exception = "";
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        exception = "Digite uma senha mais forte";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        exception = "Por Favor, digite um email válido";
                    }catch (FirebaseAuthUserCollisionException e){
                        exception = "Usuário já cadastrado!";
                    }catch (Exception e){
                        exception = "Erro ao cadastrar Usuário";
                        e.printStackTrace();
                    }

                    Toast.makeText(CadastroActivity.this,
                            exception,
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
