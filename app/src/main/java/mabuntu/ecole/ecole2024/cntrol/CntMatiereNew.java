package mabuntu.ecole.ecole2024.cntrol;

import static mabuntu.ecole.ecole2024.event.EvtMatiereNew.*;

import mabuntu.ecole.ecole2024.tools.TStrList;

public class CntMatiereNew {

    public static boolean matiere_and_coef_isFull(){
        return TCntrol.isFull(matiere, coef);
    }

    public static boolean classeIsFull(){
        return TCntrol.classeIsFull(classe);
    }

    public static boolean boxIsFull(){
        return boxContent.getChildCount() > 0;
    }


}
