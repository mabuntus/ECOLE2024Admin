package mabuntu.ecole.ecole2024.event;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mabuntu.ecole.ecole2024.cntrol.CntImporter;
import mabuntu.ecole.ecole2024.tvi.TviImporter;

public class EvtImporter {

    public static Context context;
    public static Activity activity;
    public static EditText tel;
    public static EditText pwd;
    public static TextView btnImporter;


    public static void clickImporter(){
        if (CntImporter.fields_is_Okay()) {
            TviImporter.imported();
        }
        else {
            Toast.makeText(context, "champs vide", Toast.LENGTH_LONG).show();
        }
    }

}
