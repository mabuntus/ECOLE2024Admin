package mabuntu.ecole.ecole2024.model_table;

import android.content.Context;

import java.util.ArrayList;

import mabuntu.ecole.ecole2024.tools.FILE;
import mabuntu.ecole.ecole2024.tools.Tools;

public class MMatiere {

    public String code;
    public String name;
    public String coef;
    public String classe;
    private static final int TAB_SIZE = 4;
    private static final String fname = FILE.matiere;

    private static final int niv_code = 0;
    private static final int niv_name = 1;
    private static final int niv_coef = 2;
    private static final int niv_classe = 3;

    public MMatiere(){
        this("", "", "", "");
    }

    public MMatiere(String code,
                    String name,
                    String coef,
                    String classe){
        this.code = code;
        this.name = name;
        this.coef = coef;
        this.classe = classe;
    }

    public static boolean add(Context context,
                           String code,
                           String name,
                           String coef,
                           String classe){
        return TDb.add(context, fname, code, name, coef, classe);
    }
    public static MMatiere[] fromClasse(Context context, String classe){
        ArrayList<String[]> list = TDb.getAll(context, fname, niv_classe, classe);
        if (list == null || list.isEmpty()) return null;

        int size = list.size();
        MMatiere[] mMatieres = new MMatiere[size];
        for(int i = 0; i < size; i++){
            String[] t = list.get(i);
            if (t.length == TAB_SIZE)
                mMatieres[i] = tab(t);
        }
        return mMatieres;
    }
    public static boolean del(Context context, String code){
        return TDb.rmv(context, fname, niv_code, code);
    }
    public static boolean delClasse(Context context, String classe){
        return TDb.rmv(context, fname, niv_classe, classe);
    }
    public static boolean updt(Context context, MMatiere obj){
        return TDb.updt(context, fname, obj.code, obj.name, obj.coef, obj.classe);
    }
    public static MMatiere fromCode(Context context, String code){
        String[] t = TDb.get(context, fname, niv_code, code);
        return (t == null || t.length != TAB_SIZE) ? null : tab(t);
    }
    public static MMatiere tab(String[] tab){
        String code = Tools.b642str(tab[0]);
        String name = Tools.b642str(tab[1]);
        String coef = Tools.b642str(tab[2]);
        String classe = Tools.b642str(tab[3]);
        return new MMatiere(code, name, coef, classe);
    }
}
