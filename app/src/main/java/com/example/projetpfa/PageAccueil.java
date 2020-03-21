package com.example.projetpfa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PageAccueil extends AppCompatActivity {

    private GestionSession gestionSession;
    private TextView textView;
    private Button btn_loogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_accueil);
        textView = findViewById(R.id.tv_pseudo);
        btn_loogout = findViewById(R.id.btn_logout);
        gestionSession = new GestionSession(this);

        if(gestionSession.isLogged()){
            String username = gestionSession.getPseudo();
            String id = gestionSession.getID();
            textView.setText(username);
        }
        btn_loogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gestionSession.logout();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
