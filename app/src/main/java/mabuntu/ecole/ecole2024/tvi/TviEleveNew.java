package mabuntu.ecole.ecole2024.tvi;

import static mabuntu.ecole.ecole2024.event.EvtEleveNew.*;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import mabuntu.ecole.ecole2024.model_table.MEleve;
import mabuntu.ecole.ecole2024.tools.FILE;
import mabuntu.ecole.ecole2024.tools.Fil;
import mabuntu.ecole.ecole2024.tools.TCode;
import mabuntu.ecole.ecole2024.tools.TImg;
import mabuntu.ecole.ecole2024.tools.TMatricule;
import mabuntu.ecole.ecole2024.tools.TStrList;
import mabuntu.tombolia.pressing.R;

public class TviEleveNew {

    private static void clean_photo(){
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.profile_white_foreground);
        photo.setImageBitmap(TImg.scale(bitmap));
    }
    public static void clean_fields(){
        clean_photo();
        prenom.setText("");
        nom.setText("");
        sexe.setText("homme");
        dateN.setText("31/12/2000");
        lieuN.setText("Conakry");
    }

    public static void save(){
        String codeElv = TCode.eleve();
        String strClasse = classe.getText().toString();

        save_eleve(codeElv, strClasse);
        save_eleve_photo(codeElv);

    }
    private static void save_eleve(String codeElv, String classe){
        String matricule = TMatricule.eleve(context);
        String sprenom = prenom.getText().toString();
        String snom = nom.getText().toString();
        String ssexe = TStrList.sexeId(sexe);
        String sdtN = dateN.getText().toString();
        String sltN = lieuN.getText().toString();

        MEleve.add(context, codeElv, matricule, classe, sprenom, snom, ssexe, sdtN, sltN);
    }
    private static void save_eleve_photo(String codeElv){
        Fil fil = new Fil(context);
        Bitmap bitmap = ((BitmapDrawable)photo.getDrawable()).getBitmap();
        bitmap = TImg.scale(bitmap);
        fil.bitmap2file(bitmap, FILE.eleve_photo(codeElv));
    }

}
