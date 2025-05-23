package mabuntu.ecole.ecole2024.cntrol;

import static mabuntu.ecole.ecole2024.event.EvtMenuList.*;

import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import mabuntu.ecole.ecole2024.model_table.MEleve;
import mabuntu.ecole.ecole2024.tools.Tools;

public class CntMenuList {

    private static String COL = ",";
    private static String LINE = ";";
    private static String MAP = "@";


    public static boolean hasEleve(){
        return MEleve.getAll(context) != null;
    }

    public static String file_is_valid(Uri uri){
        try {
            InputStream ips = context.getContentResolver().openInputStream(uri);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            int count;
            byte[] buffer = new byte[1024];
            while (true){
                count = ips.read(buffer);
                if (count <= 0)
                    break;
                outputStream.write(buffer, 0, count);
            }
            byte[] bytes = outputStream.toByteArray();
            ips.close();
            outputStream.close();

            String data = new String(bytes);

            String[] tab = data.split(LINE);
            String classe_prof = Tools.b642str(tab[0]);
            if (tab.length == 10 && TCntStr.is_classe(classe_prof))
                return data;
            return null;
        }
        catch (Exception e){
            Tools.Exp(e);
            return null;
        }
    }



}
