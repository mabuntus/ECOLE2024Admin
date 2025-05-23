package mabuntu.ecole.ecole2024.model_tvi;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import mabuntu.ecole.ecole2024.model_table.MEleve;
import mabuntu.ecole.ecole2024.tools.TNum;
import mabuntu.tombolia.pressing.R;

public class MLayNote {

    public LinearLayout box;
    public TextView title;
    public TextView prenom;
    public TextView nom;
    public TextView matricule;
    public TextView total;
    public TextView moy;

    public TextView btnPrev;
    public TextView btnSave;
    public TextView btnNext;


    public LinearLayout boxNote;

    public MLayNote(Context context, int lay_layout){
        LinearLayout boxElv = this.box = (LinearLayout) LayoutInflater.from(context).inflate(lay_layout, null);

        this.title = boxElv.findViewById(R.id.lay_note_title);
        this.prenom = boxElv.findViewById(R.id.lay_note_prenom);
        this.nom = boxElv.findViewById(R.id.lay_note_nom);
        this.matricule = boxElv.findViewById(R.id.lay_note_matricule);
        this.total = boxElv.findViewById(R.id.lay_note_total);
        this.moy = boxElv.findViewById(R.id.lay_note_moy);

        this.btnPrev = boxElv.findViewById(R.id.lay_note_btn_prev);
        this.btnSave = boxElv.findViewById(R.id.lay_note_btn_save);
        this.btnNext = boxElv.findViewById(R.id.lay_note_btn_next);

        this.boxNote = boxElv.findViewById(R.id.lay_note_box_matiere);
    }

    public void set_eleve(MEleve elv, int indiceElv, int elv_length){
        String stitle = String.format("Eleve %s/%s", TNum.iS(++indiceElv), TNum.iS(elv_length));

        this.title.setText(stitle);
        this.prenom.setText(elv.prenom);
        this.nom.setText(elv.nom);
        this.matricule.setText(elv.matricule);
    }


}
