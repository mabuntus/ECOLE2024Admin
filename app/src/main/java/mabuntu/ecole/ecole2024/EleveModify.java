package mabuntu.ecole.ecole2024;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import mabuntu.ecole.ecole2024.event.EvtEleveModify;
import mabuntu.ecole.ecole2024.model_table.MEleve;
import mabuntu.ecole.ecole2024.tools.FILE;
import mabuntu.ecole.ecole2024.tools.Fil;
import mabuntu.ecole.ecole2024.tools.TStrList;
import mabuntu.ecole.ecole2024.tools.Tools;
import mabuntu.tombolia.pressing.R;

public class EleveModify extends AppCompatActivity {

    private Context context;
    private ImageView photo;
    private EditText prenom;
    private EditText nom;
    private EditText sexe;
    private EditText dateN;
    private EditText lieuN;
    private TextView btnSave;
    private String codeElv;


    private final int A_OK = -1;
    private final int A_IMG = 2;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eleve_modify);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        EvtEleveModify.activity = this;
        EvtEleveModify.context = context = getApplicationContext();
        init_view();
        init_data();
        init_event();
    }

    private void init_view(){
        photo = EvtEleveModify.photo = findViewById(R.id.eleve_modify_photo);
        prenom = EvtEleveModify.prenom = findViewById(R.id.eleve_modify_prenom);
        nom = EvtEleveModify.nom = findViewById(R.id.eleve_modify_nom);
        sexe = EvtEleveModify.sexe = findViewById(R.id.eleve_modify_sexe);
        dateN = EvtEleveModify.dateN = findViewById(R.id.eleve_modify_dateN);
        lieuN = EvtEleveModify.lieuN = findViewById(R.id.eleve_modify_lieuN);
        btnSave = EvtEleveModify.btnSave = findViewById(R.id.eleve_modify_btnSave);
    }
    private void init_data(){
        Fil fil = new Fil(context);
        codeElv = fil.get(FILE.data_elv_code);
        MEleve elv = EvtEleveModify.elv = MEleve.fromCode(context, codeElv);

        photo.setImageBitmap(fil.file2bitmap_elv(FILE.eleve_photo(codeElv), elv.classe));
        prenom.setText(elv.prenom);
        nom.setText(elv.nom);
        sexe.setText(TStrList.sexeName(elv.sexe));
        lieuN.setText(elv.lieuN);
        dateN.setText(elv.dateN);
    }

    private void init_event(){
        photo.setOnClickListener(v ->  clickPhoto());
        btnSave.setOnClickListener(v -> EvtEleveModify.clickSave());
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
                bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
                photo.setImageBitmap(bitmap);
            }
        } catch (Exception e) {
            Toast.makeText(context, "image erreur", Toast.LENGTH_LONG).show();
        }
    }



}
