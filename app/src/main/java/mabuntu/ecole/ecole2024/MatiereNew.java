package mabuntu.ecole.ecole2024;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import mabuntu.ecole.ecole2024.event.EvtMatiereNew;
import mabuntu.ecole.ecole2024.tools.TStrList;
import mabuntu.tombolia.pressing.R;

public class MatiereNew extends AppCompatActivity {

    private Context context;
    private AutoCompleteTextView classe;
    private EditText matiere;
    private EditText coef;
    private TextView btnAdd;
    private LinearLayout boxContent;
    private LinearLayout btnSave;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matiere_new);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        context = EvtMatiereNew.context = getApplicationContext();
        init_view();
        //init_data();
        init_event();
    }

    private void init_view(){
        classe = EvtMatiereNew.classe = findViewById(R.id.matiere_new_classe);
        matiere = EvtMatiereNew.matiere = findViewById(R.id.matiere_new_name);
        coef = EvtMatiereNew.coef = findViewById(R.id.matiere_new_coef);
        btnAdd = EvtMatiereNew.btnAdd = findViewById(R.id.matiere_new_btnAdd);
        boxContent = EvtMatiereNew.boxContent = findViewById(R.id.matiere_new_box);
        btnSave = EvtMatiereNew.btnSave = findViewById(R.id.matiere_new_btnSave);
    }
    private void init_data(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_item, TStrList.classe);
        classe.setThreshold(1);
        classe.setAdapter(adapter);
    }
    private void init_event(){
        btnAdd.setOnClickListener(v -> EvtMatiereNew.clickAdd());
        btnSave.setOnClickListener(v -> EvtMatiereNew.clickSave());
    }


}
