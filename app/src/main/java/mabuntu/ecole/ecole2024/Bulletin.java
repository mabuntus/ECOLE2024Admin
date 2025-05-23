package mabuntu.ecole.ecole2024;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import mabuntu.ecole.ecole2024.event.EvtBulletin;
import mabuntu.ecole.ecole2024.event.EvtConnexion;
import mabuntu.ecole.ecole2024.tools.FILE;
import mabuntu.ecole.ecole2024.tools.Fil;
import mabuntu.ecole.ecole2024.tools.TStrList;
import mabuntu.tombolia.pressing.R;


public class Bulletin extends AppCompatActivity {

    private Context context;
    private AutoCompleteTextView classe;
    private AutoCompleteTextView trimestre;
    private LinearLayout boxContent;
    private TextView btnPrint;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bulletin);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        context = EvtBulletin.context = getApplicationContext();
        init_view();
        init_data();
        init_event();
    }

    private void init_view(){
        classe = EvtBulletin.classe = findViewById(R.id.bulletin_classe);
        trimestre = EvtBulletin.trimestre = findViewById(R.id.bulletin_trimestre);
        boxContent = EvtBulletin.boxContent = findViewById(R.id.bulletin_box_content);
        btnPrint = EvtBulletin.btnPrint = findViewById(R.id.bulletin_print_btn);
    }
    private void init_data(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_item, TStrList.classe);
        classe.setThreshold(1);
        classe.setAdapter(adapter);

        adapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_item, TStrList.trimestre);
        trimestre.setThreshold(1);
        trimestre.setAdapter(adapter);
    }
    private void init_event(){
        classe.setOnItemClickListener((parent, view, position, id) -> EvtBulletin.selectClasse());
        trimestre.setOnItemClickListener((parent, view, position, id) -> EvtBulletin.selectTrimestre());
        btnPrint.setOnClickListener(v -> EvtBulletin.clickPrint());
    }


}
