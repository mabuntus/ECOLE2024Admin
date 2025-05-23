package mabuntu.ecole.ecole2024;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import mabuntu.ecole.ecole2024.event.EvtMoyTotal;
import mabuntu.ecole.ecole2024.tools.TStrList;
import mabuntu.tombolia.pressing.R;


public class MoyTotal extends AppCompatActivity {

    private Context context;
    private AutoCompleteTextView classe;
    private TextView btnPrint;
    private LinearLayout boxContent;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moyenne_total);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        context = EvtMoyTotal.context = getApplicationContext();
        init_view();
        init_data();
        init_event();
    }

    private void init_view(){
        classe = EvtMoyTotal.classe = findViewById(R.id.moyenne_total_classe);
        boxContent = EvtMoyTotal.boxContent = findViewById(R.id.moyenne_total_box_content);
        btnPrint = EvtMoyTotal.btnPrint = findViewById(R.id.moyenne_total_print_btn);
    }
    private void init_data(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_item, TStrList.classe);
        classe.setThreshold(1);
        classe.setAdapter(adapter);
    }
    private void init_event(){
        classe.setOnItemClickListener((parent, view, position, id) -> EvtMoyTotal.selectClasse());
        btnPrint.setOnClickListener(v -> EvtMoyTotal.clickPrint());
    }


}
