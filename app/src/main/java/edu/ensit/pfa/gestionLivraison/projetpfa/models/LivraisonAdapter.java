package edu.ensit.pfa.gestionLivraison.projetpfa.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.ensit.pfa.gestionLivraison.projetpfa.R;


public class LivraisonAdapter extends RecyclerView.Adapter<LivraisonViewHolder>{

    public Context  context;
    private List<Livraison> data;
    private LayoutInflater inflater;

    public LivraisonAdapter(Context context, List<Livraison> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public LivraisonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  inflater.inflate(R.layout.livraison,parent,false);

        return new LivraisonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LivraisonViewHolder holder, int position) {
        Livraison livraison_actuelle = data.get(position);

        holder.codeLivraison.setText(livraison_actuelle.getCodeLivraison());
        holder.dateLivraison.setText(livraison_actuelle.getDateLivraison());
        holder.codeCommande.setText(livraison_actuelle.getCodeCommande());
        holder.etat.setText(livraison_actuelle.getEtat());
        holder.detail.setText(livraison_actuelle.getDescription());
    }

    @Override
    public int getItemCount() {
      return   data.size();
    }

}
