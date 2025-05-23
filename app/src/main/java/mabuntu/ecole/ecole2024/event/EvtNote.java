package mabuntu.ecole.ecole2024.event;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import mabuntu.ecole.ecole2024.cntrol.CntNoteNew;
import mabuntu.ecole.ecole2024.model_table.MEleve;
import mabuntu.ecole.ecole2024.model_table.MMatiere;
import mabuntu.ecole.ecole2024.tools.TStrList;
import mabuntu.ecole.ecole2024.tvi.TviNote;

public class EvtNote {


    public static Context context;
    public static AutoCompleteTextView classe;
    public static AutoCompleteTextView trimestre;

    public static LinearLayout boxContent;
    public static MEleve[] mElvs;
    public static MMatiere[] mMatieres;
    public static int indiceElv;
    public static EditText idElv;


    public static void changeClasse(){
        String sclasse = classe.getText().toString();
        trimestre.setText("");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_item, TStrList.trimestre(context, sclasse));
        trimestre.setThreshold(1);
        trimestre.setAdapter(adapter);
    }
    public static void changeTrimestre(){
        TviNote.clean_box();
        if (CntNoteNew.fields_isFull() && CntNoteNew.classeHasEleve() && CntNoteNew.classeHasMatiere()){
            TviNote.fill_box();
        }
    }
    public static void clickSave(){
        /*
        if (CntNoteNew.boxIsFull()){
            TviNote.save();
            TviNote.clean_box();
        }
        else
            Toast.makeText(context, "click save", Toast.LENGTH_LONG).show();
         */
    }


}
