package mabuntu.ecole.ecole2024.tools;


import android.annotation.SuppressLint;
import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;

public class TNum {

    public static boolean isNull(String val){ return f(val) == 0.0; }

    @SuppressLint("DefaultLocale")
    public static String fS(double val){
        return String.format("%.2f", val);
    }
    public static int h2i(EditText field){ return h2i(field.getText()); }
    public static int h2i(TextView field){ return h2i(field.getText()); }
    public static int h2i(Editable label){ return h2i(label.toString()); }
    public static int h2i(CharSequence val){ return Integer.parseInt(h2i_cleanStr(val.toString())); }

    public static int h2i(String val){ return Integer.parseInt(h2i_cleanStr(val)); }
    private static String h2i_cleanStr(String val){
        if (val == null || val.isEmpty())
            return "0";

        if (val.contains("."))
            val = val.split("\\.")[0];
        val = val.replace((" "), (""));
        return val;
    }
    public static String h2iS(String val){ return Integer.toString(h2i(val)); }
    public static String h2iS(EditText field){ return Integer.toString(h2i(field)); }
    public static String h2iS(Editable field){ return Integer.toString(h2i(field)); }
    public static String h2iS(CharSequence label){ return Integer.toString(h2i(label)); }

    public static int f2i(String val){ return h2i(val);  }
    public static int f2i(Float val){ return h2i(val.toString());  }





    public static boolean isF(String val){ return isFloat(val); }
    public static boolean isFloat(String val){
        try {
            Float.parseFloat(val);
            return true;
        } catch (Exception e) { return false; }
    }

    public static float f(String val){
        if (val.isEmpty())
            return 0.0f;
        val = val.replace(",", ".");
        val = val.replace(" ", "");
        return Float.parseFloat(val);
    }

    public static String iS(int val){ return Integer.toString(val); }
    public static int i(String val){ return Integer.parseInt(val); }

    public static boolean isI(String value){
        return isInteger(value);
    }
    public static boolean isInteger(String value){
        try {
            if (value.isEmpty()) return false;
            for (int i = 0; i < value.length(); i++){
                if (! Character.isDigit(value.charAt(i))) return false;
            }
            return true;
        } catch (Exception e) { return false; }
    }


    public static boolean isInHu(String value){
        return isIntegerHuman(value);
    }



    public static String i2h(EditText value){ return i2h(value.getEditableText()); }
    public static String i2h(Editable value){ return int_to_human(value.toString()); }
    public static String i2h(int value){ return int_to_human(value); }

    public static String i2h(String value){
        return int_to_human(value);
    }


    public static boolean isIntegerHuman(String value){
        return isInteger(value.replaceAll(" ", ""));
    }


    public static String intToHuman(int value){
        return int_to_human(value + "");
    }

    public static String intToHuman(String val){
        return int_to_human(val);
    }

    public static String int_to_human(int value){ return int_to_human(Integer.toString(value)); }
    private static String int_to_human(String val){
        String reverse = str_reverse(val);
        StringBuffer human = new StringBuffer(1024);
        for (int i = 0, j = 1; i < reverse.length(); i++, j++){
            human.append(reverse.charAt(i));
            if (j%3 == 0)
                human.append(' ');
        }
        reverse = new String(human);
        return  str_reverse(reverse);
    }

    public static int c2i(String value){ return clean2i(value); }
    public static int clean2i(String value){ return clean_to_int(value); }
    public static int clean_to_int(String val){
        StringBuffer str = new StringBuffer(1024);
        for (int i = 0; i < val.length(); i++){
            Character ch = val.charAt(i);
            if (Character.isDigit(ch))
                str.append(ch);
        }
        return Integer.parseInt(new String(str));
    }

    public static long c2l(String value){ return clean2l(value); }
    public static long clean2l(String value){ return clean_to_long(value); }
    public static long clean_to_long(String val){
        StringBuffer str = new StringBuffer(1024);
        for (int i = 0; i < val.length(); i++){
            Character ch = val.charAt(i);
            if (Character.isDigit(ch))
                str.append(ch);
        }
        return Long.parseLong(new String(str));
    }




    public static String str_to_float(String val) {
        String str;
        if (!val.contains(",") && !val.contains(".")) {
            str = val + ".0";
        } else {
            str = val.replace(',', '.');
        }

        return str.equalsIgnoreCase(".0") ? "0.0" : str;
    }

    public static float to_float(String str) {
        try {
            return Float.parseFloat(h2f(str));
        } catch (Exception var2) {
            return 0.0F;
        }
    }

    public static String h2f(String val) {
        val = val.replace(" ", "");
        val = val.replace(",", ".");
        return str_to_float(val);
    }


    public static String human_to_int(String val) {
        StringBuffer str = new StringBuffer(1024);
        for(int i = 0; val.length() > i; ++i) {
            Character ch = val.charAt(i);
            if (Character.isDigit(ch)) {
                str.append(ch);
            } else if (ch == '.') {
                return String.valueOf(str);
            }
        }
        return String.valueOf(str);
    }

    public static String str_reverse(String str) {
        StringBuilder val = new StringBuilder();
        for(int i = str.length() - 1; i > -1; --i) {
            val.append(str.charAt(i));
        }
        return val.toString();
    }

    public static String f2h(int val){
        return float_to_human(Integer.toString(val));
    }
    @SuppressLint("DefaultLocale")
    public static String f2note(Double note){
        String snote = note.toString();
        if (snote.isEmpty() || snote.contains("NaN"))
            return "0.0";
        return String.format("%.2f", note);
    }
    public static String f2note(String note){
        if (note.isEmpty() || note.contains("NaN"))
            return "0.0";
        return f2note(Double.parseDouble(h2f(note)));
    }


    public static String f2h(String val){
        return float_to_human(val);
    }
    private static String float_to_human(String val) {
        try {
            String val0 = str_to_float(val);
            String real = val0.substring(val0.indexOf("."));
            String intt = ((int)Float.parseFloat(val0)) + "";
            int len = intt.length();
            if (len < 4) {
                return val0;
            } else {
                String str = "";
                val0 = str_reverse(intt);

                for(int i = 0; i < len && i < len; str = str + " ") {
                    str = str + val0.charAt(i++);
                    if (i >= len) {
                        break;
                    }

                    str = str + val0.charAt(i++);
                    if (i >= len) {
                        break;
                    }

                    str = str + val0.charAt(i++);
                }

                String var10000 = str_reverse(str);
                return var10000 + real;
            }
        } catch (Exception var7) {
            return "0.0";
        }
    }


}
