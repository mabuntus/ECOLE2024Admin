package mabuntu.ecole.ecole2024.tvi;

import static mabuntu.ecole.ecole2024.event.EvtNote.*;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mabuntu.ecole.ecole2024.model_table.MEleve;
import mabuntu.ecole.ecole2024.model_table.MMatiere;
import mabuntu.ecole.ecole2024.model_table.MMoy1;
import mabuntu.ecole.ecole2024.model_table.MMoy2;
import mabuntu.ecole.ecole2024.model_table.MMoy3;
import mabuntu.ecole.ecole2024.model_table.MNote1;
import mabuntu.ecole.ecole2024.model_table.MNote2;
import mabuntu.ecole.ecole2024.model_table.MNote3;
import mabuntu.ecole.ecole2024.model_tvi.MLayNote;
import mabuntu.ecole.ecole2024.model_tvi.MLayNoteMatiere;
import mabuntu.ecole.ecole2024.tools.TNum;
import mabuntu.tombolia.pressing.R;

public class TviNote {

    public static void clean_box(){
        boxContent.removeAllViews();
    }

    public static void fill_box(){
        indiceElv = 0;
        String sclasse = Tvi.str(classe);
        int idtrimestre = Tvi.idtrimestre(trimestre);

        mElvs = MEleve.fromClasse(context, sclasse);
        mMatieres = MMatiere.fromClasse(context, sclasse);
        fill_box_note_by_trimestre(idtrimestre);
    }

    private static void fill_box_note_by_trimestre(int trimestre){
        if (trimestre == 1) fill_box_note1();
        else if (trimestre == 2) fill_box_note2();
        else fill_box_note3();
    }

    private static void fill_box_note_events(int idtrimestre, TextView vBtnPrev, TextView vBtnNext){
        vBtnPrev.setOnClickListener(v -> {
            String sedit = Tvi.str(idElv);
            if (!sedit.isEmpty()) {
                indiceElv = TNum.i(sedit);
            }
            if (indiceElv > 0){
                indiceElv--;
                boxContent.removeAllViews();
                fill_box_note_by_trimestre(idtrimestre);
            }
        });

        vBtnNext.setOnClickListener(v -> {
            String sedit = Tvi.str(idElv);
            if (!sedit.isEmpty()) {
                indiceElv = TNum.i(sedit);
            }
            if (indiceElv + 1 < mElvs.length) {
                indiceElv++;
                boxContent.removeAllViews();
                fill_box_note_by_trimestre(idtrimestre);
            }
        });
    }
    private static void fill_box_note1(){
        MEleve elv = mElvs[indiceElv];
        MMatiere[] matieres = mMatieres;
        MNote1.newFromClasse(context, elv, matieres);

        MMoy1 mmoy1 = MMoy1.create_if_nexist(context, elv);
        MLayNote mLayNote = new MLayNote(context, R.layout.lay_note);
        mLayNote.set_eleve(elv, indiceElv, mElvs.length);

        fill_box_note_events(1, mLayNote.btnPrev, mLayNote.btnNext);

        Double total = 0.0;
        int coefs = 0;
        for(int i = 0; i < matieres.length; i++){
            MMatiere matiere = matieres[i];
            MNote1 note = MNote1.fromElvMatiere(context, elv.code, matiere.code);

            MLayNoteMatiere mLayNoteMatiere = new MLayNoteMatiere(context, R.layout.lay_note_matiere);
            mLayNoteMatiere.fill_data_note1(i, note, matiere);

            if (!TNum.isNull(note.note))
                mLayNoteMatiere.note.setText(note.note);

            int coef = TNum.h2i(matiere.coef);
            double nte = TNum.f(note.note);
            if (nte > 0.0) {
                total = total + coef * nte;
                coefs = coefs + coef;
            }
            mLayNote.boxNote.addView(mLayNoteMatiere.box);
        }
        click_save_event(mLayNote, mmoy1, null, null);

        double moy = total / coefs;
        mLayNote.total.setText(TNum.f2note(total));
        mLayNote.moy.setText(TNum.f2note(moy));
        mmoy1.total = TNum.f2note(total);
        mmoy1.moy = TNum.f2note(moy);
        MMoy1.updt(context, mmoy1);

        boxContent.addView(mLayNote.box);
    }

    private static void fill_box_note2(){
        MEleve elv = mElvs[indiceElv];
        MMatiere[] matieres = mMatieres;
        MNote2.newFromClasse(context, elv, matieres);

        MMoy2 mmoy2 = MMoy2.create_if_nexist(context, elv);
        MLayNote mLayNote = new MLayNote(context, R.layout.lay_note);
        mLayNote.set_eleve(elv, indiceElv, mElvs.length);

        fill_box_note_events(2, mLayNote.btnPrev, mLayNote.btnNext);

        Double total = 0.0;
        int coefs = 0;
        for(int i = 0; i < matieres.length; i++){
            MMatiere matiere = matieres[i];
            MNote2 note = MNote2.fromElvMatiere(context, elv.code, matiere.code);

            MLayNoteMatiere mLayNoteMatiere = new MLayNoteMatiere(context, R.layout.lay_note_matiere);
            mLayNoteMatiere.fill_data_note2(i, note, matiere);
            Log.println(Log.DEBUG, "note-note-2", String.format("%s %s", matiere.code.substring(14), matiere.name));

            if (!TNum.isNull(note.note))
                mLayNoteMatiere.note.setText(note.note);

            int coef = TNum.h2i(matiere.coef);
            double nte = TNum.f(note.note);
            if (nte > 0.0) {
                total = total + coef * nte;
                coefs = coefs + coef;
            }
            mLayNote.boxNote.addView(mLayNoteMatiere.box);
        }
        click_save_event(mLayNote, null, mmoy2, null);

        double moy = total / coefs;
        mLayNote.total.setText(TNum.f2note(total));
        mLayNote.moy.setText(TNum.f2note(moy));
        mmoy2.total = TNum.f2note(total);
        mmoy2.moy = TNum.f2note(moy);
        MMoy2.updt(context, mmoy2);

        boxContent.addView(mLayNote.box);
    }

    private static void fill_box_note3(){
        MEleve elv = mElvs[indiceElv];
        MMatiere[] matieres = mMatieres;
        MNote3.newFromClasse(context, elv, matieres);

        MMoy3 mmoy3 = MMoy3.create_if_nexist(context, elv);
        MLayNote mLayNote = new MLayNote(context, R.layout.lay_note);
        mLayNote.set_eleve(elv, indiceElv, mElvs.length);

        fill_box_note_events(3, mLayNote.btnPrev, mLayNote.btnNext);

        Double total = 0.0;
        int coefs = 0;
        for(int i = 0; i < matieres.length; i++){
            MMatiere matiere = matieres[i];
            MNote3 note = MNote3.fromElvMatiere(context, elv.code, matiere.code);

            MLayNoteMatiere mLayNoteMatiere = new MLayNoteMatiere(context, R.layout.lay_note_matiere);
            mLayNoteMatiere.fill_data_note3(i, note, matiere);

            if (!TNum.isNull(note.note))
                mLayNoteMatiere.note.setText(note.note);

            int coef = TNum.h2i(matiere.coef);
            double nte = TNum.f(note.note);
            if (nte > 0.0) {
                total = total + coef * nte;
                coefs = coefs + coef;
            }
            mLayNote.boxNote.addView(mLayNoteMatiere.box);
        }
        click_save_event(mLayNote, null, null, mmoy3);

        double moy = total / coefs;
        mLayNote.total.setText(TNum.f2note(total));
        mLayNote.moy.setText(TNum.f2note(moy));
        mmoy3.total = TNum.f2note(total);
        mmoy3.moy = TNum.f2note(moy);
        MMoy3.updt(context, mmoy3);

        boxContent.addView(mLayNote.box);
    }
    private static void click_save_event(MLayNote mLayNote, MMoy1 mmoy1, MMoy2 mmoy2, MMoy3 mmoy3){
        mLayNote.btnSave.setOnClickListener(v -> {
            int size = mLayNote.boxNote.getChildCount();
            double itotal = 0.0;
            int icoefs = 0;
            ArrayList<MLayNoteMatiere> list = new ArrayList<>();

            for(int i = 0; i < size; i++){
                LinearLayout boxInNote = (LinearLayout) mLayNote.boxNote.getChildAt(i);
                MLayNoteMatiere mLayNoteMatiere = new MLayNoteMatiere(boxInNote);
                list.add(mLayNoteMatiere);

                int coef = Tvi.inT(mLayNoteMatiere.coef);
                float nte = Tvi.floaT(mLayNoteMatiere.note);
                if (nte > 0.0) {
                    itotal = itotal + nte * coef;
                    icoefs = icoefs + coef;
                }
            }
            click_save_event__updtNote_new(mmoy1, mmoy2, mmoy3, list);
            click_save_event__updtMoy(mmoy1, mmoy2, mmoy3, mLayNote, itotal, icoefs);
        });
    }

    private static void click_save_event__updtNote_new(MMoy1 mmoy1, MMoy2 mmoy2, MMoy3 mmoy3, ArrayList<MLayNoteMatiere> list){
        int size = list.size();
        if (mmoy1 != null) {
            ArrayList<MNote1> notes_list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                MLayNoteMatiere mLayNoteMatiere = list.get(i);
                String scode = Tvi.str(mLayNoteMatiere.code);
                String snote = Tvi.str(mLayNoteMatiere.note);
                MNote1 mNote1 = MNote1.fromCode(context, scode);
                mNote1.note = TNum.f2note(snote);
                notes_list.add(mNote1);
            }
            MNote1.updt(context, notes_list);
        }

        else if (mmoy2 != null){
            ArrayList<MNote2> notes_list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                MLayNoteMatiere mLayNoteMatiere = list.get(i);
                String scode = Tvi.str(mLayNoteMatiere.code);
                String snote = Tvi.str(mLayNoteMatiere.note);
                MNote2 mNote2 = MNote2.fromCode(context, scode);
                mNote2.note = TNum.f2note(snote);
                notes_list.add(mNote2);
            }
            MNote2.updt(context, notes_list);
        }
        else if (mmoy3 != null) {
            ArrayList<MNote3> notes_list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                MLayNoteMatiere mLayNoteMatiere = list.get(i);
                String scode = Tvi.str(mLayNoteMatiere.code);
                String snote = Tvi.str(mLayNoteMatiere.note);
                MNote3 mNote3 = MNote3.fromCode(context, scode);
                mNote3.note = TNum.f2note(snote);
                notes_list.add(mNote3);
            }
            MNote3.updt(context, notes_list);
        }
    }

    private static void click_save_event__updtMoy(MMoy1 mmoy1, MMoy2 mmoy2, MMoy3 mmoy3, MLayNote mLayNote, double total, int coefs){
        double moy = total / coefs;
        String stotal = TNum.f2note(total);
        String smoy = TNum.f2note(moy);

        mLayNote.total.setText(stotal);
        mLayNote.moy.setText(smoy);
        if (mmoy1 != null){
            mmoy1.total = stotal;
            mmoy1.moy = smoy;
            MMoy1.updt(context, mmoy1);
        }
        else if (mmoy2 != null){
            mmoy2.total = stotal;
            mmoy2.moy = smoy;
            MMoy2.updt(context, mmoy2);
        }
        else if (mmoy3 != null){
            mmoy3.total = stotal;
            mmoy3.moy = smoy;
            MMoy3.updt(context, mmoy3);
        }
    }

}
