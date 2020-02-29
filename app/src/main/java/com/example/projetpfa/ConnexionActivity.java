package com.example.projetpfa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.projetpfa.pfarequete.PfaRequete;
import com.google.android.material.textfield.TextInputLayout;

public class ConnexionActivity extends AppCompatActivity {

    private Button btn_login;
    private TextInputLayout til_pseudo_con,til_password;
    private RequestQueue queue;
    private PfaRequete requete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        til_pseudo_con = findViewById(R.id.til_pseudo_con);
        til_password = findViewById(R.id.til_password_con);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pseudo = til_pseudo_con.getEditText().getText().toString().trim();
                String password = til_password.getEditText().getText().toString().trim();

                if(pseudo.length() > 0 && password.length() > 0){


                requete.connexion(pseudo, password, new PfaRequete.ConnexionCallback() {
                    @Override
                    public void onSucces(String id, String pseudo) {
                        Intent intent = new Intent(getApplicationContext(),PageAccueil.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                    }
                });
                }else{
                    Toast.makeText(getApplicationContext(),"Veuillez remplir tous les chgamps ",Toast.LENGTH_LONG).show();
                }
            }
         });
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        requete = new PfaRequete(this,queue);
    }
}
