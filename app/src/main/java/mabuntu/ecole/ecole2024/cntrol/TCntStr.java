package mabuntu.ecole.ecole2024.cntrol;

import android.widget.AutoCompleteTextView;

import mabuntu.ecole.ecole2024.tools.TStrList;

public class TCntStr {

    public static boolean is_classe(String classe){
        return in_list(classe, TStrList.classe);
    }

    public static boolean is_classe(AutoCompleteTextView classe){
        String sclasse =  classe.getText().toString();
        return in_list(sclasse, TStrList.classe);
    }

    public static boolean is_regime(AutoCompleteTextView regime){
        String sregime = regime.getText().toString();
        return in_list(sregime, TStrList.regime);
    }

    private static boolean in_list(String item, String[] list){
        for (String value : list){
            if (value.contentEquals(item))
                return true;
        }
        return false;
    }

    public static int idTrimestre(AutoCompleteTextView trimestre){
        String strimestre = trimestre.getText().toString();
        int idTrimestre = strimestre.contains("1") ? 1 : strimestre.contains("2") ? 2 : 3;
        return idTrimestre;
    }
    public static boolean classeIsTrimestre(String classe){
        for(String val : TStrList.classe_trimestre) {
            if (val.contentEquals(classe))
                return true;
        }
        return false;
    }

    public static boolean istrimestre(AutoCompleteTextView trimestre){
        String strimestre = trimestre.getText().toString();
        return strimestre.contains("trimestre");
    }

}
