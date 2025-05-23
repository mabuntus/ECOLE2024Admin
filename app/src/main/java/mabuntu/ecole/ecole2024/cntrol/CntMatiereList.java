package mabuntu.ecole.ecole2024.cntrol;

import static mabuntu.ecole.ecole2024.event.EvtMatiereList.*;

public class CntMatiereList {

    public static boolean classeIsFull(){
        return TCntrol.isFull(classe);
    }

}
