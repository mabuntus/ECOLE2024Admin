package mabuntu.ecole.ecole2024;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import mabuntu.ecole.ecole2024.event.EvtEleveList;
import mabuntu.ecole.ecole2024.tools.FILE;
import mabuntu.ecole.ecole2024.tools.Fil;
import mabuntu.ecole.ecole2024.tools.TStrList;
import mabuntu.tombolia.pressing.R;


public class EleveList extends AppCompatActivity {

    private Context context;
    private AutoCompleteTextView classe;
    private LinearLayout boxContent;
    private final int A_OK = -1;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eleve_list);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        EvtEleveList.activity = this;
        EvtEleveList.context = context = getApplicationContext();
        init_view();
        init_data();
        init_event();
    }

    private void init_view(){
        classe = EvtEleveList.classe = findViewById(R.id.eleve_list_classe);
        boxContent = EvtEleveList.boxContent = findViewById(R.id.eleve_list_box);
    }
    private void init_data(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_item, TStrList.classe);
        classe.setThreshold(1);
        classe.setAdapter(adapter);
    }

    private void init_event(){
        classe.setOnItemClickListener((parent, view, position, id) -> EvtEleveList.changeClasse());
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == A_OK) {
                Bundle bundle = data.getExtras();
                Bitmap bitmap =(Bitmap)bundle.get("data");
                String codeElv = EvtEleveList.codeElv;
                ImageView photo = EvtEleveList.photo;
                photo.setImageBitmap(bitmap);
                Fil fil = new Fil(context);
                fil.bitmap2file(bitmap, FILE.eleve_photo(codeElv));
            }
        } catch (Exception e) {
            Toast.makeText(context, "image erreur", Toast.LENGTH_LONG).show();
        }
    }

    @Override protected void onResume() {
        super.onResume();
        EvtEleveList.changeClasse();
    }

}
