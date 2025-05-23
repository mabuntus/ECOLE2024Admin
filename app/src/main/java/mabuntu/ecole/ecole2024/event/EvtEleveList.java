package mabuntu.ecole.ecole2024.event;

import android.content.Context;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import mabuntu.ecole.ecole2024.cntrol.CntEleveList;
import mabuntu.ecole.ecole2024.cntrol.CntMatiereList;
import mabuntu.ecole.ecole2024.tvi.TviEleveList;

public class EvtEleveList {

    public static Context context;
    public static AutoCompleteTextView classe;
    public static LinearLayout boxContent;
    public static AppCompatActivity activity;
    public static ImageView photo;
    public static String codeElv;


    public static void changeClasse(){
        TviEleveList.clean_box();
        if (CntEleveList.classeIsFull()){
            TviEleveList.fill_box();
        }
    }

}
