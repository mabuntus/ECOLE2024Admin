package mabuntu.ecole.ecole2024.cntrol;

import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import mabuntu.ecole.ecole2024.tools.TStrList;

public class TCntrol {

    public static boolean isFull(AutoCompleteTextView autoCmpt, EditText entPrix){
        String auto = autoCmpt.getEditableText().toString();
        String prix = entPrix.getText().toString();

        if (auto.isEmpty() || prix.isEmpty())
            return false;
        return true;
    }

    public static boolean isFull(EditText ...args){
        for (EditText editText : args){
            String val = editText.getEditableText().toString();
            if (val.isEmpty())
                return false;
        }
        return true;
    }

    public static boolean classeIsFull(AutoCompleteTextView classe){
        if (!TCntrol.isFull(classe))
            return false;

        String sclasse = classe.getText().toString();
        for(String item : TStrList.classe){
            if (item.contentEquals(sclasse))
                return true;
        }
        return false;
    }

    public static boolean sexeIsFemme(String sexe){
        return sexe.contains("0");
    }
    public static boolean sexeIsHomme(String sexe){
        return sexe.contains("1");
    }

}
