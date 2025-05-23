package mabuntu.ecole.ecole2024.model_table;

import android.content.Context;

import java.util.ArrayList;

import mabuntu.ecole.ecole2024.tools.Fil;
import mabuntu.ecole.ecole2024.tools.Tools;

public class TDb {

    public static String line = ";";
    public static String col = ",";


    public static boolean add(Context context, String fname, String ...cols){
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < cols.length; i++){
            String str = Tools.str2b64(cols[i]);
            builder.append(str).append(col);
        }
        String data = builder.substring(0, builder.length()-1);
        data = String.format("%s%s", data, line);
        Fil.append(context, fname, data);
        return true;
    }

    public static boolean updt(Context context, String fname, String ...args){
        if (!Fil.exists(context, fname))
            return false;

        String data = Fil.get(context, fname);
        if (data.isEmpty() || !data.contains(line))
            return false;

        ArrayList<String> lines = new ArrayList<>();
        String code = Tools.str2b64(args[0]);
        String[] tab = data.split(line);
        for (int i = 0; i < tab.length; i++) {
            if (tab[i].contains(col)) {

                String[] t = tab[i].split(col);
                if (!t[0].contentEquals(code)) {
                    lines.add(String.format("%s%s", tab[i], line));
                } else {
                    StringBuilder builder = new StringBuilder();
                    for (int j = 0; j < args.length; j++) {
                        String str = Tools.str2b64(args[j]);
                        builder.append(str).append(col);
                    }
                    String val = builder.substring(0, builder.length() - 1);
                    val = String.format("%s%s", val, line);
                    lines.add(val);
                }
            }
        }
        context.deleteFile(fname);
        for(String val : lines){
            Fil.append(context, fname, String.format("%s%s", val, line));
        }
        return true;
    }


    public static boolean updt(Context context, String fname, ArrayList<String[]> list_args){
        if (!Fil.exists(context, fname))
            return false;

        String data = Fil.get(context, fname);
        if (data.isEmpty() || !data.contains(line))
            return true;

        ArrayList<String> lines = new ArrayList<>();
        String[] tab = data.split(line);
        for (int i = 0; i < tab.length; i++) {
            if (tab[i].contains(col)) {

                for (String[] args : list_args) {
                    String[] t = tab[i].split(col);
                    String code = Tools.str2b64(args[0]);

                    if (!t[0].contentEquals(code)) {
                        lines.add(String.format("%s%s", tab[i], line));
                    } else {
                        StringBuilder builder = new StringBuilder();
                        for (int j = 0; j < args.length; j++) {
                            String str = Tools.str2b64(args[j]);
                            builder.append(str).append(col);
                        }
                        String val = builder.substring(0, builder.length() - 1);
                        val = String.format("%s%s", val, line);
                        lines.add(val);
                    }
                }
            }
        }
        for(String val : lines){
            Fil.put(context, fname, String.format("%s%s", val, line));
        }
        return true;
    }


    public static boolean rmv(Context context, String fname, String code){
        return rmv(context, fname, 0, code);
    }
    public static boolean rmv(Context context, String fname, int colNiv, String colValue){
        if (!Fil.exists(context, fname))
            return false;

        String data = Fil.get(context, fname);
        if (data.isEmpty() || !data.contains(line))
            return true;

        ArrayList<String> lines = new ArrayList<>();
        colValue = Tools.str2b64(colValue);
        String[] tab = data.split(line);
        for (int i = 0; i < tab.length; i++) {
            if (tab[i].contains(col)) {
                String[] t = tab[i].split(col);
                if (!t[colNiv].contentEquals(colValue))
                    lines.add(tab[i]);
            }
        }

        context.deleteFile(fname);
        for(String val : lines){
            Fil.append(context, fname, String.format("%s%s", val, line));
        }
        return true;
    }

    public static String[] get(Context context, String fname, String code){
        return get(context, fname, 0, code);
    }
    public static String[] get(Context context, String fname, int colNiv, String colVal){
        colVal = Tools.str2b64(colVal);

        if (!Fil.exists(context, fname))
            return null;

        String[] tab = Fil.get(context, fname).split(line);
        for(String val : tab){
            String[] t = val.split(col);
            if (t.length > colNiv && t[colNiv].contentEquals(colVal)){
                return t;
            }
        }
        return null;
    }
    public static ArrayList<String[]> getAll(Context context, String fname, int colNiv, String colVal){
        colVal = Tools.str2b64(colVal);

        if (!Fil.exists(context, fname))
            return null;

        ArrayList<String[]> list = new ArrayList<>();
        String[] tab = Fil.get(context, fname).split(line);
        for(String val : tab){
            if (val.contains(col)) {
                String[] t = val.split(col);
                if (t[colNiv].contentEquals(colVal)) {
                    list.add(t);
                }
            }
        }
        return list.isEmpty() ? null : list;
    }

    public static ArrayList<String[]> getAll(Context context, String fname){
        if (!Fil.exists(context, fname))
            return null;

        ArrayList<String[]> list = new ArrayList<>();
        String[] tab = Fil.get(context, fname).split(line);
        for(String val : tab){
            if (val.contains(col)) {
                String[] t = val.split(col);
                list.add(t);
            }
        }
        return list.isEmpty() ? null : list;
    }

}
