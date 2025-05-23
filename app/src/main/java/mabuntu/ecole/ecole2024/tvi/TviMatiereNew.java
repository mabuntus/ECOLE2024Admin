package mabuntu.ecole.ecole2024.tvi;

import static mabuntu.ecole.ecole2024.event.EvtMatiereNew.*;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import mabuntu.ecole.ecole2024.model_table.MMatiere;
import mabuntu.ecole.ecole2024.tools.TCode;
import mabuntu.ecole.ecole2024.tools.TNum;
import mabuntu.tombolia.pressing.R;

public class TviMatiereNew {

    public static void clean_matieres(){
        matiere.setText("");
        coef.setText("1");
    }
    public static void clean_all(){
        clean_matieres();
        classe.setText("");
        boxContent.removeAllViews();
    }

    @SuppressLint("DefaultLocale")
    public static void addMatiere_to_box(){
        LinearLayout box = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.lay_matiere_new, null);
        TextView vno = box.findViewById(R.id.lay_matiere_new_no);
        TextView vname = box.findViewById(R.id.lay_matiere_new_name);
        TextView vcoef = box.findViewById(R.id.lay_matiere_new_coef);
        TextView vBtnModify = box.findViewById(R.id.lay_matiere_new_btnModifier);
        TextView vBtnRemove = box.findViewById(R.id.lay_matiere_new_btnRetirer);

        vno.setText(String.format("%d", boxContent.getChildCount() + 1));
        vname.setText(matiere.getText());
        vcoef.setText(coef.getText());

        vBtnRemove.setOnClickListener(v -> {
            boxContent.removeView(box);
            refresh_box_no();
        });
        vBtnModify.setOnClickListener(v -> {
            matiere.setText(vname.getText());
            coef.setText(vcoef.getText());
            boxContent.removeView(box);
            refresh_box_no();
        });
        boxContent.addView(box);
    }
    private static void refresh_box_no(){
        int size = boxContent.getChildCount();
        for(int i = 0; i < size; i++){
            int no = i + 1;
            LinearLayout box = (LinearLayout) boxContent.getChildAt(i);
            TextView vno = box.findViewById(R.id.lay_matiere_new_no);
            vno.setText(TNum.iS(no));
        }
    }

    public static void save(){
        int size = boxContent.getChildCount();
        String sclasse = classe.getText().toString();

        for (int i = 0; i < size; i++){
            LinearLayout box = (LinearLayout) boxContent.getChildAt(i);
            TextView vname = box.findViewById(R.id.lay_matiere_new_name);
            TextView vcoef = box.findViewById(R.id.lay_matiere_new_coef);

            String sname = vname.getText().toString();
            String scoef = vcoef.getText().toString();
            save_item(sname, scoef, sclasse);
        }
    }
    private static void save_item(String name, String coef, String classe){
        MMatiere.add(context, TCode.matiere(), name, coef, classe);
    }


}
