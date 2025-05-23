package mabuntu.ecole.ecole2024.cntrol;


import static mabuntu.ecole.ecole2024.event.EvtBulletin.*;

import mabuntu.ecole.ecole2024.model_table.MEleve;
import mabuntu.ecole.ecole2024.tools.TStrList;

public class CntBulletin {

    public static boolean fieldsIsFull(){
        return TCntrol.isFull(classe, trimestre);
    }

    public static boolean classeHasEleves(){
        String sclasse = classe.getText().toString();
        MEleve[] eleves = MEleve.fromClasse(context, sclasse);
        return eleves != null;
    }
    public static boolean boxIsFull(){
        return boxContent.getChildCount() > 0;
    }

}
