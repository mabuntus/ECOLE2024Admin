package mabuntu.ecole.ecole2024;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import mabuntu.ecole.ecole2024.event.EvtNote;
import mabuntu.ecole.ecole2024.tools.TStrList;
import mabuntu.tombolia.pressing.R;


public class Note extends AppCompatActivity {

    private Context context;
    private AutoCompleteTextView classe;
    private AutoCompleteTextView trimestre;
    private LinearLayout boxContent;
    private EditText idElv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        context = EvtNote.context = getApplicationContext();
        init_view();
        init_data();
        init_event();
    }

    private void init_view(){
        classe = EvtNote.classe = findViewById(R.id.note_classe);
        trimestre = EvtNote.trimestre = findViewById(R.id.note_trimestre);
        boxContent = EvtNote.boxContent = findViewById(R.id.note_box_content);
        idElv = EvtNote.idElv = findViewById(R.id.note_idelv);
    }

    private void init_data(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_item, TStrList.classe);
        classe.setThreshold(1);
        classe.setAdapter(adapter);
    }
    private void init_event(){
        classe.setOnItemClickListener((parent, view, position, id) -> EvtNote.changeClasse());
        trimestre.setOnItemClickListener((parent, view, position, id) -> EvtNote.changeTrimestre());
    }

}
