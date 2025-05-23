package mabuntu.ecole.ecole2024;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.provider.MediaStore;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import mabuntu.ecole.ecole2024.cntrol.CntMenuList;
import mabuntu.ecole.ecole2024.event.EvtMenuList;
import mabuntu.ecole.ecole2024.tools.FileExtern;
import mabuntu.ecole.ecole2024.tvi.Synchronise;
import mabuntu.ecole.ecole2024.tvi.TviMenuList;
import mabuntu.tombolia.pressing.R;

public class MenuList extends AppCompatActivity {

    private Context context;
    private TextView eleveNew;
    private TextView eleveList;
    private TextView matiereNew;
    private TextView matiereList;
    private TextView note;
    private TextView bulletin;
    private TextView statistique;
    private TextView moyTotal;
    private TextView apropos;
    private TextView ecole;
    private TextView synchroShare;
    private TextView synchroSynchro;
    private TextView imported;
    private TextView exported;

    private final int A_OK = -1;
    private final int A_FILE = 2;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_list);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        context = EvtMenuList.context = getApplicationContext();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        init_view();
        init_fillView();
        init_event();
    }


    private void init_view(){
        matiereNew = EvtMenuList.matiereNew = findViewById(R.id.menu_matiere_new);
        matiereList = EvtMenuList.matiereList = findViewById(R.id.menu_matiere_list);
        eleveNew = EvtMenuList.eleveNew = findViewById(R.id.menu_eleve_new);
        eleveList = EvtMenuList.eleveList = findViewById(R.id.menu_eleve_list);
        note = EvtMenuList.note = findViewById(R.id.menu_note);
        bulletin = EvtMenuList.bulletin = findViewById(R.id.menu_bulletin);
        statistique = EvtMenuList.statistique = findViewById(R.id.menu_statistique);
        moyTotal = EvtMenuList.moyTotal = findViewById(R.id.menu_moy_annuelle);
        apropos = EvtMenuList.apropos = findViewById(R.id.menu_apropos);
        ecole = EvtMenuList.ecole = findViewById(R.id.menu_ecole);
        synchroShare = EvtMenuList.synchroShare = findViewById(R.id.menu_synchro_share);
        synchroSynchro = EvtMenuList.synchroSynchro = findViewById(R.id.menu_synchro_receiv);
        imported = EvtMenuList.imported = findViewById(R.id.menu_imported);
        exported = EvtMenuList.exported = findViewById(R.id.menu_exported);
    }
    private void init_fillView(){
    }
    private void init_event(){
        EvtMenuList.matiereNew.setOnClickListener(v -> EvtMenuList.clickMatiereNew());
        EvtMenuList.matiereList.setOnClickListener(v -> EvtMenuList.clickMatiereList());
        EvtMenuList.eleveNew.setOnClickListener(v -> EvtMenuList.clickEleveNew());
        EvtMenuList.eleveList.setOnClickListener(v -> EvtMenuList.clickEleveList());
        EvtMenuList.note.setOnClickListener(v -> EvtMenuList.clickNote());
        EvtMenuList.bulletin.setOnClickListener(v -> EvtMenuList.clickBulletin());
        EvtMenuList.statistique.setOnClickListener(v -> EvtMenuList.clickStatistique());
        EvtMenuList.moyTotal.setOnClickListener(v -> EvtMenuList.clickMoyTotal());
        EvtMenuList.ecole.setOnClickListener(v -> EvtMenuList.clickEcole());
        EvtMenuList.apropos.setOnClickListener(v -> EvtMenuList.clickApropos());
        EvtMenuList.synchroShare.setOnClickListener(v -> clickSynchroShare());
        EvtMenuList.synchroSynchro.setOnClickListener(v -> clickSynchroSynchro());
        EvtMenuList.exported.setOnClickListener(v -> EvtMenuList.clickImported());
        EvtMenuList.imported.setOnClickListener(v -> EvtMenuList.clickExported());
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == A_OK) {
                Uri uri = data.getData();
                String file_data = CntMenuList.file_is_valid(uri);

                if (file_data != null){
                    Synchronise.run(file_data);
                    Toast.makeText(context, "donnees synchroniser", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(context, "fichier incorrect", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, "fichier erreur", Toast.LENGTH_LONG).show();
        }
    }

    private void clickSynchroSynchro(){
        try {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, A_FILE);
        } catch (Exception e){
            Toast.makeText(context, "fichier echec", Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("WrongConstant")
    private void clickSynchroShare(){
        if (CntMenuList.hasEleve()) {
            String data = TviMenuList.get_files_contents();
            String fname = "ecole.infos.ecole";
            File file = FileExtern.save(fname, data);

            if (file != null) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.setPackage("com.android.bluetooth");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                startActivity(Intent.createChooser(intent, "share file"));
            }
        }
        else
            Toast.makeText(context, "eleve absent", Toast.LENGTH_LONG).show();

    }

}
