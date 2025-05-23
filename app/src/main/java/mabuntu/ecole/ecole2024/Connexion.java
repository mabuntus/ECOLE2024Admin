package mabuntu.ecole.ecole2024;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import mabuntu.ecole.ecole2024.event.EvtConnexion;
import mabuntu.ecole.ecole2024.model_table.MLogin;
import mabuntu.ecole.ecole2024.tools.FILE;
import mabuntu.ecole.ecole2024.tools.Fil;
import mabuntu.ecole.ecole2024.tools.TStrList;
import mabuntu.tombolia.pressing.R;


public class Connexion extends AppCompatActivity {

    private Context context;
    private EditText codeApp;
    private EditText codeEcole;
    private TextView btnConnexion;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        EvtConnexion.context = context = Connexion.this;
        if (!Fil.exists(context, FILE.eleve_matricule))
            Fil.put(context, FILE.eleve_matricule, "1");

        init_verify();
        init_view();
        init_data();
        init_event();
    }

    private void init_verify(){
        MLogin mlogin = MLogin.get(context);
        if (mlogin != null && mlogin.isLogin()){

            Intent intent = new Intent(context, MenuList.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            this.finish();
        }
    }
    private void init_view(){
        EvtConnexion.codeApp = codeApp = findViewById(R.id.connexion_code_application);
        EvtConnexion.codeEcole = codeEcole = findViewById(R.id.connexion_code_ecole);
        EvtConnexion.btnConnexion = btnConnexion = findViewById(R.id.connexion_btn);
    }

    private void init_data(){
    }

    private void init_event(){
        EvtConnexion.btnConnexion.setOnClickListener(v -> EvtConnexion.clickConnexion());
    }


}
