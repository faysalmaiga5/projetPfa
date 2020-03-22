package edu.ensit.pfa.gestionLivraison.projetpfa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_login,btn_inscrire,btn_log_liv;
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
        btn_log_liv = findViewById(R.id.btn_login_livreur);

        btn_inscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),InscrireActivity.class);
                startActivity(intent);
                finish();
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ConnexionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_log_liv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ConnexionLivreurActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
