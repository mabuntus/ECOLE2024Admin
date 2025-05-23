package mabuntu.ecole.ecole2024.model_table;


import android.content.Context;

import java.util.ArrayList;

import mabuntu.ecole.ecole2024.tools.FILE;
import mabuntu.ecole.ecole2024.tools.TCode;
import mabuntu.ecole.ecole2024.tools.Tools;

public class MNote1 {

    public  String code;
    public  String code_eleve;
    public  String code_matiere;
    public  String note;
    public  String classe;

    protected static final int TAB_SIZE = 5;
    protected static final String fname = FILE.note1;
    protected static final int niv_code = 0;
    protected static final int niv_code_elev = 1;
    protected static final int niv_matiere = 2;
    protected static final int niv_note = 3;
    protected static final int niv_classe = 4;


    public MNote1(){
        this("", "", "", "", "");
    }

    public MNote1(String code,
                  String code_elev,
                  String code_matiere,
                  String note,
                  String classe){
        this.code = code;
        this.code_eleve = code_elev;
        this.note = note;
        this.code_matiere = code_matiere;
        this.classe = classe;
    }

    public static void add(Context context,
                            String code,
                            String codeElv,
                            String codeMatiere,
                            String note,
                            String classe){
        TDb.add(context, fname, code, codeElv, codeMatiere, note,classe);
    }
    public static MNote1[] newFromClasse(Context context, MEleve elv, MMatiere[] matieres){
        for(int i = 0; i < matieres.length; i++){
            MNote1 note = fromElvMatiere(context, elv.code, matieres[i].code);
            if (note == null)
                add(context, TCode.note1(), elv.code, matieres[i].code, "0.0", matieres[i].classe);
        }
        MNote1[] notes = new MNote1[matieres.length];
        for(int i = 0; i < notes.length; i++){
            notes[i] = fromElvMatiere(context, elv.code, matieres[i].code);
        }
        return notes;
    }
    public static MNote1 fromElvMatiere(Context context, String codeElv, String codeMatiere){
        MNote1[] notes = from(context, niv_matiere, codeMatiere);
        if (notes == null) return null;

        for(int i = 0; i < notes.length; i++){
            MNote1 note1 = notes[i];
            if (note1 != null && note1.code_eleve.contentEquals(codeElv))
                return note1;
        }
        return null;
    }
    public static MNote1[] fromCodeMatiere(Context context, String codeMatiere){
        return from(context, niv_matiere, codeMatiere);
    }
    public static MNote1[] fromCodeElv(Context context, String codeElv){
        return from(context, niv_code_elev, codeElv);
    }
    public static MNote1[] fromClasse(Context context, String classe){
        return from(context, niv_classe, classe);
    }
    protected static MNote1[] from(Context context, int colNiv, String colValue){
        ArrayList<String[]> list = TDb.getAll(context, fname, colNiv, colValue);
        if (list == null || list.isEmpty()) return null;

        int size = list.size();
        MNote1[] notes = new MNote1[size];
        for(int i = 0; i < size; i++){
            String[] t = list.get(i);
            if (t.length == TAB_SIZE)
                notes[i] = tab(t);
        }
        return notes;
    }

    public static boolean del(Context context, String code){
        return TDb.rmv(context, fname, niv_code, code);
    }
    public static boolean delCodeElv(Context context, String codeElv){
        return TDb.rmv(context, fname, niv_code_elev, codeElv);
    }
    public static boolean delMatiere(Context context, String codeMatiere){
        return TDb.rmv(context, fname, niv_matiere, codeMatiere);
    }
    public static boolean delClasse(Context context, String classe){
        return TDb.rmv(context, fname, niv_classe, classe);
    }
    public static MNote1 fromCode(Context context, String code){
        String[] t = TDb.get(context, fname, niv_code, code);
        return (t == null || t.length != TAB_SIZE) ? null : tab(t);
    }
    public static boolean updt(Context context, MNote1 note){
        return TDb.updt(context, fname, note.code, note.code_eleve, note.code_matiere, note.note, note.classe);
    }
    public static boolean updt(Context context, ArrayList<MNote1> notes){
        for (MNote1 note : notes) {
            TDb.updt(context, fname, note.code, note.code_eleve, note.code_matiere, note.note, note.classe);
        }
        return true;
    }

    public static MNote1 tab(String[] tab){
        String code = Tools.b642str(tab[0]);
        String codeElv = Tools.b642str(tab[1]);
        String codeMatiere = Tools.b642str(tab[2]);
        String note = Tools.b642str(tab[3]);
        String classe = Tools.b642str(tab[4]);

        return new MNote1(code, codeElv, codeMatiere, note, classe);
    }
}
