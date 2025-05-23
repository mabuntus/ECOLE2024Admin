package mabuntu.ecole.ecole2024.tvi;

import static mabuntu.ecole.ecole2024.event.EvtMoyTotal.*;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

import mabuntu.ecole.ecole2024.cntrol.TCntStr;
import mabuntu.ecole.ecole2024.model_table.MEcole;
import mabuntu.ecole.ecole2024.model_table.MEleve;
import mabuntu.ecole.ecole2024.model_table.MMatiere;
import mabuntu.ecole.ecole2024.model_table.MMoy1;
import mabuntu.ecole.ecole2024.model_table.MMoy2;
import mabuntu.ecole.ecole2024.model_table.MMoy3;
import mabuntu.ecole.ecole2024.model_table.MMoyTotal;
import mabuntu.ecole.ecole2024.model_table.MNote1;
import mabuntu.ecole.ecole2024.model_table.MNote2;
import mabuntu.ecole.ecole2024.model_table.MNote3;
import mabuntu.ecole.ecole2024.model_table.MYear;
import mabuntu.ecole.ecole2024.print.PrtBulletinSemestreYear;
import mabuntu.ecole.ecole2024.print.PrtBulletinYear;
import mabuntu.ecole.ecole2024.tools.Fil;
import mabuntu.ecole.ecole2024.tools.TMap;
import mabuntu.ecole.ecole2024.tools.TNum;
import mabuntu.ecole.ecole2024.tools.TStrList;
import mabuntu.tombolia.pressing.R;

public class TviMoyTotal {

    private static boolean istrimestre;
    public static void print(){
        String sclasse = classe.getText().toString();
        istrimestre = TCntStr.classeIsTrimestre(sclasse);

        if (istrimestre) {
            print_trimestre();
        }
        else {
            print_semestre();
        }
    }


    public static void full_box(){
        boxContent.removeAllViews();
        String sclasse = classe.getText().toString();
        istrimestre = TCntStr.classeIsTrimestre(sclasse);
        MEleve[] eleves = MEleve.fromClasse(context, sclasse);
        if (eleves == null)
            return;

        if (istrimestre){
            full_box_trimestre(eleves, sclasse);
        }
        else {
            full_box_semestre(eleves, sclasse);
        }
    }
    private static void full_box_semestre(MEleve[] eleves, String sclasse){
        MMoyTotal[] moyTotals = new MMoyTotal[eleves.length];

        for (int i = 0; i < eleves.length; i++) {
            MEleve elv = eleves[i];
            MMoy1 moy1 = MMoy1.fromCodeElv(context, elv.code);
            MMoy2 moy2 = MMoy2.fromCodeElv(context, elv.code);

            MMoyTotal moyTotal = new MMoyTotal();
            fill_moy_total_semestre(elv, moyTotal, moy1, moy2);
            moyTotals[i] = moyTotal;
        }
        moyTotals = sortByMoy(moyTotals);
        full_box_view_semestre(moyTotals);
    }
    private static void full_box_trimestre(MEleve[] eleves, String sclasse){
        MMoyTotal[] moyTotals = new MMoyTotal[eleves.length];

        for (int i = 0; i < eleves.length; i++) {
            MEleve elv = eleves[i];
            MMoy1 moy1 = MMoy1.fromCodeElv(context, elv.code);
            MMoy2 moy2 = MMoy2.fromCodeElv(context, elv.code);
            MMoy3 moy3 = MMoy3.fromCodeElv(context, elv.code);

            MMoyTotal moyTotal = new MMoyTotal();
            fill_moy_total(elv, moyTotal, moy1, moy2, moy3);
            moyTotals[i] = moyTotal;
        }
        moyTotals = sortByMoy(moyTotals);
        full_box_view(moyTotals);
    }

    private static MMoyTotal[] sortByMoy(MMoyTotal[] moyTotals){
        MMoyTotal[] moyTotals_new = new MMoyTotal[moyTotals.length];
        HashMap<String, Double> map = new HashMap<>();

        for (MMoyTotal moyTotal : moyTotals){
            Double moy = (double) TNum.f(moyTotal.moy);
            map.put(moyTotal.matricule, moy);
        }
        int i = 0;
        map = TMap.sortByValue(map);
        for (String matricule : map.keySet()) {

            for (int j = 0; j < moyTotals.length; j++){
                String mat = moyTotals[j].matricule;
                if (mat.contentEquals(matricule)) {
                    moyTotals_new[i++] = moyTotals[j];
                    break;
                }
            }
        }
        moyTotals_setRang(moyTotals_new);
        return moyTotals_new;
    }
    private static void moyTotals_setRang(MMoyTotal[] moyTotals){
        for (int i = 0; i < moyTotals.length; i++){
            if (i == 0){
                moyTotals[i].rang = "1er";
            }
            else {
                MMoyTotal prev = moyTotals[i-1];
                MMoyTotal now = moyTotals[i];
                if (prev.moy.contentEquals(now.moy)) {
                    if (prev.rang.contains("exe"))
                        now.rang = prev.rang;
                    else
                        now.rang = String.format("%s exe", prev.rang);
                }
                else {
                    now.rang = String.format("%s eme", TNum.iS(i+1));
                }
            }
        }
    }

    private static void fill_moy_total_semestre(MEleve elv, MMoyTotal moyTotal, MMoy1 moy1, MMoy2 moy2){
        moyTotal.trimestre = false;

        moyTotal.matricule = elv.matricule;
        moyTotal.prenom = elv.prenom;
        moyTotal.nom = elv.nom;
        moyTotal.isFemme = TStrList.isFemme(elv.sexe);

        if (moy1 != null) {
            moyTotal.moy1 = moy1.moy;
            moyTotal.total1 = moy1.total;
            moyTotal.rang1 = moy1.code_matiere;
        }
        else {
            moy1 = new MMoy1();
            moyTotal.moy1 = moy1.moy = "0";
            moyTotal.total1 = moy1.total = "0";
            moyTotal.rang1 = "0";
        }

        if (moy2 != null) {
            moyTotal.moy2 = moy2.moy;
            moyTotal.total2 = moy2.total;
            moyTotal.rang2 = moy2.code_matiere;
        }
        else {
            moy2 = new MMoy2();
            moyTotal.moy2 = moy2.moy = "0";
            moyTotal.total2 = moy2.total = "0";
            moyTotal.rang2 = "0";
        }

        float moy1_total = TNum.f(moy1.total);
        float moy2_total = TNum.f(moy2.total);

        int nb_div = 2;
        if (moy1_total == 0.0 || moy2_total == 0.0)
            nb_div = 1;

        Float moy = (TNum.f(moy1.moy) + TNum.f(moy2.moy)) / nb_div;
        Float total = (TNum.f(moy1.total) + TNum.f(moy2.total)) / nb_div;
        moyTotal.moy = TNum.f2note(moy.toString());
        moyTotal.total = TNum.f2note(total.toString());
    }
    private static void fill_moy_total(MEleve elv, MMoyTotal moyTotal, MMoy1 moy1, MMoy2 moy2, MMoy3 moy3){
        moyTotal.trimestre = true;

        moyTotal.matricule = elv.matricule;
        moyTotal.prenom = elv.prenom;
        moyTotal.nom = elv.nom;
        moyTotal.isFemme = TStrList.isFemme(elv.sexe);

        if (moy1 != null) {
            moyTotal.moy1 = moy1.moy;
            moyTotal.total1 = moy1.total;
            moyTotal.rang1 = moy1.code_matiere;
        }
        else {
            moy1 = new MMoy1();
            moyTotal.moy1 = moy1.moy = "0";
            moyTotal.total1 = moy1.total = "0";
            moyTotal.rang1 = "0";
        }

        if (moy2 != null) {
            moyTotal.moy2 = moy2.moy;
            moyTotal.total2 = moy2.total;
            moyTotal.rang2 = moy2.code_matiere;
        }
        else {
            moy2 = new MMoy2();
            moyTotal.moy2 = moy2.moy = "0";
            moyTotal.total2 = moy2.total = "0";
            moyTotal.rang2 = "0";
        }

        if (moy3 != null) {
            moyTotal.moy3 = moy3.moy;
            moyTotal.total3 = moy3.total;
            moyTotal.rang3 = moy3.code_matiere;
        }
        else {
            moy3 = new MMoy3();
            moyTotal.moy3 = moy3.moy = "0";
            moyTotal.total3 = moy3.total = "0";
            moyTotal.rang3 = "0";
        }

        float moy1_total = TNum.f(moy1.total);
        float moy2_total = TNum.f(moy2.total);
        float moy3_total = TNum.f(moy3.total);

        int nb_div = 3;
        if (moy1_total == 0.0) --nb_div;
        if (moy2_total == 0.0) --nb_div;
        if (moy3_total == 0.0) --nb_div;
        if (nb_div < 1) nb_div = 1;

        Float moy = (TNum.f(moy1.moy) + TNum.f(moy2.moy) + TNum.f(moy3.moy)) / nb_div;
        Float total = (TNum.f(moy1.total) + TNum.f(moy2.total) + TNum.f(moy3.total)) / nb_div;
        moyTotal.moy = TNum.f2note(moy.toString());
        moyTotal.total = TNum.f2note(total.toString());
    }

    private static void full_box_view(MMoyTotal[] moyTotals){
        for (int i = 0; i < moyTotals.length; i++) {
            int no = i + 1;
            MMoyTotal moyTotal = moyTotals[i];

            LinearLayout box = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.lay_total_moy, null);

            TextView vno = Tvi.textview(box.findViewById(R.id.lay_total_moy_no), TNum.iS(no));
            TextView vprenom = Tvi.textview(box.findViewById(R.id.lay_total_moy_prenom), moyTotal.prenom);
            TextView vnom = Tvi.textview(box.findViewById(R.id.lay_total_moy_nom), moyTotal.nom);
            TextView vmatricule = Tvi.textview(box.findViewById(R.id.lay_total_moy_matricule), moyTotal.matricule);

            TextView vtotal_tri_1 = Tvi.textview(box.findViewById(R.id.lay_total_moy_total_trimestre_1), moyTotal.total1);
            TextView vtotal_tri_2 = Tvi.textview(box.findViewById(R.id.lay_total_moy_total_trimestre_2), moyTotal.total2);
            TextView vtotal_tri_3 = Tvi.textview(box.findViewById(R.id.lay_total_moy_total_trimestre_3), moyTotal.total3);

            TextView vmoy_tri_1 = Tvi.textview(box.findViewById(R.id.lay_total_moy_moy_trimestre_1), moyTotal.moy1);
            TextView vmoy_tri_2 = Tvi.textview(box.findViewById(R.id.lay_total_moy_moy_trimestre_2), moyTotal.moy2);
            TextView vmoy_tri_3 = Tvi.textview(box.findViewById(R.id.lay_total_moy_moy_trimestre_3), moyTotal.moy3);

            vtotal_tri_3.setVisibility(View.VISIBLE);
            vmoy_tri_3.setVisibility(View.VISIBLE);

            TextView vtotal = Tvi.textview(box.findViewById(R.id.lay_total_moy_total), moyTotal.total);
            TextView vmoy = Tvi.textview(box.findViewById(R.id.lay_total_moy_moyenne), moyTotal.moy);
            TextView vrang = Tvi.textview(box.findViewById(R.id.lay_total_moy_rang), moyTotal.rang);

            if (moyTotal.isFemme) {
                vprenom.setTextColor(Color.RED);
                vnom.setTextColor(Color.RED);
            }

            boxContent.addView(box);
        }

    }
    private static void full_box_view_semestre(MMoyTotal[] moyTotals){
        for (int i = 0; i < moyTotals.length; i++) {
            int no = i + 1;
            MMoyTotal moyTotal = moyTotals[i];

            LinearLayout box = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.lay_total_moy, null);

            TextView vno = Tvi.textview(box.findViewById(R.id.lay_total_moy_no), TNum.iS(no));
            TextView vprenom = Tvi.textview(box.findViewById(R.id.lay_total_moy_prenom), moyTotal.prenom);
            TextView vnom = Tvi.textview(box.findViewById(R.id.lay_total_moy_nom), moyTotal.nom);
            TextView vmatricule = Tvi.textview(box.findViewById(R.id.lay_total_moy_matricule), moyTotal.matricule);

            TextView vtotal_tri_1 = Tvi.textview(box.findViewById(R.id.lay_total_moy_total_trimestre_1), moyTotal.total1);
            TextView vtotal_tri_2 = Tvi.textview(box.findViewById(R.id.lay_total_moy_total_trimestre_2), moyTotal.total2);

            TextView vmoy_tri_1 = Tvi.textview(box.findViewById(R.id.lay_total_moy_moy_trimestre_1), moyTotal.moy1);
            TextView vmoy_tri_2 = Tvi.textview(box.findViewById(R.id.lay_total_moy_moy_trimestre_2), moyTotal.moy2);

            TextView vtotal = Tvi.textview(box.findViewById(R.id.lay_total_moy_total), moyTotal.total);
            TextView vmoy = Tvi.textview(box.findViewById(R.id.lay_total_moy_moyenne), moyTotal.moy);
            TextView vrang = Tvi.textview(box.findViewById(R.id.lay_total_moy_rang), moyTotal.rang);

            if (moyTotal.isFemme) {
                vprenom.setTextColor(Color.RED);
                vnom.setTextColor(Color.RED);
            }

            boxContent.addView(box);
        }

    }
    private static void print_semestre(){
        String sclasse = classe.getText().toString();
        int size = boxContent.getChildCount();

        for (int i = 0; i < size; i++){

            LinearLayout box = (LinearLayout) boxContent.getChildAt(i);
            MYear mYear = myear_from_view(box);
            MEleve elv = MEleve.fromMatricule(context, mYear.matricule);

            MEcole ecole = MEcole.fromCode(context);
            MMatiere[] matieres = MMatiere.fromClasse(context, sclasse);
            MNote1[] note1s = MNote1.fromCodeElv(context, elv.code);
            MNote2[] note2s = MNote2.fromCodeElv(context, elv.code);

            MMoy1 mMoy1s = MMoy1.fromCodeElv(context, elv.code);
            MMoy2 mMoy2s = MMoy2.fromCodeElv(context, elv.code);

            if (note1s == null || mMoy1s == null)
                continue;

            print_semestre_eleve(elv, mYear, ecole, matieres, note1s, note2s, mMoy1s, mMoy2s);
        }
    }
    private static void print_trimestre(){
        String sclasse = classe.getText().toString();
        int size = boxContent.getChildCount();

        for (int i = 0; i < size; i++){

                LinearLayout box = (LinearLayout) boxContent.getChildAt(i);
                MYear mYear = myear_from_view(box);
                MEleve elv = MEleve.fromMatricule(context, mYear.matricule);

                MEcole ecole = MEcole.fromCode(context);
                MMatiere[] matieres = MMatiere.fromClasse(context, sclasse);
                MNote1[] note1s = MNote1.fromCodeElv(context, elv.code);
                MNote2[] note2s = MNote2.fromCodeElv(context, elv.code);
                MNote3[] note3s = MNote3.fromCodeElv(context, elv.code);

                MMoy1 mMoy1s = MMoy1.fromCodeElv(context, elv.code);
                MMoy2 mMoy2s = MMoy2.fromCodeElv(context, elv.code);
                MMoy3 mMoy3s = MMoy3.fromCodeElv(context, elv.code);

                if (note1s == null || mMoy1s == null)
                    continue;

                print_trimestre_eleve(elv, mYear, ecole, matieres, note1s, note2s, note3s, mMoy1s, mMoy2s, mMoy3s);
        }
    }
    private static MYear myear_from_view(LinearLayout box){
        MYear myear = new MYear();
        myear.no = Tvi.text2str(box.findViewById(R.id.lay_total_moy_no));
        myear.prenom = Tvi.text2str(box.findViewById(R.id.lay_total_moy_prenom));
        myear.nom = Tvi.text2str(box.findViewById(R.id.lay_total_moy_nom));
        myear.matricule = Tvi.text2str(box.findViewById(R.id.lay_total_moy_matricule));

        myear.moy_tri1 = Tvi.text2str(box.findViewById(R.id.lay_total_moy_moy_trimestre_1));
        myear.moy_tri2 = Tvi.text2str(box.findViewById(R.id.lay_total_moy_moy_trimestre_2));
        myear.moy_tri3 = Tvi.text2str(box.findViewById(R.id.lay_total_moy_moy_trimestre_3));

        myear.total_tri1 = Tvi.text2str(box.findViewById(R.id.lay_total_moy_total_trimestre_1));
        myear.total_tri2 = Tvi.text2str(box.findViewById(R.id.lay_total_moy_total_trimestre_2));
        myear.total_tri3 = Tvi.text2str(box.findViewById(R.id.lay_total_moy_total_trimestre_3));

        myear.rang = Tvi.text2str(box.findViewById(R.id.lay_total_moy_rang));
        myear.total = Tvi.text2str(box.findViewById(R.id.lay_total_moy_total));
        myear.moy = Tvi.text2str(box.findViewById(R.id.lay_total_moy_moyenne));
        return myear;
    }

    private static String get_note1(MMatiere matiere, MNote1[] note1s){
        if (note1s == null)
            return "0.0";
        for (int i = 0; i < note1s.length; i++){
            MNote1 note = note1s[i];
            if (note.code_matiere.contentEquals(matiere.code)){
                return note.note;
            }
        }
        return "0.0";
    }
    private static String get_note2(MMatiere matiere, MNote2[] note2s){
        if (note2s == null)
            return "0.0";
        for (int i = 0; i < note2s.length; i++){
            MNote2 note = note2s[i];
            if (note.code_matiere.contentEquals(matiere.code)){
                return note.note;
            }
        }
        return "0.0";
    }
    private static String get_note3(MMatiere matiere, MNote3[] note3s){
        if (note3s == null)
            return "0.0";
        for (int i = 0; i < note3s.length; i++){
            MNote3 note = note3s[i];
            if (note.code_matiere.contentEquals(matiere.code)){
                return note.note;
            }
        }
        return "0.0";
    }
    private static void print_trimestre_eleve(MEleve elv, MYear myear, MEcole ecole, MMatiere[] matieres,
                                              MNote1[] note1s, MNote2[] note2s, MNote3[] note3s, MMoy1 moy1, MMoy2 moy2, MMoy3 moy3){

        int effectif = boxContent.getChildCount();
        StringBuilder html_str = new StringBuilder();

        html_str.append( PrtBulletinYear.head(context, ecole) );
        html_str.append( PrtBulletinYear.eleve(context, elv, myear, effectif) );
        html_str.append( Fil.rawFile2Str(context, R.raw.html_yearly_bulletin_table_head) );
        String html_table_line = Fil.rawFile2Str(context, R.raw.html_yearly_bulletin_table_body);

        for (int i = 0; i < matieres.length; i++){
            int no = i + 1;
            String str = new String(html_table_line);

            MMatiere matiere = matieres[i];
            String note1 = get_note1(matiere, note1s);
            String note2 = get_note2(matiere, note2s);
            String note3 = get_note3(matiere, note3s);
            double nte1 = TNum.f(note1);
            double nte2 = TNum.f(note2);
            double nte3 = TNum.f(note3);
            double total = nte1 + nte2 + nte3;
            if (total > 0.0) {
                int nb_div = 3;
                if (nte1 == 0) --nb_div;
                if (nte2 == 0) --nb_div;
                if (nte3 == 0) --nb_div;
                if (nb_div < 1) nb_div = 1;
                total = total / nb_div;
            }

            str = str.replace("$no$", TNum.iS(no));
            str = str.replace("$matiere$", matiere.name);
            str = str.replace("$note_tri1$", note1);
            str = str.replace("$note_tri2$", note2);
            str = str.replace("$note_tri3$", note3);
            str = str.replace("$note_year$", TNum.f2note(total));

            html_str.append(str);
        }

        String html_table_footer = PrtBulletinYear.table_footer(context, myear, moy1, moy2, moy3);
        html_str.append( html_table_footer);
        String html_end = PrtBulletinYear.footer(context);
        html_str.append( html_end) ;

        String fname_pdf = String.format("%s-%s-%s", elv.classe, elv.prenom, elv.nom);
        PrtBulletinYear.save_bulletin(context, new String(html_str), elv.classe, fname_pdf);
    }

    private static void print_semestre_eleve(MEleve elv, MYear myear, MEcole ecole, MMatiere[] matieres,
                                              MNote1[] note1s, MNote2[] note2s,  MMoy1 moy1, MMoy2 moy2){

        int effectif = boxContent.getChildCount();
        StringBuilder html_str = new StringBuilder();

        html_str.append( PrtBulletinSemestreYear.head(context, ecole) );
        html_str.append( PrtBulletinSemestreYear.eleve(context, elv, myear, effectif) );
        html_str.append( Fil.rawFile2Str(context, R.raw.html_yearly_bulletin_table_head_semestre) );
        String html_table_line = Fil.rawFile2Str(context, R.raw.html_yearly_bulletin_table_body_semestre);

        for (int i = 0; i < matieres.length; i++){
            int no = i + 1;
            String str = new String(html_table_line);

            MMatiere matiere = matieres[i];
            String note1 = get_note1(matiere, note1s);
            String note2 = get_note2(matiere, note2s);
            double nte1 = TNum.f(note1);
            double nte2 = TNum.f(note2);
            double total = nte1 + nte2;
            if (total > 0.0) {
                if (nte1 == 0.0 || nte2 == 0.0) total = total;
                else total = total / 2;
            }

            str = str.replace("$no$", TNum.iS(no));
            str = str.replace("$matiere$", matiere.name);
            str = str.replace("$note_tri1$", note1);
            str = str.replace("$note_tri2$", note2);
            str = str.replace("$note_year$", TNum.f2note(total));

            html_str.append(str);
        }

        String html_table_footer = PrtBulletinSemestreYear.table_footer(context, myear, moy1, moy2);
        html_str.append( html_table_footer);
        String html_end = PrtBulletinSemestreYear.footer(context);
        html_str.append( html_end) ;

        String fname_pdf = String.format("%s-%s-%s", elv.classe, elv.prenom, elv.nom);
        PrtBulletinYear.save_bulletin(context, new String(html_str), elv.classe, fname_pdf);
    }

}

