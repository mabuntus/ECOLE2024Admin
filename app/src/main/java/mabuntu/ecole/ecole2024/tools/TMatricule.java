package mabuntu.ecole.ecole2024.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import java.util.Random;

import mabuntu.ecole.ecole2024.model_table.MEleve;

public class TMatricule {

    public static String eleve(Context context){
        Random rand = new Random();

        while (true){
            Integer code = rand.nextInt(10000);
            String matricule = String.format("ELV-ADMIN-%s", code.toString());

            MEleve elv = MEleve.fromMatricule(context, matricule);
            if (elv == null)
                return matricule;
        }
    }

    @SuppressLint("DefaultLocale")
    public static String eleve_matricule(Context context){
        Fil fil = new Fil(context);
        String val = fil.get(FILE.eleve_matricule);
        int code = TNum.h2i(val) + 1;
        String num = TNum.iS(code);
        fil.put(FILE.eleve_matricule, num);
        return String.format("ELV%s", num);
    }

}
