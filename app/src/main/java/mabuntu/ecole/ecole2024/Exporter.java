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

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import mabuntu.ecole.ecole2024.event.EvtExporter;
import mabuntu.tombolia.pressing.R;


public class Exporter extends AppCompatActivity {

    private Context context;
    private EditText tel;
    private EditText pwd;
    private TextView btnExporter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exporter);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        context = EvtExporter.context = Exporter.this;
        EvtExporter.activity = this;
        init_view();
        init_data();
        init_event();
    }

    private void init_view(){
        EvtExporter.tel = tel = findViewById(R.id.export_tel);
        EvtExporter.pwd = pwd = findViewById(R.id.export_pwd);
        EvtExporter.btnExporter = btnExporter = findViewById(R.id.export_btn_synchronised);
    }
    private void init_data(){ }

    private void init_event(){
        EvtExporter.btnExporter.setOnClickListener(v -> EvtExporter.clickExporter());
    }


}
