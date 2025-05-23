package mabuntu.ecole.ecole2024.cntrol;

import static mabuntu.ecole.ecole2024.event.EvtEleveModify.*;

public class CntEleveModify {

    public static boolean fieldsIsFull(){
        return TCntrol.isFull(prenom, nom, sexe, dateN, lieuN);
    }

}
