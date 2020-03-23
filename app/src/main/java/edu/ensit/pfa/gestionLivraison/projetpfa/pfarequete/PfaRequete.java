package edu.ensit.pfa.gestionLivraison.projetpfa.pfarequete;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.ensit.pfa.gestionLivraison.projetpfa.PageAccueilLivreur;
import edu.ensit.pfa.gestionLivraison.projetpfa.models.Livraison;

public class PfaRequete {
    private Context context;
    private RequestQueue queue;

    public PfaRequete(Context context, RequestQueue queue) {
        this.context = context;
        this.queue = queue;
    }

    public void register(final String username,final String email,final  String password,final  String password2,final InscriptionCallback callback){

        String url = "http://192.168.211.1/pfa/inscrire.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Map<String,String> errors = new HashMap<>();

                try {
                    JSONObject json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");


                    if(!error){
                        //l inbscription s est bien poassee
                        callback.onSucces("L inscription s est bien deroulé");
                    }else {
                        JSONObject messages = json.getJSONObject("message");
                        if(messages.has("username")){
                            errors.put("username",messages.getString("username"));
                        }
                        if(messages.has("email")){
                            errors.put("email",messages.getString("email"));
                        }
                        if(messages.has("password")){
                            errors.put("password",messages.getString("password"));
                        }
                        callback.inputErrors(errors);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof NetworkError){
                    callback.onError("Impossible de se connecter");
                }else if(error instanceof  VolleyError){
                    callback.onError("Une erreur s est produite");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("username", username);
                map.put("email", email);
                map.put("password", password);
                map.put("password2", password2);
                return map;
            }
        };

        queue.add(request);

    }



    public interface InscriptionCallback{
        void onSucces(String message);
        void inputErrors(Map<String,String> errors);
        void onError(String message);
    }





    //ajout d un livreur
    public void register(final String nom,final String prenom,final String adresse,final String nomUser,final  String motDePasse,final  String motDePasse1,final AjoutLivreurCallback callback){

        String url = "http://192.168.211.1/pfa/ajoutLivreur.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Map<String,String> errors = new HashMap<>();

                try {
                    JSONObject json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");


                    if(!error){
                        //l inbscription s est bien poassee
                        callback.onSucces("L inscription s est bien deroulé");
                    }else {
                        JSONObject messages = json.getJSONObject("message");
                        if(messages.has("nom")){
                            errors.put("nom",messages.getString("nom"));
                        }
                        if(messages.has("prenom")){
                            errors.put("prenom",messages.getString("prenom"));
                        }
                        if(messages.has("adresse")){
                            errors.put("adresse",messages.getString("adresse"));
                        }
                        if(messages.has("nomUser")){
                            errors.put("nomUser",messages.getString("nomUser"));
                        }
                        if(messages.has("motDePasse")){
                            errors.put("motDePasse",messages.getString("motDePasse"));
                        }

                        callback.inputErrors(errors);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof NetworkError){
                    callback.onError("Impossible de se connecter");
                }else if(error instanceof  VolleyError){
                    callback.onError("Une erreur s est produite");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("nom", nom);
                map.put("prenom", prenom);
                map.put("adresse", adresse);
                map.put("nomUser", nomUser);
                map.put("motDePasse", motDePasse);
                map.put("motDePasse1", motDePasse1);
                return map;
            }
        };

        queue.add(request);

    }

    public interface AjoutLivreurCallback{
        void onSucces(String message);
        void inputErrors(Map<String,String> errors);
        void onError(String message);
    }



    public void connexion(final String username, final String password, final ConnexionCallback callback){
        String url = "http://192.168.211.1/pfa/connexion.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject json  = null;

                try {
                    json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");

                    if(!error){
                        String id = json.getString("id");
                        String username = json.getString("username");
                        callback.onSucces(id,username);
                    }else {
                       callback.onError(json.getString("message"));
                    }
                } catch (JSONException e) {
                    callback.onError("Une erreur s est produite");
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof NetworkError){
                    callback.onError("Impossible de se connecter");
                }else if(error instanceof  VolleyError){
                    callback.onError("Une erreur s est produite");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("username", username);
                map.put("password", password);
                return map;
            }
        };

        queue.add(request);

    }


    public interface ConnexionCallback{
        void onSucces(String id,String username);
        void onError(String message);
    }







    public void connexionLivreur(final String nomUser, final String motDePasse, final ConnexionLivreurCallback callback){
        String url = "http://192.168.211.1/pfa/VerifAuthentification.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("APP",response);

                JSONObject json  = null;

                try {
                    json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");

                    if(!error){
                        String codeLivreur = json.getString("codeLivreur");
                        String nomUser = json.getString("nomUser");
                        callback.onSucces(codeLivreur,nomUser);
                    }else {
                        callback.onError(json.getString("message"));
                    }
                } catch (JSONException e) {
                    callback.onError("Probleme coté json ");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof NetworkError){
                    callback.onError("Impossible de se connecter");
                }else if(error instanceof  VolleyError){
                    callback.onError("Une erreur  s est produite");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("nomUser", nomUser);
                map.put("motDePasse", motDePasse);
                return map;
            }
        };

        queue.add(request);

    }


    public interface ConnexionLivreurCallback{
        void onSucces(String codeLivreur,String nomUser);
        void onError(String message);
    }

}
