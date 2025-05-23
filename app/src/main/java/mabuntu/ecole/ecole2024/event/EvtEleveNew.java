package mabuntu.ecole.ecole2024.event;

import android.content.Context;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import mabuntu.ecole.ecole2024.cntrol.CntEleveNew;
import mabuntu.ecole.ecole2024.tvi.TviEleveNew;

public class EvtEleveNew {

    public static Context context;
    public static AutoCompleteTextView classe;
    public static ImageView photo;
    public static EditText prenom;
    public static EditText nom;
    public static EditText sexe;
    public static EditText dateN;
    public static EditText lieuN;
    public static LinearLayout btnSave;


    public static void clickSave(){
        if (CntEleveNew.classeIsFull() && CntEleveNew.fieldsIsFull()){
            TviEleveNew.save();
            TviEleveNew.clean_fields();
        }
        else
            Toast.makeText(context, "champs vides ou incorectes", Toast.LENGTH_LONG).show();
    }


}
