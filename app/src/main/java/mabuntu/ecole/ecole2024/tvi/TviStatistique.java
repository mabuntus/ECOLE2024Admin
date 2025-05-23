package mabuntu.ecole.ecole2024.tvi;

import static mabuntu.ecole.ecole2024.event.EvtStatistique.*;

import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import mabuntu.ecole.ecole2024.cntrol.TCntrol;
import mabuntu.ecole.ecole2024.model_table.MEleve;
import mabuntu.ecole.ecole2024.model_table.MMatiere;
import mabuntu.ecole.ecole2024.model_table.MNote1;
import mabuntu.ecole.ecole2024.model_table.MNote2;
import mabuntu.ecole.ecole2024.model_table.MNote3;
import mabuntu.ecole.ecole2024.tools.TNum;
import mabuntu.tombolia.pressing.R;

public class TviStatistique {

    public static void clean_box(){
        int size = boxContent.getChildCount();
        if (size == 1) return;

        LinearLayout[] boxes = new LinearLayout[size-1];
        for(int i = 1; i < size; i++){
            LinearLayout box = (LinearLayout) boxContent.getChildAt(i);
            boxes[i - 1] = box;
        }
        for(int i = 0; i < boxes.length; i++){
            boxContent.removeView(boxes[i]);
        }
    }

    public static void fill_box(){
        String sclasse = classe.getText().toString();
        String strimestre = trimestre.getText().toString();
        int idtrimestre = strimestre.contains("1") ? 1 : strimestre.contains("2") ? 2 : 3;

        MMatiere[] matieres = MMatiere.fromClasse(context, sclasse);
        if (matieres == null) return;

        for(MMatiere matiere : matieres){
            if (idtrimestre == 1) {
                MNote1[] notes = MNote1.fromCodeMatiere(context, matiere.code);
                if (notes != null)
                    fill_box_note1(notes, matiere);
            }
            else if (idtrimestre == 2){
                MNote2[] notes = MNote2.fromCodeMatiere(context, matiere.code);
                if (notes != null)
                    fill_box_note2(notes, matiere);
            }
            else if (idtrimestre == 3){
                MNote3[] notes = MNote3.fromCodeMatiere(context, matiere.code);
                if (notes != null)
                    fill_box_note3(notes, matiere);
            }
        }
    }

    private static int[] note1_femme_and_total(MNote1[] notes, double nmin, double nmax){
        int femme = 0;
        int total = 0;
        for(int i = 0; i < notes.length; i++){
            MNote1 note = notes[i];
            MEleve elv = MEleve.fromCode(context, note.code_eleve);
            if (elv != null) {
                double nte = TNum.f(note.note);
                if (nte >= nmin && nte <= nmax) {
                    total = total + 1;
                    if (TCntrol.sexeIsFemme(elv.sexe))
                        femme = femme + 1;
                }
            }
        }
        return new int[] {femme, total};
    }
    private static int[] note2_femme_and_total(MNote2[] notes, double nmin, double nmax){
        int femme = 0;
        int total = 0;
        for(int i = 0; i < notes.length; i++){
            MNote2 note = notes[i];
            MEleve elv = MEleve.fromCode(context, note.code_eleve);
            if (elv != null) {
                double nte = TNum.f(note.note);
                if (nte >= nmin && nte <= nmax) {
                    total = total + 1;
                    if (TCntrol.sexeIsFemme(elv.sexe))
                        femme = femme + 1;
                }
            }
        }
        return new int[] {femme, total};
    }
    private static int[] note3_femme_and_total(MNote3[] notes, double nmin, double nmax){
        int femme = 0;
        int total = 0;
        for(int i = 0; i < notes.length; i++){
            MNote3 note = notes[i];
            MEleve elv = MEleve.fromCode(context, note.code_eleve);
            if (elv != null) {
                double nte = TNum.f(note.note);
                if (nte >= nmin && nte <= nmax) {
                    total = total + 1;
                    if (TCntrol.sexeIsFemme(elv.sexe))
                        femme = femme + 1;
                }
            }
        }
        return new int[] {femme, total};
    }
    private static void fill_box_note1(MNote1[] notes, MMatiere matiere){
        int[] n0_1_99 = note1_femme_and_total(notes, 0, 1.99);
        int[] n2_3_99 = note1_femme_and_total(notes, 2, 3.99);
        int[] n4_5_99 = note1_femme_and_total(notes, 4, 5.99);
        int[] n6_7_99 = note1_femme_and_total(notes, 6, 7.99);
        int[] n8_10 = note1_femme_and_total(notes, 8, 10);
        int[] moy_5_sup = note1_femme_and_total(notes, 5, 20);
        int[] composer = note1_femme_and_total(notes, 0.5, 20);

        fill_box_note_view(matiere, new int[][]{n0_1_99, n2_3_99, n4_5_99, n6_7_99, n8_10, moy_5_sup, composer});
    }
    private static void fill_box_note2(MNote2[] notes, MMatiere matiere){
        int[] n0_1_99 = note2_femme_and_total(notes, 0, 1.99);
        int[] n2_3_99 = note2_femme_and_total(notes, 2, 3.99);
        int[] n4_5_99 = note2_femme_and_total(notes, 4, 5.99);
        int[] n6_7_99 = note2_femme_and_total(notes, 6, 7.99);
        int[] n8_10 = note2_femme_and_total(notes, 8, 10);
        int[] moy_5_sup = note2_femme_and_total(notes, 5, 20);
        int[] composer = note2_femme_and_total(notes, 0.5, 20);

        fill_box_note_view(matiere, new int[][]{n0_1_99, n2_3_99, n4_5_99, n6_7_99, n8_10, moy_5_sup, composer});
    }
    private static void fill_box_note3(MNote3[] notes, MMatiere matiere){
        int[] n0_1_99 = note3_femme_and_total(notes, 0, 1.99);
        int[] n2_3_99 = note3_femme_and_total(notes, 2, 3.99);
        int[] n4_5_99 = note3_femme_and_total(notes, 4, 5.99);
        int[] n6_7_99 = note3_femme_and_total(notes, 6, 7.99);
        int[] n8_10 = note3_femme_and_total(notes, 8, 10);
        int[] moy_5_sup = note3_femme_and_total(notes, 5, 20);
        int[] composer = note3_femme_and_total(notes, 0.5, 20);

        fill_box_note_view(matiere, new int[][]{n0_1_99, n2_3_99, n4_5_99, n6_7_99, n8_10, moy_5_sup, composer});
    }
    private static void fill_box_note_view(MMatiere matiere, int[][] value){
        int[] n0_1_99 = value[0];
        int[] n2_3_99 = value[1];
        int[] n4_5_99 = value[2];
        int[] n6_7_99 = value[3];
        int[] n8_10 = value[4];
        int[] moy_5_sup = value[5];
        int[] composer = value[6];

        LinearLayout box = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.lay_statistique, null);
        setTextView(box, R.id.lay_statistique_matiere, matiere.name);

        setTextView(box, R.id.lay_statistique_no_1_fille, TNum.iS(n0_1_99[0]));
        setTextView(box, R.id.lay_statistique_no_1_total, TNum.iS(n0_1_99[1]));
        setTextView(box, R.id.lay_statistique_no_2_fille, TNum.iS(n2_3_99[0]));
        setTextView(box, R.id.lay_statistique_no_2_total, TNum.iS(n2_3_99[1]));
        setTextView(box, R.id.lay_statistique_no_3_fille, TNum.iS(n4_5_99[0]));
        setTextView(box, R.id.lay_statistique_no_3_total, TNum.iS(n4_5_99[1]));
        setTextView(box, R.id.lay_statistique_no_4_fille, TNum.iS(n6_7_99[0]));
        setTextView(box, R.id.lay_statistique_no_4_total, TNum.iS(n6_7_99[1]));
        setTextView(box, R.id.lay_statistique_no_5_fille, TNum.iS(n8_10[0]));
        setTextView(box, R.id.lay_statistique_no_5_total, TNum.iS(n8_10[1]));
        setTextView(box, R.id.lay_statistique_composer_fille, TNum.iS(composer[0]));
        setTextView(box, R.id.lay_statistique_composer_total, TNum.iS(composer[1]));
        setTextView(box, R.id.lay_statistique_moyennant_fille, TNum.iS(moy_5_sup[0]));
        setTextView(box, R.id.lay_statistique_moyennant_total, TNum.iS(moy_5_sup[1]));

        boxContent.addView(box);
    }


    private static void setTextView(LinearLayout box, int idRes, String value){
        TextView txt = box.findViewById(idRes);
        txt.setText(value);
    }


}
