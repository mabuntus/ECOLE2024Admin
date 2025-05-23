package mabuntu.ecole.ecole2024.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TDate {

    public static String dateFull() {
        long sec = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date resultdate = new Date(sec);
        return sdf.format(resultdate).toString();
    }
    public static  String date() {
        long sec = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date resultdate = new Date(sec);
        return sdf.format(resultdate).toString();
    }

    public static  String date(int t) {
        long sec = t * (long)1000;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date resultdate = new Date(sec);
        return sdf.format(resultdate).toString();
    }

}

