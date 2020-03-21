package com.example.projetpfa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.projetpfa.pfarequete.PfaRequete;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Map;

public class InscrireActivity extends AppCompatActivity {

    private Button btn_env;
    private ProgressBar loader;
    private TextInputLayout til_pseudo,til_email,til_password,til_password2;
    private RequestQueue queue;
    private PfaRequete requete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrire);

        btn_env =findViewById(R.id.btn_send);
        til_pseudo = findViewById(R.id.til_pseudo);
        til_email = findViewById(R.id.til_email);
        til_password = findViewById(R.id.til_password);
        til_password2 = findViewById(R.id.til_password2);
        loader = findViewById(R.id.pb_loader);

        queue = VolleySingleton.getInstance(this).getRequestQueue();
        requete = new PfaRequete(this,queue);

        btn_env.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loader.setVisibility(View.VISIBLE);
                String username = til_pseudo.getEditText().getText().toString().trim();
                String email = til_email.getEditText().getText().toString().trim();
                String password = til_password.getEditText().getText().toString().trim();
                String password2 = til_password2.getEditText().getText().toString().trim();

                if(username.length() > 0 && email.length() > 0 && password.length() > 0 && password2.length() > 0){

                requete.register(username, email, password, password2, new PfaRequete.InscriptionCallback() {
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
                        if(errors.get("username") != null){
                            til_pseudo.setError(errors.get("username"));
                        }else{
                            til_pseudo.setErrorEnabled(false);
                        }

                        if(errors.get("email") != null){
                            til_email.setError(errors.get("email"));
                        }else{
                            til_email.setErrorEnabled(false);
                        }

                        if(errors.get("password") != null){
                            til_password.setError(errors.get("password"));
                        }else{
                           til_password.setErrorEnabled(false);
                        }
                    }

                    @Override
                    public void onError(String message) {
                            loader.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                    }
                });
                }else {
                    Toast.makeText(getApplicationContext(),"Veuillez remplir tous les chgamps ",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
