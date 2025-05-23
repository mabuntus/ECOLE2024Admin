package mabuntu.ecole.ecole2024.tvi;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import mabuntu.ecole.ecole2024.tools.TNum;

public class Tvi {

    public static int idtrimestre(EditText editText){
        String trimestre = str(editText);
        return trimestre.contains("1") ? 1 : trimestre.contains("2") ? 2 : 3;
    }

    public static int inT(TextView textView){ return TNum.h2i(str(textView)); }
    public static int inT(EditText editText){ return TNum.h2i(str(editText)); }


    public static float floaT(TextView textView){ return TNum.f(str(textView)); }
    public static float floaT(EditText editText){ return TNum.f(str(editText)); }


    public static String str(TextView textView){ return textView.getText().toString(); }
    public static String str(EditText editText){ return editText.getText().toString(); }



    public static String text2str(TextView textView){
        return textView.getText().toString();
    }
    public static String edit2str(EditText editText){
        return editText.getText().toString();
    }

    public static TextView textview(View view, String value){
        TextView textView = (TextView)view;
        textView.setText(value);
        return textView;
    }

}
