package mabuntu.ecole.ecole2024.tools;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class FileExtern {

    public static File save(String fname, String data){
        try {
            File root = android.os.Environment.getExternalStorageDirectory();
            File dir = new File(String.format("%s%s", root.getAbsolutePath(), "/bulletins"));
            dir.mkdirs();

            dir = new File(String.format("%s%s/%s", root.getAbsolutePath(), "/bulletins", fname));
            OutputStream os = new FileOutputStream(dir);
            os.write(data.getBytes());
            os.close();
            return new File(String.format("%s%s/%s", root.getAbsolutePath(), "/bulletins", fname));
        } catch (Exception e){
            Log.println(Log.DEBUG, "save", e.getMessage());
            return null;
        }
    }


    public static File save(String fname, String data, String path){
        try {
            File root = android.os.Environment.getExternalStorageDirectory();
            File dir = new File(String.format("%s%s", root.getAbsolutePath(), "/" + path));
            dir.mkdirs();

            dir = new File(String.format("%s%s/%s", root.getAbsolutePath(), "/" + path, fname));
            OutputStream os = new FileOutputStream(dir);
            os.write(data.getBytes());
            os.close();
            return new File(String.format("%s%s/%s", root.getAbsolutePath(), "/" + path, fname));
        } catch (Exception e){
            Log.println(Log.DEBUG, "save", e.getMessage());
            return null;
        }
    }


}
