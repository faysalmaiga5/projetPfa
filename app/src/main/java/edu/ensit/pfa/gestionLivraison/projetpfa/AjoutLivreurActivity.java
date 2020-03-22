package edu.ensit.pfa.gestionLivraison.projetpfa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Map;

import edu.ensit.pfa.gestionLivraison.projetpfa.pfarequete.PfaRequete;

public class AjoutLivreurActivity extends AppCompatActivity {


    private Button btn_ajout_livreur;
    private ProgressBar loader;
    private TextInputLayout til_nom,til_prenom,til_adresse,til_nomUser,til_motDePasse,til_motDePasse1;
    private RequestQueue queue;
    private PfaRequete requete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_livreur);

        btn_ajout_livreur =findViewById(R.id.btn_ajout_livreur);
        til_nom = findViewById(R.id.til_nom);
        til_prenom = findViewById(R.id.til_prenom);
        til_adresse = findViewById(R.id.til_adresse);
        til_nomUser = findViewById(R.id.til_nomUser);
        til_motDePasse = findViewById(R.id.til_motDePasse);
        til_motDePasse1 = findViewById(R.id.til_motDePasse2);
        loader = findViewById(R.id.pb_loader);

        queue = VolleySingleton.getInstance(this).getRequestQueue();
        requete = new PfaRequete(this,queue);


        btn_ajout_livreur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loader.setVisibility(View.VISIBLE);

                String nom = til_nom.getEditText().getText().toString().trim();
                final String prenom = til_prenom.getEditText().getText().toString().trim();
                String adresse = til_adresse.getEditText().getText().toString().trim();
                String nomUser = til_nomUser.getEditText().getText().toString().trim();
                final String motDePasse = til_motDePasse.getEditText().getText().toString().trim();
                String motDePasse1 = til_motDePasse1.getEditText().getText().toString().trim();
                if(nom.length() > 0 && prenom.length() > 0 && adresse.length() > 0 && nomUser.length() > 0 && motDePasse.length() > 0 && motDePasse1.length() > 0 ){


                    requete.register(nom, prenom, adresse, nomUser,motDePasse,motDePasse1, new PfaRequete.AjoutLivreurCallback() {
                        @Override
                        public void onSucces(String message) {
                            loader.setVisibility(View.GONE);
                            Intent intent = new Intent(getApplicationContext(),ConnexionActivity.class);
                            intent.putExtra("INSCRIRE",message);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void inputErrors(Map<String, String> errors) {
                            loader.setVisibility(View.GONE);
                            if (errors.get("nom") != null) {
                                til_nom.setError(errors.get("nom"));
                            } else {
                                til_nom.setErrorEnabled(false);
                            }

                            if (errors.get("prenom") != null) {
                                til_prenom.setError(errors.get("prenom"));
                            } else {
                                til_prenom.setErrorEnabled(false);
                            }

                            if (errors.get("adresse") != null) {
                                til_adresse.setError(errors.get("adresse"));
                            } else {
                                til_adresse.setErrorEnabled(false);
                            }

                            if (errors.get("nomUser") != null) {
                                til_nomUser.setError(errors.get("nomUser"));
                            } else {
                                til_nomUser.setErrorEnabled(false);
                            }

                            if (errors.get("motDePasse") != null) {
                                til_motDePasse.setError(errors.get("motDePasse"));
                            } else {
                                til_motDePasse.setErrorEnabled(false);
                            }
                        }
                        @Override
                        public void onError(String message) {
                            loader.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                        }
        });
    }else{
                    Toast.makeText(getApplicationContext(),"Veuillez remplir tous les chgamps ",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
