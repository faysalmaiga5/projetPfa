package edu.ensit.pfa.gestionLivraison.projetpfa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.google.android.material.textfield.TextInputLayout;

import edu.ensit.pfa.gestionLivraison.projetpfa.pfarequete.PfaRequete;

public class ConnexionLivreurActivity extends AppCompatActivity {

    private Button btn_login_livreur;
    private TextInputLayout til_nomUser_con,til_motDePasse_con;
    private RequestQueue queue;
    private PfaRequete requete;
    private ProgressBar pb_loader;
    private Handler handler;
    private GestionSession gestionSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion_livreur);

        til_nomUser_con = findViewById(R.id.til_nomUser_con);
        til_motDePasse_con = findViewById(R.id.til_motDePasse_con);
        btn_login_livreur = findViewById(R.id.btn_login_livreur_con);
        pb_loader = findViewById(R.id.pb_loader);
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        requete = new PfaRequete(this,queue);

        handler = new Handler();

        gestionSession = new GestionSession(this);

        btn_login_livreur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  String nomUser = til_nomUser_con.getEditText().getText().toString().trim();
                final String motDePasse = til_motDePasse_con.getEditText().getText().toString().trim();
                pb_loader.setVisibility(View.VISIBLE);
                if(nomUser.length() > 0 && motDePasse.length() > 0){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            requete.connexionLivreur(nomUser, motDePasse, new PfaRequete.ConnexionLivreurCallback() {
                                @Override
                                public void onSucces(String codeLivreur, String nomUser) {
                                    pb_loader.setVisibility(View.VISIBLE);
                                    //gestionSession.insertUser(codeLivreur,nomUser);
                                    Intent intent = new Intent(getApplicationContext(),PageAccueil.class);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onError(String message) {
                                    pb_loader.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    },1000);

                }else{
                    Toast.makeText(getApplicationContext(),"Veuillez remplir tous les chgamps ",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
