package mabuntu.ecole.ecole2024.cntrol;

import static mabuntu.ecole.ecole2024.event.EvtConnexion.*;

import java.util.HashMap;

import mabuntu.ecole.ecole2024.model_table.MLogin;
import mabuntu.ecole.ecole2024.tools.FILE;
import mabuntu.ecole.ecole2024.tools.Fil;
import mabuntu.ecole.ecole2024.tools.TStr;
import mabuntu.ecole.ecole2024.tvi.Tvi;

public class CntConnexion {

    public static boolean isAdmin(){
        if (!TCntrol.isFull(codeApp, codeEcole))
            return false;

        String scodeApp = Tvi.str(codeApp);
        String scodeEcole = Tvi.str(codeEcole);

        if (MLogin.isLogin(scodeApp)) {
            context.deleteFile(FILE.login);
            MLogin.add(context, scodeApp, "all", scodeEcole);
            return true;
        }
        return false;
    }

}
