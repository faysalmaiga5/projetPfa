package edu.ensit.pfa.gestionLivraison.projetpfa.models;

public class Livraison {
    private String codeLivraison;
    private String dateLivraison;
    private String etat;
    private String Description;
    private String codeLivreur;
    private String codeCommande;

    public Livraison() {
    }

    public Livraison(String codeLivraison, String dateLivraison, String etat, String description, String codeLivreur, String codeCommande) {
        this.codeLivraison = codeLivraison;
        this.dateLivraison = dateLivraison;
        this.etat = etat;
        Description = description;
        this.codeLivreur = codeLivreur;
        this.codeCommande = codeCommande;
    }


    public String getCodeLivraison() {
        return codeLivraison;
    }

    public void setCodeLivraison(String codeLivraison) {
        this.codeLivraison = codeLivraison;
    }

    public String getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(String dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCodeLivreur() {
        return codeLivreur;
    }

    public void setCodeLivreur(String codeLivreur) {
        this.codeLivreur = codeLivreur;
    }

    public String getCodeCommande() {
        return codeCommande;
    }

    public void setCodeCommande(String codeCommande) {
        this.codeCommande = codeCommande;
    }
}
