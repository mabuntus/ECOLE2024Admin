package mabuntu.ecole.ecole2024.event;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mabuntu.ecole.ecole2024.cntrol.CntExporter;
import mabuntu.ecole.ecole2024.tvi.TviExporter;

public class EvtExporter {

    public static Context context;
    public static Activity activity;
    public static EditText tel;
    public static EditText pwd;
    public static TextView btnExporter;


    public static void clickExporter(){
        if (CntExporter.fields_is_Okay()) {
            TviExporter.exported();
        }
        else {
            Toast.makeText(context, "champs vide", Toast.LENGTH_LONG).show();
        }
    }

}
