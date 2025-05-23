package mabuntu.ecole.ecole2024.event;

import android.content.Context;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import mabuntu.ecole.ecole2024.cntrol.CntMatiereNew;
import mabuntu.ecole.ecole2024.cntrol.TCntrol;
import mabuntu.ecole.ecole2024.tvi.TviMatiereNew;

public class EvtMatiereNew {

    public static Context context;
    public static AutoCompleteTextView classe;
    public static EditText matiere;
    public static EditText coef;
    public static TextView btnAdd;
    public static LinearLayout boxContent;
    public static LinearLayout btnSave;

    public static void clickAdd(){
        if (CntMatiereNew.matiere_and_coef_isFull()){
            TviMatiereNew.addMatiere_to_box();
            TviMatiereNew.clean_matieres();
        }
        else
            Toast.makeText(context, "champs vide", Toast.LENGTH_LONG).show();
    }

    public static void clickSave(){
        if (CntMatiereNew.classeIsFull() && CntMatiereNew.boxIsFull()){
            TviMatiereNew.save();
            TviMatiereNew.clean_all();
        }
        else
            Toast.makeText(context, "champs classe ou matiere vide", Toast.LENGTH_LONG).show();
    }


}
