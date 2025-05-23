package mabuntu.ecole.ecole2024.tvi;

import static mabuntu.ecole.ecole2024.event.EvtMatiereList.*;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import mabuntu.ecole.ecole2024.model_table.MMatiere;
import mabuntu.ecole.ecole2024.tools.TNum;
import mabuntu.tombolia.pressing.R;

public class TviMatiereList {

    public static void clean_box(){
        boxContent.removeAllViews();
    }

    public static void fill_box(){
        String sclasse = classe.getText().toString();
        MMatiere[] matieres = MMatiere.fromClasse(context, sclasse);
        if (matieres == null) return;

        for(int i = 0; i < matieres.length; i++){
            MMatiere obj = matieres[i];
            int no = i + 1;
            fill_box(no, obj);
        }

    }
    private static void fill_box(int no, MMatiere obj){
        LinearLayout box = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.lay_matiere_list, null);
        TextView vno = box.findViewById(R.id.lay_matiere_list_no);
        TextView vname = box.findViewById(R.id.lay_matiere_list_name);
        TextView vcoef = box.findViewById(R.id.lay_matiere_list_coef);
        TextView vBtnModify = box.findViewById(R.id.lay_matiere_list_btnModify);
        TextView vBtnRemove = box.findViewById(R.id.lay_matiere_list_btnRetirer);
        LinearLayout boxModify = box.findViewById(R.id.lay_matiere_list_box_modify);
        EditText vename = box.findViewById(R.id.lay_matiere_list_name_modify);
        EditText vecoef = box.findViewById(R.id.lay_matiere_list_coef_modify);

        vno.setText(TNum.iS(no));
        vname.setText(obj.name);
        vcoef.setText(obj.coef);
        vename.setText(obj.name);
        vecoef.setText(obj.coef);

        vBtnModify.setOnClickListener(v -> {
            boxModify.setVisibility(View.VISIBLE);
            obj.name = vename.getText().toString();
            obj.coef = vecoef.getText().toString();
            if (MMatiere.updt(context, obj)) {
                vname.setText(vename.getText());
                vcoef.setText(vecoef.getText());
            }
        });
        vBtnRemove.setOnClickListener(v -> {
            MMatiere.del(context, obj.code);
            boxContent.removeView(box);
            refresh_boxContent_no();
        });
        boxContent.addView(box);
    }

    private static void refresh_boxContent_no(){
        int size = boxContent.getChildCount();
        for(int i = 0; i < size; i++){
            int no = i + 1;
            LinearLayout box = (LinearLayout) boxContent.getChildAt(i);
            TextView vno = box.findViewById(R.id.lay_matiere_list_no);
            vno.setText(TNum.iS(no));
        }
    }


}
