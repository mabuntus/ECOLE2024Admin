package mabuntu.ecole.ecole2024.model_table;

import mabuntu.ecole.ecole2024.MoyTotal;

public class MMoyTotal {

    public String matricule;
    public boolean trimestre;
    public String prenom;
    public String nom;
    public boolean isFemme;

    public String moy1;
    public String moy2;
    public String moy3;

    public String total1;
    public String total2;
    public String total3;

    public String rang1;
    public String rang2;
    public String rang3;

    public String total;
    public String moy;
    public String rang;

    public MMoyTotal(String matricule,
                    String prenom,
                    String nom,
                    boolean isFemme,
                    String moy1,
                    String moy2,
                    String moy3,
                    String total1,
                    String total2,
                    String total3,
                    String rang1,
                    String rang2,
                    String rang3,
                    String moy,
                    String total,
                    String rang,
                    boolean trimestre){
        this.matricule = matricule;
        this.prenom = prenom;
        this.nom = nom;
        this.isFemme = isFemme;
        this.moy1 = moy1;
        this.moy2 = moy2;
        this.moy3 = moy3;
        this.total1 = total1;
        this.total2 = total2;
        this.total3 = total3;
        this.rang1 = rang1;
        this.rang2 = rang2;
        this.rang3 = rang3;
        this.total = total;
        this.moy = moy;
        this.rang = rang;
        this.trimestre = trimestre;
    }

    public MMoyTotal(){
        this("", "", "", true, "", "", "", "", "", "", "", "", "", "", "", "", true);
    }

}
