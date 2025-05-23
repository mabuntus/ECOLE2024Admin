package mabuntu.ecole.ecole2024.cntrol;

import static mabuntu.ecole.ecole2024.event.EvtEleveNew.*;

public class CntEleveNew {

    public static boolean classeIsFull(){
        return TCntrol.classeIsFull(classe);
    }

    public static boolean fieldsIsFull(){
        return TCntrol.isFull(prenom, nom, sexe, dateN, lieuN);
    }


}
