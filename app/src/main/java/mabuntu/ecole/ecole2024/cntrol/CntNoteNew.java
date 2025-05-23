package mabuntu.ecole.ecole2024.cntrol;

import static mabuntu.ecole.ecole2024.event.EvtNote.*;

import mabuntu.ecole.ecole2024.model_table.MEleve;
import mabuntu.ecole.ecole2024.model_table.MMatiere;

public class CntNoteNew {

    public static boolean fields_isFull(){
        return TCntrol.isFull(classe, trimestre);
    }

    public static boolean classeHasEleve(){
        String sclasse = classe.getText().toString();
        MEleve[] eleves = MEleve.fromClasse(context, sclasse);
        return eleves != null && eleves.length > 0;
    }
    public static boolean classeHasMatiere(){
        String sclasse = classe.getText().toString();
        MMatiere[] matieres = MMatiere.fromClasse(context, sclasse);
        return matieres != null && matieres.length > 0;
    }


}

