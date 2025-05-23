package mabuntu.ecole.ecole2024.wtools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;

@SuppressLint("AppCompatCustomView")
public class WEditText extends EditText {

    public WEditText(Context context) {
        super(context);
    }

    public void set(String txt){ this.setText(txt); }

    public String get(){ return this.getText().toString();}


}
