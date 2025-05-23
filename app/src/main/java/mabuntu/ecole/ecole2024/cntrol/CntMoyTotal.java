package mabuntu.ecole.ecole2024.cntrol;


import mabuntu.ecole.ecole2024.model_table.MEcole;
import mabuntu.ecole.ecole2024.model_table.MEleve;
import mabuntu.ecole.ecole2024.model_table.MMatiere;

import static mabuntu.ecole.ecole2024.event.EvtMoyTotal.*;
public class CntMoyTotal {

    public static boolean boxIsFull() {
        return boxContent.getChildCount() > 0;
    }

    public static boolean ecoleIsFull(){
        MEcole mecole = MEcole.fromCode(context);
        return mecole != null;
    }

    public static boolean classeIsFull(){
        String sclasse = classe.getText().toString();
        MMatiere[] matieres = MMatiere.fromClasse(context, sclasse);
        if (matieres == null)
            return false;

        MEleve[] mEleves = MEleve.fromClasse(context, sclasse);
        if (mEleves == null)
            return false;
        return true;
    }

}
