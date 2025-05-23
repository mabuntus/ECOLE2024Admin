package mabuntu.ecole.ecole2024.tools;


import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import mabuntu.ecole.ecole2024.model_table.MMatiere;

public class TStrList {


    public static String[] classe = new String[] {
            "creche", "pepiniere", "garderie", "petite-section", "moyenne-section", "grande-section",
            "CP1", "CP2", "CE1", "CE2", "CM1", "CM2",
            "7eme", "8eme", "9eme", "10eme"
    };
    public static String[] trimestre = new String[] {
            "1er trimestre", "2eme trimestre", "3eme trimestre"
    };
    public static String[] classe_trimestre = new String[] {
            "creche", "pepiniere", "garderie", "petite-section", "moyenne-section", "grande-section",
            "CP1", "CP2", "CE1", "CE2", "CM1", "CM2"
    };
    public static String[] classe_semestre = new String[] {
            "7eme", "8eme", "9eme", "10eme",
            "11eme Scientifique", "11eme Litteraire",
            "12eme Scientifique", "12eme Litteraire"
    };
    public static String[] semestre = new String[] {
            "1er semestre", "2eme semestre"
    };
    public static String[] regime = new String[] {
        "externat", "semi-internat", "internat", "pensionnat"
    };
    public static String[] empty(){
        return new String[] {};
    }

    public static String[] trimestre(Context context, String classe){
        for(String val : classe_trimestre){
            if (val.contentEquals(classe))
                return trimestre;
        }
        for(String val : classe_semestre){
            if (val.contentEquals(classe))
                return semestre;
        }
        return empty();
    }
    public static String[] matiere(Context context, String classe){
        HashSet<String> setMatiere = new HashSet<>();
        MMatiere[] mMatieres = MMatiere.fromClasse(context, classe);
        if (mMatieres == null) return empty();

        for(int i = 0; i < mMatieres.length; i++){
            MMatiere matiere = mMatieres[i];
            if (matiere != null && matiere.name != null) {
                setMatiere.add(matiere.name);
            }
        }
        if (setMatiere.isEmpty()) return empty();

        int i = 0;
        int size = setMatiere.size();
        String[] tab = new String[size];
        for(String val : setMatiere){
            tab[i++] = val;
        }
        return tab;
    }

    public static String sexeId(EditText esexe){
       return sexeId(esexe.getText().toString());
    }
    public static String sexeId(String sexe){
        return sexe.contains("h") ? "1" : "0";
    }
    public static boolean isFemme(String sexe){
        return sexe.contains("1") ? false : true;
    }
    public static String sexeName(String sexe){
        return sexe.contains("0") ? "femme" : "homme";
    }


}
