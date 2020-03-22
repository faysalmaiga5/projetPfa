package edu.ensit.pfa.gestionLivraison.projetpfa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PageAccueil extends AppCompatActivity {

    private GestionSession gestionSession;
    private TextView textView;
    private Button btn_loogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_accueil);
        //textView = findViewById(R.id.tv_pseudo);
       // btn_loogout = findViewById(R.id.btn_logout);
        gestionSession = new GestionSession(this);

        if(gestionSession.isLogged()){
            String username = gestionSession.getPseudo();
            String id = gestionSession.getID();
            Toast toast = Toast.makeText(getApplicationContext(),"vous etes actuellement connecté avec succes "+username,Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            //textView.setText(username + " vous connecté ");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.page_accueil_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                Intent ajoutIntent = new Intent(this,AjoutLivreurActivity.class);
                startActivity(ajoutIntent);
                finish();
                break;
            case R.id.logout:
                gestionSession.logout();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
            return false;
    }
}
