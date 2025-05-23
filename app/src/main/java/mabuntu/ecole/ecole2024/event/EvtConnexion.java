package mabuntu.ecole.ecole2024.event;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mabuntu.ecole.ecole2024.Exporter;
import mabuntu.ecole.ecole2024.Importer;
import mabuntu.ecole.ecole2024.MenuList;
import mabuntu.ecole.ecole2024.cntrol.CntConnexion;

public class EvtConnexion {

    public static Context context;
    public static EditText codeApp;
    public static EditText codeEcole;
    public static TextView btnConnexion;


    public static void clickConnexion(){
        if (CntConnexion.isAdmin()) {
            Intent intent = new Intent(context, MenuList.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            ((Activity) context).finish();
        }
        else
            Toast.makeText(context, "login ou mot de passe incorrect", Toast.LENGTH_LONG).show();
    }

}

