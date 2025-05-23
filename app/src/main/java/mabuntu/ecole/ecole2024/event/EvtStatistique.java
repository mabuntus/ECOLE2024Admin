package mabuntu.ecole.ecole2024.event;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import mabuntu.ecole.ecole2024.cntrol.CntStatistique;
import mabuntu.ecole.ecole2024.tools.TStrList;
import mabuntu.ecole.ecole2024.tvi.TviStatistique;
import mabuntu.tombolia.pressing.R;

public class EvtStatistique {

    public static Context context;
    public static AutoCompleteTextView classe;
    public static AutoCompleteTextView trimestre;
    public static LinearLayout boxContent;
    public static LinearLayout boxBtnPrint;


    public static void changeClasse(){
        String sclasse = classe.getText().toString();
        trimestre.setText("");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_item, TStrList.trimestre(context, sclasse));
        trimestre.setThreshold(1);
        trimestre.setAdapter(adapter);
    }
    public static void changeTrimestre(){
        TviStatistique.clean_box();
        if (CntStatistique.fields_isFull()){
            TviStatistique.fill_box();
        }

    }
    public static void clickPrint(){
    }

}
