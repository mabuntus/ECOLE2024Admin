package mabuntu.ecole.ecole2024;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import mabuntu.ecole.ecole2024.event.EvtMatiereList;
import mabuntu.ecole.ecole2024.tools.TStrList;
import mabuntu.ecole.ecole2024.tvi.TviMatiereList;
import mabuntu.tombolia.pressing.R;

public class MatiereList extends AppCompatActivity {

    private Context context;
    private AutoCompleteTextView classe;
    private LinearLayout boxContent;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matiere_list);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        context = EvtMatiereList.context = getApplicationContext();
        init_view();
        init_event();
    }

    private void init_view(){
        classe = EvtMatiereList.classe = findViewById(R.id.matiere_list_classe);
        boxContent = EvtMatiereList.boxContent = findViewById(R.id.matiere_list_box);
    }
    private void init_data(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_item, TStrList.classe);
        classe.setThreshold(1);
        classe.setAdapter(adapter);
    }
    private void init_event(){
        classe.setOnItemClickListener((parent, view, position, id) -> EvtMatiereList.changeClasse());
    }

}
