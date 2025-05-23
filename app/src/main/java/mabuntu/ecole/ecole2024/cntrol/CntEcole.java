package mabuntu.ecole.ecole2024.cntrol;

import static mabuntu.ecole.ecole2024.event.EvtEcole.*;
public class CntEcole {

    public static boolean fields_isFull(){
        return TCntrol.isFull(mepua, ire, dce, devise, ecole, slogan, etablissement, cycle, adresse, telephone);
    }


}
