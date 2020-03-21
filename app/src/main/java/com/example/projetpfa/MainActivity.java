package com.example.projetpfa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_login,btn_inscrire;
    private GestionSession gestionSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestionSession = new GestionSession(this);

        if(gestionSession.isLogged()){
            Intent intent=new Intent(this,PageAccueil.class);
            startActivity(intent);
            finish();
        }



        btn_login = findViewById(R.id.btn_login);
        btn_inscrire =  findViewById(R.id.btn_inscrire);

        btn_inscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),InscrireActivity.class);
                startActivity(intent);
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ConnexionActivity.class);
                startActivity(intent);
            }
        });
    }
}
