package edu.ensit.pfa.gestionLivraison.projetpfa.models;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.ensit.pfa.gestionLivraison.projetpfa.R;

public class LivraisonViewHolder extends RecyclerView.ViewHolder {

    TextView codeLivraison,dateLivraison,codeCommande,etat,detail;

    public LivraisonViewHolder(@NonNull View itemView) {
        super(itemView);
        codeLivraison = itemView.findViewById(R.id.codelivraison_cv);
        dateLivraison = itemView.findViewById(R.id.datelivraison_cv);
        codeCommande = itemView.findViewById(R.id.codecommande_cv);
        etat = itemView.findViewById(R.id.etat_cv);
        detail = itemView.findViewById(R.id.detail_cv);
    }
}
