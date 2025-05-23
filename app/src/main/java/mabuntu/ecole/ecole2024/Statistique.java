package mabuntu.ecole.ecole2024;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import mabuntu.ecole.ecole2024.event.EvtStatistique;
import mabuntu.ecole.ecole2024.tools.TStrList;
import mabuntu.tombolia.pressing.R;


public class Statistique extends AppCompatActivity {

    private Context context;
    private AutoCompleteTextView classe;
    private AutoCompleteTextView trimestre;
    private LinearLayout boxContent;
    private LinearLayout boxBtnPrint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistique);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        context = EvtStatistique.context = getApplicationContext();
        init_view();
        init_data();
        init_event();
    }
    private void init_view(){
        classe = EvtStatistique.classe = findViewById(R.id.statistique_classe);
        trimestre = EvtStatistique.trimestre = findViewById(R.id.statistique_trimestre);
        boxContent = EvtStatistique.boxContent = findViewById(R.id.statistique_box);
        boxBtnPrint = EvtStatistique.boxBtnPrint = findViewById(R.id.statistique_btn_print);
    }
    private void init_data(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_item, TStrList.classe);
        classe.setThreshold(1);
        classe.setAdapter(adapter);
    }
    private void init_event(){
        classe.setOnItemClickListener((parent, view, position, id) -> EvtStatistique.changeClasse());
        trimestre.setOnItemClickListener((parent, view, position, id) -> EvtStatistique.changeTrimestre());
        boxBtnPrint.setOnClickListener(v -> EvtStatistique.clickPrint());
    }


}
