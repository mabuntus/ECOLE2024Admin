package mabuntu.ecole.ecole2024;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import mabuntu.ecole.ecole2024.event.EvtEcole;
import mabuntu.ecole.ecole2024.model_table.MEcole;
import mabuntu.ecole.ecole2024.tools.Fil;
import mabuntu.ecole.ecole2024.tvi.TviEcole;
import mabuntu.tombolia.pressing.R;

public class Ecole extends AppCompatActivity {

    private Context context;
    private ImageView photo;
    private EditText mepua;
    private EditText ire;
    private EditText dce;
    private EditText pays;
    private EditText devise;
    private EditText ecole;
    private EditText slogan;
    private EditText etablissement;
    private EditText cycle;
    private EditText adresse;
    private EditText telephone;

    private LinearLayout btnSave;

    private final int A_OK = -1;
    private final int A_IMG = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecole);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        context = EvtEcole.context = getApplicationContext();
        init_view();
        init_data();
        init_event();
    }

    private void init_view(){
        photo = EvtEcole.photo = findViewById(R.id.ecole_photo);
        mepua = EvtEcole.mepua = findViewById(R.id.ecole_mepua);
        ire = EvtEcole.ire = findViewById(R.id.ecole_ire);
        dce = EvtEcole.dce = findViewById(R.id.ecole_dce);
        pays = EvtEcole.pays = findViewById(R.id.ecole_pays);
        devise = EvtEcole.devise = findViewById(R.id.ecole_devise);
        ecole = EvtEcole.ecole = findViewById(R.id.ecole_fullname);
        slogan = EvtEcole.slogan = findViewById(R.id.ecole_slogan);
        etablissement = EvtEcole.etablissement = findViewById(R.id.ecole_etablissement);
        cycle = EvtEcole.cycle = findViewById(R.id.ecole_cycle);
        adresse = EvtEcole.adresse = findViewById(R.id.ecole_adresse);
        telephone = EvtEcole.telephone = findViewById(R.id.ecole_telephone);
        btnSave = EvtEcole.btnSave = findViewById(R.id.ecole_btnSave);
    }
    private void init_data(){
        MEcole ecole = MEcole.fromCode(context);
        if (ecole == null) {
            MEcole.create(context);
            MEcole.create_logo(context);
        }
        ecole = MEcole.fromCode(context);
        TviEcole.full_box(ecole);
    }
    private void init_event(){
        photo.setOnClickListener(v -> clickPhoto());
        btnSave.setOnClickListener(v -> EvtEcole.clickSave());
    }

    private void clickPhoto(){
        try {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, A_IMG);
        } catch (Exception e){
            Toast.makeText(context, "Camera echec", Toast.LENGTH_LONG).show();
        }
    }


    @Override protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == A_OK) {
                Uri uriImg = data.getData();
                photo.setImageURI(uriImg);
                /*
                Bundle bundle = data.getExtras();
                Bitmap bitmap =(Bitmap)bundle.get("data");
                photo.setImageBitmap(bitmap);
                 */
            }
        } catch (Exception e) {
            Toast.makeText(context, "image erreur", Toast.LENGTH_LONG).show();
        }
    }


}

