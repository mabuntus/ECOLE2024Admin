package mabuntu.ecole.ecole2024.tvi;

import static mabuntu.ecole.ecole2024.event.EvtEleveModify.*;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import mabuntu.ecole.ecole2024.model_table.MEleve;
import mabuntu.ecole.ecole2024.tools.FILE;
import mabuntu.ecole.ecole2024.tools.Fil;
import mabuntu.ecole.ecole2024.tools.TImg;
import mabuntu.ecole.ecole2024.tools.TStrList;

public class TviEleveModify {

    public static void save(){
        Fil fil = new Fil(context);

        elv.prenom = prenom.getText().toString();
        elv.nom = nom.getText().toString();
        elv.sexe = TStrList.sexeId(sexe);
        elv.dateN = dateN.getText().toString();
        elv.lieuN = lieuN.getText().toString();

        MEleve.updt(context, elv);
        Bitmap bitmap = ((BitmapDrawable)photo.getDrawable()).getBitmap();
        bitmap = TImg.scale(bitmap);
        fil.bitmap2file(bitmap, FILE.eleve_photo(elv.code));
    }

}

