package mabuntu.ecole.ecole2024;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import mabuntu.ecole.ecole2024.event.EvtImporter;
import mabuntu.tombolia.pressing.R;


public class Importer extends AppCompatActivity {

    private Context context;
    private EditText tel;
    private EditText pwd;
    private TextView btnImporter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.importer);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        context = EvtImporter.context = this;
        EvtImporter.activity = this;
        init_view();
        init_data();
        init_event();
    }

    private void init_view(){
        EvtImporter.tel = tel = findViewById(R.id.back_tel);
        EvtImporter.pwd = pwd = findViewById(R.id.back_pwd);
        EvtImporter.btnImporter = btnImporter = findViewById(R.id.back_btn_synchronised);
    }
    private void init_data(){ }

    private void init_event(){
        EvtImporter.btnImporter.setOnClickListener(v -> EvtImporter.clickImporter());
    }


}
