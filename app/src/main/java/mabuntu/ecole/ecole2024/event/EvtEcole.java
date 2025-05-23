package mabuntu.ecole.ecole2024.event;

import android.content.Context;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import mabuntu.ecole.ecole2024.cntrol.CntEcole;
import mabuntu.ecole.ecole2024.tvi.TviEcole;

public class EvtEcole {

    public static Context context;
    public static ImageView photo;
    public static EditText mepua;
    public static EditText ire;
    public static EditText dce;
    public static EditText pays;
    public static EditText devise;
    public static EditText ecole;
    public static EditText slogan;
    public static EditText etablissement;
    public static EditText cycle;
    public static EditText adresse;
    public static EditText telephone;
    public static LinearLayout btnSave;

    public static String code_ecole;

    public static void clickSave(){
        if (CntEcole.fields_isFull()){
            TviEcole.save();
            Toast.makeText(context, "SUCCES ENREGISTRER", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(context, "champs vide", Toast.LENGTH_LONG).show();
    }

}
