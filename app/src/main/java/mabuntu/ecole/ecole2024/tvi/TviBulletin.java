package mabuntu.ecole.ecole2024.tvi;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import mabuntu.ecole.ecole2024.cntrol.TCntStr;
import mabuntu.ecole.ecole2024.model_table.MEcole;
import mabuntu.ecole.ecole2024.model_table.MEleve;
import mabuntu.ecole.ecole2024.model_table.MMatiere;
import mabuntu.ecole.ecole2024.model_table.MMoy;
import mabuntu.ecole.ecole2024.model_table.MMoy1;
import mabuntu.ecole.ecole2024.model_table.MMoy2;
import mabuntu.ecole.ecole2024.model_table.MMoy3;
import mabuntu.ecole.ecole2024.model_table.MNote1;
import mabuntu.ecole.ecole2024.model_table.MNote2;
import mabuntu.ecole.ecole2024.model_table.MNote3;
import mabuntu.ecole.ecole2024.print.PrtBulletinTrimestre;
import mabuntu.ecole.ecole2024.print.PrtTools;
import mabuntu.ecole.ecole2024.tools.TMap;
import mabuntu.ecole.ecole2024.tools.TNum;
import mabuntu.tombolia.pressing.R;

import static mabuntu.ecole.ecole2024.event.EvtBulletin.*;

public class TviBulletin {

    public static void clean_box(){
        boxContent.removeAllViews();
    }

    public static void full_box(){
        String sclasse = classe.getText().toString();
        int idTrimestre = TCntStr.idTrimestre(trimestre);
        ArrayList<MEleve> list_eleve = new ArrayList<>();
        HashMap<String, Double> hash_note = new HashMap<>();

        MEleve[] eleves = MEleve.fromClasse(context, sclasse);
        for(int i = 0; i < eleves.length; i++){
            MEleve elv = eleves[i];
            Double moy = 0.0;

            if (idTrimestre == 1) {
                MMoy1 mmoy = MMoy1.fromCodeElv(context, elv.code);
                if (mmoy == null) continue;
                moy = (double) TNum.f(mmoy.moy);
            }
            else if (idTrimestre == 2){
                MMoy2 mmoy = MMoy2.fromCodeElv(context, elv.code);
                if (mmoy == null) continue;
                moy = (double) TNum.f(mmoy.moy);
            }
            else if (idTrimestre == 3){
                MMoy3 mmoy = MMoy3.fromCodeElv(context, elv.code);
                if (mmoy == null) continue;
                moy = (double) TNum.f(mmoy.moy);
            }

            list_eleve.add(eleves[i]);
            hash_note.put(elv.code, moy);
        }
        hash_note = TMap.sortByValue(hash_note);
        full_box_data(list_eleve, hash_note, idTrimestre);
    }

    private static void full_box_data(ArrayList<MEleve> list_eleve, HashMap<String, Double> hash_note, int idtrimestre){
        int i = 0;
        MMoy[] mMoys = new MMoy[hash_note.size()];

        for(String codeElv : hash_note.keySet()){
            if (idtrimestre == 1){
                MMoy1 mmoy = MMoy1.fromCodeElv(context, codeElv);
                mMoys[i++] = new MMoy(mmoy.code_eleve, mmoy.total, mmoy.moy, idtrimestre);
            }
            else if (idtrimestre == 2){
                MMoy2 mmoy = MMoy2.fromCodeElv(context, codeElv);
                mMoys[i++] = new MMoy(mmoy.code_eleve, mmoy.total, mmoy.moy, idtrimestre);
            }
            else if (idtrimestre == 3){
                MMoy3 mmoy = MMoy3.fromCodeElv(context, codeElv);
                mMoys[i++] = new MMoy(mmoy.code_eleve, mmoy.total, mmoy.moy, idtrimestre);
            }
        }
        full_box_rang(mMoys);
        moy_set_rang(mMoys);

        int no = 1;
        for(MMoy moy : mMoys){
            for(MEleve elv : list_eleve){
                if (elv.code.contentEquals(moy.codeElv)) {
                    fill_box_view(no++, elv, moy);
                    break;
                }
            }
        }
    }

    private static void moy_set_rang(MMoy[] mMoys){
        for (MMoy moy : mMoys){
            if (moy.trimestre == 1) {
                MMoy1 mmoy = MMoy1.fromCodeElv(context, moy.codeElv);
                mmoy.code_matiere = moy.rang;
                MMoy1.updt(context, mmoy);
            }
            else if (moy.trimestre == 2){
                MMoy2 mmoy = MMoy2.fromCodeElv(context, moy.codeElv);
                mmoy.code_matiere = moy.rang;
                MMoy2.updt(context, mmoy);
            }
            else if (moy.trimestre == 3){
                MMoy3 mmoy = MMoy3.fromCodeElv(context, moy.codeElv);
                mmoy.code_matiere = moy.rang;
                MMoy3.updt(context, mmoy);
            }
        }
    }
    private static void full_box_rang(MMoy[] mmoys){
        for (int i = 0; i < mmoys.length; i++){
            if (i == 0)
                mmoys[i].rang = "1er";
            else {
                MMoy prev = mmoys[i-1];
                MMoy now = mmoys[i];
                if (prev.moy.contentEquals(now.moy)) {
                    if (prev.rang.contains("exe"))
                        now.rang = prev.rang;
                    else
                        now.rang = String.format("%s exe", prev.rang);
                }
                else {
                    now.rang = String.format("%seme", TNum.iS(i+1));
                }
            }
        }
    }

    private static void fill_box_view(int no, MEleve elv, MMoy mmoy){
        LinearLayout box = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.lay_bulletin, null);
        TextView vno = box.findViewById(R.id.lay_bulletin_no);
        TextView vprenom = box.findViewById(R.id.lay_bulletin_prenom);
        TextView vnom = box.findViewById(R.id.lay_bulletin_nom);
        TextView vmatricule = box.findViewById(R.id.lay_bulletin_matricule);
        TextView vtotal = box.findViewById(R.id.lay_bulletin_total);
        TextView vmoy = box.findViewById(R.id.lay_bulletin_moy);
        TextView vrang = box.findViewById(R.id.lay_bulletin_rang);

        vno.setText(TNum.iS(no));
        vprenom.setText(elv.prenom);
        vnom.setText(elv.nom);
        vmatricule.setText(elv.matricule);
        vtotal.setText(TNum.f2note(mmoy.total));
        vmoy.setText(TNum.f2note(mmoy.moy));
        vrang.setText(mmoy.rang);

        if (elv.sexe.contains("0")){
            vprenom.setTextColor(Color.RED);
            vnom.setTextColor(Color.RED);
        }
        boxContent.addView(box);
    }


    private static String moy_in_box(int indice){
        LinearLayout box = (LinearLayout) boxContent.getChildAt(indice);
        String moy = Tvi.text2str(box.findViewById(R.id.lay_bulletin_moy));
        return moy;
    }
    public static void print_bulletin(){
        String sclasse = classe.getText().toString();
        int effectif = boxContent.getChildCount();
        MEcole ecole = MEcole.fromCode(context);
        MMatiere[] matieres = MMatiere.fromClasse(context, sclasse);
        if (effectif < 1 || matieres == null || ecole == null)
            return;

        String moy_high = moy_in_box(0);
        String moy_low = moy_in_box(effectif - 1);

        for(int i = 0; i < effectif; i++){
            LinearLayout box = (LinearLayout) boxContent.getChildAt(i);
            MMoy mmoy = new MMoy();

            String matricule = Tvi.text2str(box.findViewById(R.id.lay_bulletin_matricule));
            mmoy.total = Tvi.text2str(box.findViewById(R.id.lay_bulletin_total));
            mmoy.moy = Tvi.text2str(box.findViewById(R.id.lay_bulletin_moy));
            mmoy.rang = Tvi.text2str(box.findViewById(R.id.lay_bulletin_rang));

            MEleve elv = MEleve.fromMatricule(context, matricule);
            mmoy.codeElv = elv.code;

            print_bulletin_eleve(ecole, effectif, mmoy, elv, matieres, moy_low, moy_high);
        }
    }
    private static void print_bulletin_eleve(MEcole ecole, int effectif, MMoy mmoy, MEleve elv, MMatiere[] matieres, String moy_low, String moy_high){
        int idtrimeste = TCntStr.idTrimestre(trimestre);
        String html_head = PrtBulletinTrimestre.head(context, ecole, trimestre);
        String html_elv = PrtBulletinTrimestre.eleve(context, elv, mmoy, effectif, trimestre);
        String html_matiere_head = PrtBulletinTrimestre.matiere_head(context, matieres, moy_low, moy_high);
        String html_matiere_line = PrtBulletinTrimestre.matiere_line(context);
        String html_footer = PrtBulletinTrimestre.footer(context);

        StringBuilder html_matiere = new StringBuilder();
        for (int i = 0; i < matieres.length; i++){
            MMatiere matiere = matieres[i];
            String str_line = new String(html_matiere_line);
            int no = i + 1;

            str_line = str_line.replace("$no$", TNum.iS(no));
            str_line = str_line.replace("$matiere$", matiere.name);

            if (idtrimeste == 1) {
                MNote1 note = MNote1.fromElvMatiere(context, elv.code, matiere.code);
                if (note != null) str_line = str_line.replace("$note$", PrtTools.note(note.note));
            }
            else if (idtrimeste == 2) {
                MNote2 note = MNote2.fromElvMatiere(context, elv.code, matiere.code);
                if (note != null) str_line = str_line.replace("$note$", PrtTools.note(note.note));
            }
            else if (idtrimeste == 3) {
                MNote3 note = MNote3.fromElvMatiere(context, elv.code, matiere.code);
                if (note != null) str_line = str_line.replace("$note$", PrtTools.note(note.note));
            }

            html_matiere.append(str_line);
        }
        StringBuilder html = new StringBuilder();
        html.append(html_head);
        html.append(html_elv);
        html.append(html_matiere_head);
        html.append(html_matiere);
        html.append(html_footer);

        String fname_pdf = String.format("%s-%s-%s", elv.classe, elv.prenom, elv.nom);
        PrtBulletinTrimestre.save_bulletin(context, new String(html), elv.classe, fname_pdf);
    }





}
