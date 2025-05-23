package mabuntu.ecole.ecole2024.model_table;

import android.content.Context;

import java.util.ArrayList;

import mabuntu.ecole.ecole2024.tools.FILE;
import mabuntu.ecole.ecole2024.tools.Tools;

public class MEleve {


    public  String code;
    public  String matricule;
    public  String classe;
    public  String prenom;
    public  String nom;
    public  String sexe;
    public  String dateN;
    public  String lieuN;
    private static final int TAB_SIZE = 8;
    private static final String fname = FILE.eleve;

    private static final int niv_code = 0;
    private static final int niv_matricule = 1;
    private static final int niv_classe = 2;
    private static final int niv_prenom = 3;
    private static final int niv_nom = 4;
    private static final int niv_sexe = 5;
    private static final int niv_dateN = 6;
    private static final int niv_lieuN = 7;

    public MEleve(){
        this("", "", "", "", "", "", "", "");
    }

    public MEleve(String code,
                  String matricule,
                  String classe,
                  String prenom,
                  String nom,
                  String sexe,
                  String dateN,
                  String lieuN){
        this.code = code;
        this.matricule = matricule;
        this.classe = classe;
        this.prenom = prenom;
        this.nom = nom;
        this.sexe = sexe;
        this.dateN = dateN;
        this.lieuN = lieuN;
    }

    public static boolean add(Context context,
                              String codeElv,
                              String matricule,
                              String classe,
                              String prenom,
                              String nom,
                              String sexe,
                              String dtN,
                              String ltN){
        return TDb.add(context, FILE.eleve, codeElv, matricule, classe, prenom, nom, sexe, dtN, ltN);
    }
    public static MEleve[] fromClasse(Context context, String classe){
        ArrayList<String[]> list = TDb.getAll(context, fname, niv_classe, classe);
        if (list == null || list.isEmpty()) return null;

        int size = list.size();
        MEleve[] mEleves = new MEleve[size];
        for(int i = 0; i < size; i++){
            String[] t = list.get(i);
            if (t.length == TAB_SIZE)
                mEleves[i] = tab(list.get(i));
        }
        return mEleves;
    }
    public static MEleve[] getAll(Context context){
        ArrayList<String[]> list = TDb.getAll(context, fname);
        if (list == null || list.isEmpty()) return null;

        int size = list.size();
        MEleve[] mEleves = new MEleve[size];
        for(int i = 0; i < size; i++){
            String[] t = list.get(i);
            if (t.length == TAB_SIZE)
                mEleves[i] = tab(list.get(i));
        }
        return mEleves;
    }
    public static boolean delFromCode(Context context, String code){
        return TDb.rmv(context, fname, code);
    }
    public static boolean delClasse(Context context, String classe){
        return TDb.rmv(context, fname, niv_classe, classe);
    }
    public static boolean delFromMatricule(Context context, String matricule){
        return TDb.rmv(context, fname, niv_matricule, matricule);
    }
    public static boolean updt(Context context, MEleve obj){
        return TDb.updt(context, fname, obj.code, obj.matricule, obj.classe, obj.prenom, obj.nom, obj.sexe, obj.dateN, obj.lieuN);
    }

    public static MEleve fromCode(Context context, String code){
        return fromNiveau(context, niv_code, code);
    }
    public static MEleve fromMatricule(Context context, String matricule){
        MEleve eleve = fromNiveau(context, MEleve.niv_matricule, matricule);
        return eleve;
    }
    public static MEleve fromMatricule(Context context, String matricule, String classe){
        MEleve[] eleves = fromClasse(context, classe);
        if (eleves == null) return null;

        for(int i = 0; i < eleves.length; i++){
            MEleve elv = eleves[i];
            if (elv != null || elv.matricule.contentEquals(matricule))
                return eleves[i];
        }
        return null;
    }
    private static MEleve fromNiveau(Context context, int niveau, String colNiv){
        String[] t = TDb.get(context, fname, niveau, colNiv);
        return (t != null && t.length == TAB_SIZE) ? tab(t) : null;
    }

    public static MEleve tab(String[] tab){
        String code = Tools.b642str(tab[0]);
        String matricule = Tools.b642str(tab[1]);
        String classe = Tools.b642str(tab[2]);
        String prenom = Tools.b642str(tab[3]);
        String nom = Tools.b642str(tab[4]);
        String sexe = Tools.b642str(tab[5]);
        String dateN = Tools.b642str(tab[6]);
        String lieuN = Tools.b642str(tab[7]);
        return new MEleve(code, matricule, classe, prenom, nom, sexe, dateN, lieuN);
    }

}

