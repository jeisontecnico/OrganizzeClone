package com.jepsolucoes.organizzeclone.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.jepsolucoes.organizzeclone.R;
import com.jepsolucoes.organizzeclone.config.ConfigFirebase;

public class SelectActivity extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_cadastro);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = ConfigFirebase.getAuth();
        if(auth.getCurrentUser() != null){
            startActivity(new Intent(this,MainActivity.class));
        }
       }

    public void btEntrar(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }
    public void btCadastrar(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }
}
