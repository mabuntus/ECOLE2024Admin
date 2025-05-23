package mabuntu.ecole.ecole2024.cntrol;

import static mabuntu.ecole.ecole2024.event.EvtImporter.*;
public class CntImporter {

    public static boolean fields_is_Okay(){
        return (TCntrol.isFull(tel) && TCntrol.isFull(pwd));
    }

}
