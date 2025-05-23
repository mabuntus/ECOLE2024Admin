package mabuntu.ecole.ecole2024;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import mabuntu.ecole.ecole2024.event.EvtEleveNew;
import mabuntu.ecole.ecole2024.model_table.TDb;
import mabuntu.ecole.ecole2024.tools.TImg;
import mabuntu.ecole.ecole2024.tools.TStrList;
import mabuntu.tombolia.pressing.R;


public class EleveNew extends AppCompatActivity {

    private Context context;
    private AutoCompleteTextView classe;
    private ImageView photo;
    private EditText prenom;
    private EditText nom;
    private EditText sexe;
    private EditText dateN;
    private EditText lieuN;
    private LinearLayout btnSave;


    private final int A_OK = -1;
    private final int A_IMG = 2;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eleve_new);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        EvtEleveNew.context = context = getApplicationContext();
        init_view();
        init_data();
        init_event();
    }

    private void init_view(){
        classe = EvtEleveNew.classe = findViewById(R.id.eleve_new_classe);
        photo = EvtEleveNew.photo = findViewById(R.id.eleve_new_photo);
        prenom = EvtEleveNew.prenom = findViewById(R.id.eleve_new_prenom);
        nom = EvtEleveNew.nom = findViewById(R.id.eleve_new_nom);
        sexe = EvtEleveNew.sexe = findViewById(R.id.eleve_new_sexe);
        dateN = EvtEleveNew.dateN = findViewById(R.id.eleve_new_dateN);
        lieuN = EvtEleveNew.lieuN = findViewById(R.id.eleve_new_lieuN);
        btnSave = EvtEleveNew.btnSave = findViewById(R.id.eleve_new_btnSave);
    }
    private void init_data(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_item, TStrList.classe);
        classe.setThreshold(1);
        classe.setAdapter(adapter);
    }

    private void init_event(){
        photo.setOnClickListener(v ->  clickPhoto());
        btnSave.setOnClickListener(v -> EvtEleveNew.clickSave());
    }
    private void clickPhoto(){
        try {
            startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), A_IMG);
        } catch (Exception e){
            Toast.makeText(context, "Camera echec", Toast.LENGTH_LONG).show();
        }
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == A_OK) {
                Bundle bundle = data.getExtras();
                Bitmap bitmap =(Bitmap)bundle.get("data");
                photo.setImageBitmap(TImg.scale(bitmap));
            }
        } catch (Exception e) {
            Toast.makeText(context, "image erreur", Toast.LENGTH_LONG).show();
        }
    }

    @Override protected void onResume() {
        super.onResume();
        //fill_eleve_data("CP1", TInitData.cp1_eleve_kalla_bangoura);
    }
    private void fill_eleve_data(String strClasse, String[] tabData){
        classe.setText(strClasse);
        for(String val : tabData){
            String[] tab = val.split(TDb.col);
            String sprenom = tab[0];
            String snom = tab[1];
            String ssexe = tab[2];

            prenom.setText(sprenom);
            nom.setText(snom);
            sexe.setText(ssexe);
            EvtEleveNew.clickSave();
        }
    }


}
