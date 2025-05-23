package mabuntu.ecole.ecole2024.model_table;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import mabuntu.ecole.ecole2024.tools.FILE;
import mabuntu.ecole.ecole2024.tools.Tools;

public class MLogin {

    public static String password = "MABuntuS.2024.ecole";

    public boolean isLogin(){
        Log.println(Log.DEBUG, "login", this.code);
        return isLogin(this.code);
    }
    public static boolean isLogin(String codeApp){
        return password.contentEquals(codeApp);
    }


    public String code;
    public String classe;
    public String code_ecole;
    private static final int TAB_SIZE = 3;
    private static final String fname = FILE.login;

    private static final int niv_code = 0;
    private static final int niv_classe = 1;
    private static final int niv_code_ecole = 2;

    public MLogin(){ this("", "", ""); }

    public MLogin(String code, String classe, String code_ecole){
        this.code = code;
        this.classe = classe;
        this.code_ecole = code_ecole;
    }

    public static boolean add(Context context,
                              String code,
                              String classe,
                              String code_ecole){
        return TDb.add(context, fname, code, classe, code_ecole);
    }

    public static boolean del(Context context, String code){
        return TDb.rmv(context, fname, niv_code, code);
    }

    public static MLogin get(Context context){
        ArrayList<String[]> list = TDb.getAll(context, fname);
        if (list == null || list.isEmpty()) return null;
        return tab(list.get(0));
    }

    private static MLogin tab(String[] tab){
        try {
            String code = Tools.b642str(tab[0]);
            String classe = Tools.b642str(tab[1]);
            String code_ecole = Tools.b642str(tab[2]);
            return new MLogin(code, classe, code_ecole);
        } catch (Exception e){
            return null;
        }
    }

}

