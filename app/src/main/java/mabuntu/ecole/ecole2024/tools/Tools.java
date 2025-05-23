package mabuntu.ecole.ecole2024.tools;

import android.util.Base64;
import android.util.Log;

public class Tools {

    public static void Exp(Exception e){
        Log.println(Log.DEBUG, "tools-ecole2024", e.getMessage());
    }

    public static String str2b64(String data){
        return Base64.encodeToString(data.getBytes(), Base64.DEFAULT);
    }

    public static String b642str(String data){
        return new String(Base64.decode(data.getBytes(), Base64.DEFAULT));
    }


}
