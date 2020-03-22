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
import edu.ensit.pfa.gestionLivraison.projetpfa.pfarequete.PfaRequete;
import com.google.android.material.textfield.TextInputLayout;

public class ConnexionActivity extends AppCompatActivity {

    private Button btn_login;
    private TextInputLayout til_pseudo_con,til_password;
    private RequestQueue queue;
    private PfaRequete requete;
    private ProgressBar pb_loader;
    private Handler handler;
    private GestionSession gestionSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

    //recupertaion du message de l'inscription
        Intent intent = getIntent();
        if(intent.hasExtra("INSCRIRE")){
            Toast.makeText(this,intent.getStringExtra("INSCRIRE"),Toast.LENGTH_LONG).show();
        }

        til_pseudo_con = findViewById(R.id.til_pseudo_con);
        til_password = findViewById(R.id.til_password_con);
        btn_login = findViewById(R.id.btn_login);
        pb_loader = findViewById(R.id.pb_loader);
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        requete = new PfaRequete(this,queue);

        handler = new Handler();

        gestionSession = new GestionSession(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final  String username = til_pseudo_con.getEditText().getText().toString().trim();
                final String password = til_password.getEditText().getText().toString().trim();
                pb_loader.setVisibility(View.VISIBLE);
                if(username.length() > 0 && password.length() > 0){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            requete.connexion(username, password, new PfaRequete.ConnexionCallback() {
                                @Override
                                public void onSucces(String id, String username) {
                                    pb_loader.setVisibility(View.VISIBLE);
                                    gestionSession.insertUser(id,username);
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
