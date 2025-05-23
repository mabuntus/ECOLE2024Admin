package mabuntu.ecole.ecole2024.event;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import mabuntu.ecole.ecole2024.cntrol.CntBulletin;
import mabuntu.ecole.ecole2024.tools.TStrList;
import mabuntu.ecole.ecole2024.tvi.TviBulletin;

public class EvtBulletin {

    public static Context context;
    public static AutoCompleteTextView classe;
    public static AutoCompleteTextView trimestre;
    public static LinearLayout boxContent;
    public static TextView btnPrint;


    public static void selectClasse(){
        String sclasse = classe.getText().toString();
        trimestre.setText("");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_item, TStrList.trimestre(context, sclasse));
        trimestre.setThreshold(1);
        trimestre.setAdapter(adapter);
    }
    public static void selectTrimestre(){
        TviBulletin.clean_box();
        if (CntBulletin.fieldsIsFull() && CntBulletin.classeHasEleves()){
            TviBulletin.full_box();
        }
    }

    public static void clickPrint(){
        TviBulletin.print_bulletin();
    }

}
