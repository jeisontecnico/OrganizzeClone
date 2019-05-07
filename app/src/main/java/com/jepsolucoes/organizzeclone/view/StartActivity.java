package com.jepsolucoes.organizzeclone.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.jepsolucoes.organizzeclone.R;
import com.jepsolucoes.organizzeclone.config.ConfigFirebase;

public class StartActivity extends IntroActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setFullscreen(true);
        super.onCreate(savedInstanceState);

        checkUserLogin();

        setButtonBackVisible(false);
        setButtonNextVisible(false);


        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .backgroundDark(android.R.color.white)
                .fragment(R.layout.intro_1,R.style.Theme_AppCompat_Light_NoActionBar)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .backgroundDark(android.R.color.white)
                .fragment(R.layout.intro_2,R.style.Theme_AppCompat_Light_NoActionBar)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .backgroundDark(android.R.color.white)
                .fragment(R.layout.intro_3,R.style.Theme_AppCompat_Light_NoActionBar)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .backgroundDark(android.R.color.white)
                .fragment(R.layout.intro_4,R.style.Theme_AppCompat_Light_NoActionBar)
                .canGoForward(false)
                .buttonCtaLabel("Vamos Começar")
                .buttonCtaClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(StartActivity.this, SelectActivity.class));
                    }
                })
                .build()
        );

    }

    public void checkUserLogin(){
        auth = ConfigFirebase.getAuth();
        if(auth.getCurrentUser() != null){
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
