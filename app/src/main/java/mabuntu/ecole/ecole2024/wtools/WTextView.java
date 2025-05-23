package mabuntu.ecole.ecole2024.wtools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class WTextView extends TextView {

    public WTextView(Context context){
        super(context);
    }

    public void set(String txt){ this.setText(txt); }

    public String get(){ return this.getText().toString();}


}
