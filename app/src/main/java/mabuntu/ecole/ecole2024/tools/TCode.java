package mabuntu.ecole.ecole2024.tools;

import android.annotation.SuppressLint;
import android.content.Context;

public class TCode {

    private static String ELEVE = "eleve";
    private static String MATIERE = "matiere";
    private static String NOTE1 = "note1";
    private static String NOTE2 = "note2";
    private static String NOTE3 = "note3";
    private static String MOY1 = "moy1";
    private static String MOY2 = "moy2";
    private static String MOY3 = "moy3";
    private static String ECOLE = "ecole";


    public static String eleve(){ return String.format("%s%s", ELEVE, code()); }

    public static String matiere(){ return String.format("%s%s", MATIERE, code()); }
    public static String note1(){ return String.format("%s%s", NOTE1, code()); }
    public static String note2(){ return String.format("%s%s", NOTE2, code()); }
    public static String note3(){ return String.format("%s%s", NOTE3, code()); }
    public static String moy1(){ return String.format("%s%s", MOY1, code()); }
    public static String moy2(){ return String.format("%s%s", MOY2, code()); }
    public static String moy3(){ return String.format("%s%s", MOY3, code()); }


    private static long last_code = 0;
    private static String code(){
        Long new_code = System.currentTimeMillis();
        if (last_code == new_code) {
            last_code = new_code;
            return String.format("-%s__odd", new_code.toString());
        }
        else {
            last_code = new_code;
            return String.format("-%s", new_code.toString());
        }
    }



}
