package mabuntu.ecole.ecole2024.cntrol;

import static mabuntu.ecole.ecole2024.event.EvtStatistique.*;
public class CntStatistique {

    public static boolean fields_isFull(){
        return TCntrol.isFull(classe, trimestre);
    }
}
