package mabuntu.ecole.ecole2024.event;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mabuntu.ecole.ecole2024.cntrol.CntEleveModify;
import mabuntu.ecole.ecole2024.model_table.MEleve;
import mabuntu.ecole.ecole2024.tvi.TviEleveModify;
import mabuntu.ecole.ecole2024.tvi.TviEleveNew;

public class EvtEleveModify {

    public static Context context;
    public static Activity activity;
    public static ImageView photo;
    public static EditText prenom;
    public static EditText nom;
    public static EditText sexe;
    public static EditText dateN;
    public static EditText lieuN;
    public static TextView btnSave;
    public static MEleve elv;


    public static void clickSave(){
        if (CntEleveModify.fieldsIsFull()){
            TviEleveModify.save();
            activity.finish();
        }
        else
            Toast.makeText(context, "champs vides ou incorectes", Toast.LENGTH_LONG).show();
    }


}
