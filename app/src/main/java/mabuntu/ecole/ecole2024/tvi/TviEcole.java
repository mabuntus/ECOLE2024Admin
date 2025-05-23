package mabuntu.ecole.ecole2024.tvi;

import static mabuntu.ecole.ecole2024.event.EvtEcole.*;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import mabuntu.ecole.ecole2024.event.EvtEcole;
import mabuntu.ecole.ecole2024.model_table.MEcole;
import mabuntu.ecole.ecole2024.tools.Fil;
import mabuntu.ecole.ecole2024.tools.TImg;

public class TviEcole {

    public static void full_box(MEcole ecole){
        code_ecole = ecole.code;
        photo.setImageBitmap(MEcole.get_logo(context));
        mepua.setText(ecole.mepua);
        ire.setText(ecole.ire);
        dce.setText(ecole.dce);
        pays.setText(ecole.pays);
        devise.setText(ecole.devise);
        EvtEcole.ecole.setText(ecole.ecole);
        slogan.setText(ecole.slogan);
        etablissement.setText(ecole.etablissement);
        cycle.setText(ecole.cycle);
        adresse.setText(ecole.addresse);
        telephone.setText(ecole.telephone);
    }

    public static void save(){
        BitmapDrawable drawable = (BitmapDrawable)photo.getDrawable();
        MEcole.set_logo(context, TImg.scale(drawable.getBitmap()));

        MEcole ecole = new MEcole();
        ecole.code = code_ecole;
        ecole.mepua = mepua.getText().toString();
        ecole.ire = ire.getText().toString();
        ecole.dce = dce.getText().toString();
        ecole.pays = pays.getText().toString();
        ecole.devise = devise.getText().toString();
        ecole.ecole = EvtEcole.ecole.getText().toString();
        ecole.slogan = slogan.getText().toString();
        ecole.etablissement = etablissement.getText().toString();
        ecole.cycle = cycle.getText().toString();
        ecole.addresse = adresse.getText().toString();
        ecole.telephone = telephone.getText().toString();
        MEcole.updt(context, ecole);
    }


}
