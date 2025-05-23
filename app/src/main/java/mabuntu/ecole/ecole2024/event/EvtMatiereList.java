package mabuntu.ecole.ecole2024.event;

import android.content.Context;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import mabuntu.ecole.ecole2024.cntrol.CntMatiereList;
import mabuntu.ecole.ecole2024.tvi.TviMatiereList;

public class EvtMatiereList {

    public static Context context;
    public static AutoCompleteTextView classe;
    public static LinearLayout boxContent;


    public static void changeClasse(){
        TviMatiereList.clean_box();
        if (CntMatiereList.classeIsFull()){
            TviMatiereList.fill_box();
        }
    }


}
