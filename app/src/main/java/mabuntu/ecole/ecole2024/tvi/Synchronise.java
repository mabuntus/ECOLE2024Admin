package mabuntu.ecole.ecole2024.tvi;

import static mabuntu.ecole.ecole2024.event.EvtMenuList.context;

import android.util.Log;

import mabuntu.ecole.ecole2024.model_table.MEleve;
import mabuntu.ecole.ecole2024.model_table.MMatiere;
import mabuntu.ecole.ecole2024.model_table.MMoy1;
import mabuntu.ecole.ecole2024.model_table.MMoy2;
import mabuntu.ecole.ecole2024.model_table.MMoy3;
import mabuntu.ecole.ecole2024.model_table.MNote1;
import mabuntu.ecole.ecole2024.model_table.MNote2;
import mabuntu.ecole.ecole2024.model_table.MNote3;
import mabuntu.ecole.ecole2024.tools.Fil;
import mabuntu.ecole.ecole2024.tools.TCode;
import mabuntu.ecole.ecole2024.tools.Tools;

public class Synchronise {

    private static String COL = ",";
    private static String LINE = ";";
    private static String MAP = "@";

    private static void synchronise_deleteAll(String classe){
        MMatiere.delClasse(context, classe);
        MEleve.delClasse(context, classe);
        MNote1.delClasse(context, classe);
        MNote2.delClasse(context, classe);
        MNote3.delClasse(context, classe);
        MMoy1.delClasse(context, classe);
        MMoy2.delClasse(context, classe);
        MMoy3.delClasse(context, classe);
    }

    public static void run(String file_data){
        String[] tab = file_data.split(LINE);
        String classe = Tools.b642str(tab[0]);
        int i = 1;

        synchronise_deleteAll(classe);

        synchro_matiere(tab[i++], classe);
        synchro_eleve(tab[i++], classe);
        synchro_note1(tab[i++], classe);
        synchro_note2(tab[i++], classe);
        synchro_note3(tab[i++], classe);
        synchro_moy1(tab[i++], classe);
        synchro_moy2(tab[i++], classe);
        synchro_moy3(tab[i++], classe);
        synchro_eleve_photo(tab[i], classe);
    }

    private static void synchro_matiere(String tab_matiere, String classe){
        if (tab_matiere.length() > 3) {
            String data = Tools.b642str(tab_matiere);
            String[] tab = data.split(LINE);
            for(String line : tab){
                if (line.contains(COL)){
                    String[] t = line.split(COL);
                    MMatiere obj = MMatiere.tab(t);
                    if (obj.classe.contentEquals(classe))
                        MMatiere.add(context, obj.code, obj.name, obj.coef, obj.classe);
                }
            }
        }
    }
    private static void synchro_eleve(String tab_eleve, String classe){
        if (tab_eleve.length() > 3) {
            String data = Tools.b642str(tab_eleve);
            String[] tab = data.split(LINE);
            for(String line : tab){
                if (line.contains(COL)){
                    String[] t = line.split(COL);
                    MEleve obj = MEleve.tab(t);
                    if (obj.classe.contentEquals(classe)) {

                        obj.matricule = obj.matricule.contains("-") ? obj.matricule : obj.matricule + "-" + classe.replace(" ", "");

                        MEleve.add(context,obj.code, obj.matricule, obj.classe, obj.prenom, obj.nom, obj.sexe, obj.dateN, obj.lieuN);
                    }
                }
            }
        }
    }
    private static void synchro_note1(String tab_note1, String classe){
        if (tab_note1.length() > 3) {
            String data = Tools.b642str(tab_note1);
            String[] tab = data.split(LINE);
            for(String line : tab){
                if (line.contains(COL)){
                    String[] t = line.split(COL);
                    MNote1 obj = MNote1.tab(t);
                    if (obj.classe.contentEquals(classe))
                        MNote1.add(context, TCode.note1(), obj.code_eleve, obj.code_matiere, obj.note, obj.classe);
                }
            }
        }
    }
    private static void synchro_note2(String tab_note2, String classe){
        if (tab_note2.length() > 3) {
            String data = Tools.b642str(tab_note2);
            String[] tab = data.split(LINE);
            for(String line : tab){
                if (line.contains(COL)){
                    String[] t = line.split(COL);
                    MNote2 obj = MNote2.tab(t);
                    if (obj.classe.contentEquals(classe))
                        MNote2.add(context, TCode.note2(), obj.code_eleve, obj.code_matiere, obj.note, obj.classe);
                }
            }
        }
    }
    private static void synchro_note3(String tab_note3, String classe){
        if (tab_note3.length() > 3) {
            String data = Tools.b642str(tab_note3);
            String[] tab = data.split(LINE);
            for(String line : tab){
                if (line.contains(COL)){
                    String[] t = line.split(COL);
                    MNote3 obj = MNote3.tab(t);
                    if (obj.classe.contentEquals(classe))
                        MNote3.add(context, TCode.note3(), obj.code_eleve, obj.code_matiere, obj.note, obj.classe);
                }
            }
        }
    }

    private static void synchro_moy1(String tab_moy1, String classe){
        if (tab_moy1.length() > 3) {
            String data = Tools.b642str(tab_moy1);
            String[] tab = data.split(LINE);
            for(String line : tab){
                if (line.contains(COL)){
                    String[] t = line.split(COL);
                    MMoy1 obj = MMoy1.tab(t);
                    if (obj.classe.contentEquals(classe))
                        MMoy1.add(context, TCode.moy1(), obj.code_eleve, obj.classe, obj.code_matiere, obj.total, obj.moy);
                }
            }
        }
    }
    private static void synchro_moy2(String tab_moy2, String classe){
        if (tab_moy2.length() > 3) {
            String data = Tools.b642str(tab_moy2);
            String[] tab = data.split(LINE);
            for(String line : tab){
                if (line.contains(COL)){
                    String[] t = line.split(COL);
                    MMoy2 obj = MMoy2.tab(t);
                    if (obj.classe.contentEquals(classe))
                        MMoy2.add(context, TCode.moy2(), obj.code_eleve, obj.classe, obj.code_matiere, obj.total, obj.moy);
                }
            }
        }
    }
    private static void synchro_moy3(String tab_moy3, String classe){
        if (tab_moy3.length() > 3) {
            String data = Tools.b642str(tab_moy3);
            String[] tab = data.split(LINE);
            for(String line : tab){
                if (line.contains(COL)){
                    String[] t = line.split(COL);
                    MMoy3 obj = MMoy3.tab(t);
                    if (obj.classe.contentEquals(classe))
                        MMoy3.add(context, TCode.moy3(), obj.code_eleve, obj.classe, obj.code_matiere, obj.total, obj.moy);
                }
            }
        }
    }

    private static void synchro_eleve_photo(String tab_photo, String classe){
        if (tab_photo.length() > 3) {
            String[] tab = tab_photo.split(COL);
            for (String line : tab){
                String[] t = line.split(MAP);
                String fname = Tools.b642str(t[0]);

                classe = classe.replace(" ", "");
                fname = fname.contains(classe) ? fname : fname + "-" + classe;

                Fil.put_from_b64_str(context, fname, t[1]);
                Log.println(Log.DEBUG, "synchronise", fname);
            }
        }
    }

}
