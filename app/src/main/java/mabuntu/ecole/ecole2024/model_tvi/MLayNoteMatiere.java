package mabuntu.ecole.ecole2024.model_tvi;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import mabuntu.ecole.ecole2024.model_table.MMatiere;
import mabuntu.ecole.ecole2024.model_table.MNote1;
import mabuntu.ecole.ecole2024.model_table.MNote2;
import mabuntu.ecole.ecole2024.model_table.MNote3;
import mabuntu.ecole.ecole2024.tools.TNum;
import mabuntu.tombolia.pressing.R;

public class MLayNoteMatiere {

    public LinearLayout box;
    public TextView no;
    public TextView code;
    public TextView matiere;
    public TextView coef;
    public EditText note;

    public MLayNoteMatiere(Context context, int layout_lay){
        this.box = (LinearLayout) LayoutInflater.from(context).inflate(layout_lay, null);
        init_item();
    }
    public MLayNoteMatiere(LinearLayout boxNote){
        this.box = boxNote;
        init_item();
    }

    private void init_item(){
        this.no = box.findViewById(R.id.lay_note_matiere_no);
        this.code = box.findViewById(R.id.lay_note_matiere_code);
        this.matiere = box.findViewById(R.id.lay_note_matiere_name);
        this.coef = box.findViewById(R.id.lay_note_matiere_coef);
        this.note = box.findViewById(R.id.lay_note_matiere_note);
    }

    public void fill_data_note1(int no, MNote1 note, MMatiere matiere){
        this.no.setText(TNum.iS( ++no));
        this.code.setText(note.code);
        this.matiere.setText(matiere.name);
        this.coef.setText(matiere.coef);
        this.note.setText("");
    }

    public void fill_data_note2(int no, MNote2 note, MMatiere matiere){
        this.no.setText(TNum.iS( ++no));
        this.code.setText(note.code);
        this.matiere.setText(matiere.name);
        this.coef.setText(matiere.coef);
        this.note.setText("");
    }
    public void fill_data_note3(int no, MNote3 note, MMatiere matiere){
        this.no.setText(TNum.iS( ++no));
        this.code.setText(note.code);
        this.matiere.setText(matiere.name);
        this.coef.setText(matiere.coef);
        this.note.setText("");
    }



}
