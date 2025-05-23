package mabuntu.ecole.ecole2024.tvi;

import static mabuntu.ecole.ecole2024.event.EvtEleveList.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import mabuntu.ecole.ecole2024.EleveModify;
import mabuntu.ecole.ecole2024.event.EvtEleveList;
import mabuntu.ecole.ecole2024.model_table.MEleve;
import mabuntu.ecole.ecole2024.tools.FILE;
import mabuntu.ecole.ecole2024.tools.Fil;
import mabuntu.ecole.ecole2024.tools.TNum;
import mabuntu.tombolia.pressing.R;

public class TviEleveList {

    public static void clean_box(){
        boxContent.removeAllViews();
    }

    public static void fill_box(){
        String sclasse = classe.getText().toString();
        MEleve[] mEleves = MEleve.fromClasse(context, sclasse);
        if (mEleves == null) return;

        for(int i = 0; i < mEleves.length; i++){
            int no = i + 1;
            MEleve mElv = mEleves[i];
            fill_box_item(no, mElv);
        }
    }
    @SuppressLint("ResourceAsColor")
    private static void fill_box_item(int no, MEleve obj){
        LinearLayout box = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.lay_eleve, null);
        TextView vno = box.findViewById(R.id.lay_eleve_new_no);
        LinearLayout boxElv = box.findViewById(R.id.lay_eleve_new_box);
        TextView vprenom = box.findViewById(R.id.lay_eleve_new_prenom);
        TextView vnom = box.findViewById(R.id.lay_eleve_new_nom);
        TextView vmatricule = box.findViewById(R.id.lay_eleve_new_matricule);
        TextView vbtnDetail = box.findViewById(R.id.lay_eleve_new_btnDetail);
        TextView vbtnDelete = box.findViewById(R.id.lay_eleve_new_btnDelete);
        TextView vbtnModify = box.findViewById(R.id.lay_eleve_new_btnModify);

        vno.setText(TNum.iS(no));
        vprenom.setText(obj.prenom);
        vnom.setText(obj.nom);
        vmatricule.setText(obj.matricule);

        vbtnDetail.setOnClickListener(v -> {
            if (boxElv.getChildCount() == 2) {
                boxElv.setVisibility(View.VISIBLE);
                LinearLayout boxItem = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.lay_eleve_detail, null);
                ImageView photo = EvtEleveList.photo = boxItem.findViewById(R.id.lay_eleve_new_detail_photo);
                TextView vdtN = boxItem.findViewById(R.id.lay_eleve_new_detail_dateN);
                TextView vltN = boxItem.findViewById(R.id.lay_eleve_new_detail_lieuN);

                Fil fil = new Fil(context);
                photo.setImageBitmap(fil.file2bitmap_elv(FILE.eleve_photo(obj.code), Tvi.str(classe)));
                vdtN.setText(obj.dateN);
                vltN.setText(obj.lieuN);
                photo.setOnClickListener(vv -> {
                    EvtEleveList.codeElv = obj.code;
                    EvtEleveList.photo = photo;
                    clickPhoto();
                });
                boxElv.addView(boxItem);
            }
        });

        vbtnModify.setOnClickListener(v -> {
            Fil fil = new Fil(context);
            fil.put(FILE.data_elv_code, obj.code);
            Intent intent = new Intent(context, EleveModify.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        vbtnDelete.setOnClickListener(v -> {
            if (context.deleteFile(FILE.eleve_photo(obj.code))) {
                if (MEleve.delFromCode(context, obj.code))
                    boxContent.removeView(box);
            }
        });

        if (obj.sexe.contentEquals("0")) {
            vprenom.setTextColor(Color.RED);
            vnom.setTextColor(Color.RED);
        }

        boxContent.addView(box);
    }

    // change eleve photo
    static private final int A_IMG = 2;
    static private void clickPhoto(){
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ((Activity) activity).startActivityForResult(intent, A_IMG);
        } catch (Exception e){
            Log.println(Log.DEBUG, "photo", e.getMessage());
            Toast.makeText(context, "Camera echec", Toast.LENGTH_LONG).show();
        }
    }

}
