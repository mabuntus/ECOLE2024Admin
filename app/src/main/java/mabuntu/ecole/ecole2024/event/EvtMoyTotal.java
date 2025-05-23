package mabuntu.ecole.ecole2024.event;

import android.content.Context;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import mabuntu.ecole.ecole2024.cntrol.CntMoyTotal;
import mabuntu.ecole.ecole2024.cntrol.TCntrol;
import mabuntu.ecole.ecole2024.tvi.TviMoyTotal;

public class EvtMoyTotal {

    public static Context context;
    public static AutoCompleteTextView classe;
    public static TextView btnPrint;
    public static LinearLayout boxContent;

    public static void clickPrint(){
        if (CntMoyTotal.boxIsFull()) {
            if (CntMoyTotal.ecoleIsFull())
                TviMoyTotal.print();
            else
                Toast.makeText(context, "ecole informations", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(context, "liste vide", Toast.LENGTH_LONG).show();
    }

    public static void selectClasse(){
        if (CntMoyTotal.classeIsFull()) {
            TviMoyTotal.full_box();
        }
        else
            Toast.makeText(context, "matiere ou classe vide", Toast.LENGTH_LONG).show();
    }


}
