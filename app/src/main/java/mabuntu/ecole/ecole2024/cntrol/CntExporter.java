package mabuntu.ecole.ecole2024.cntrol;

import static mabuntu.ecole.ecole2024.event.EvtExporter.*;
public class CntExporter {

    public static boolean fields_is_Okay(){
        return (TCntrol.isFull(tel) && TCntrol.isFull(pwd));
    }

}
