package mabuntu.ecole.ecole2024.model_table;

import android.content.Context;

import java.util.ArrayList;

import mabuntu.ecole.ecole2024.tools.FILE;
import mabuntu.ecole.ecole2024.tools.TCode;
import mabuntu.ecole.ecole2024.tools.Tools;

public class MMoy3 {

    public String code;
    public String code_eleve;
    public String classe;
    public String code_matiere;
    public String total;
    public String moy;
    private static final int TAB_SIZE = 6;
    private static final String fname = FILE.moy3;

    private static final int niv_code = 0;
    private static final int niv_code_eleve = 1;
    private static final int niv_classe = 2;
    private static final int niv_code_matiere = 3;
    private static final int niv_total = 4;
    private static final int niv_moy = 5;

    public MMoy3(){
        this("", "", "", "", "", "");
    }

    public MMoy3(String code,
                 String code_eleve,
                 String classe,
                 String code_matiere,
                 String total,
                 String moy){
        this.code = code;
        this.code_eleve = code_eleve;
        this.classe = classe;
        this.code_matiere = code_matiere;
        this.total = total;
        this.moy = moy;
    }

    public static void add(Context context,
                           String code,
                           String code_eleve,
                           String classe,
                           String code_matiere,
                           String total,
                           String moy){
        TDb.add(context, fname, code, code_eleve, classe, code_matiere, total, moy);
    }


    public static MMoy3 create_if_nexist(Context context, MEleve elv){
        MMoy3 mmoy = fromCodeElv(context, elv.code);
        if (mmoy != null)
            return mmoy;

        add(context, TCode.moy3(), elv.code, elv.classe, "code-matiere", "0.0", "0.0");
        return fromCodeElv(context, elv.code);
    }

    public static MMoy3[] fromCodeMatiere(Context context, String codeMatiere){
        return from_(context, niv_code_matiere, codeMatiere);
    }
    public static MMoy3[] fromClasse(Context context, String classe){
        return from_(context, niv_classe, classe);
    }
    public static MMoy3 fromCodeElv(Context context, String codeElv){
        MMoy3[] mmoys = from_(context, niv_code_eleve, codeElv);
        if (mmoys == null) return null;
        return mmoys[0];
    }
    private static MMoy3[] from_(Context context, int colNiv, String colVal){
        ArrayList<String[]> list = TDb.getAll(context, fname, colNiv, colVal);
        if (list == null || list.isEmpty()) return null;

        int size = list.size();
        MMoy3[] mMoys = new MMoy3[size];
        for(int i = 0; i < size; i++){
            String[] t = list.get(i);
            if (t.length == TAB_SIZE)
                mMoys[i] = tab(t);
        }
        return mMoys;
    }
    public static boolean delCodeMatiere(Context context, String codeMatiere){
        return TDb.rmv(context, fname, niv_code_matiere, codeMatiere);
    }
    public static boolean delCodeElv(Context context, String codeElv){
        return TDb.rmv(context, fname, niv_code_eleve, codeElv);
    }
    public static boolean del(Context context, String code){
        return TDb.rmv(context, fname, niv_code, code);
    }
    public static boolean delClasse(Context context, String classe){
        return TDb.rmv(context, fname, niv_classe, classe);
    }
    public static boolean updt(Context context, MMoy3 obj){
        return TDb.updt(context, fname, obj.code, obj.code_eleve, obj.classe, obj.code_matiere, obj.total, obj.moy);
    }
    public static MMoy3 fromCode(Context context, String code){
        String[] t = TDb.get(context, fname, niv_code, code);
        return (t == null || t.length != TAB_SIZE) ? null : tab(t);
    }
    public static MMoy3 tab(String[] tab){
        String code = Tools.b642str(tab[0]);
        String code_eleve = Tools.b642str(tab[1]);
        String classe = Tools.b642str(tab[2]);
        String code_matiere = Tools.b642str(tab[3]);
        String total = Tools.b642str(tab[4]);
        String moy = Tools.b642str(tab[5]);
        return new MMoy3(code, code_eleve, classe, code_matiere, total, moy);
    }

}
