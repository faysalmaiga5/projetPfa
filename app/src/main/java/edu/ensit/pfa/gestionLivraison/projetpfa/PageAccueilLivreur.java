package edu.ensit.pfa.gestionLivraison.projetpfa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.ensit.pfa.gestionLivraison.projetpfa.models.Livraison;
import edu.ensit.pfa.gestionLivraison.projetpfa.models.LivraisonAdapter;
import edu.ensit.pfa.gestionLivraison.projetpfa.pfarequete.PfaRequete;

public class PageAccueilLivreur extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    List<Livraison> livraison_list;

    RequestQueue rq;

    String request_url = "http://localhost/testapi.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_accueil_livreur);

        rq = Volley.newRequestQueue(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        livraison_list = new ArrayList<>();

        sendRequest();

    }


    public void sendRequest(){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, request_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i = 0; i < response.length(); i++){

                    Livraison liv = new Livraison();

                    try {

                        JSONObject jsonObject = response.getJSONObject(i);

                        liv.setCodeLivraison(jsonObject.getString("codeLivraison"));
                        liv.setDateLivraison(jsonObject.getString("dateLivraison"));
                        liv.setCodeCommande(jsonObject.getString("codeCommande"));
                        liv.setEtat(jsonObject.getString("etat"));
                        liv.setDescription(jsonObject.getString("description"));
                        livraison_list.add(liv);
                        Log.d("APP",liv.getCodeCommande());
                        mAdapter = new LivraisonAdapter(PageAccueilLivreur.this, livraison_list);
                        recyclerView.setAdapter(mAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Volley Error: ", String.valueOf(error));
            }
        });

        rq.add(jsonArrayRequest);

    }

}
