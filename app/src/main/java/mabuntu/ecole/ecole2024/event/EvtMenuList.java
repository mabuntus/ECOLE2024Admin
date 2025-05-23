package mabuntu.ecole.ecole2024.event;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import mabuntu.ecole.ecole2024.Apropos;
import mabuntu.ecole.ecole2024.Bulletin;
import mabuntu.ecole.ecole2024.Ecole;
import mabuntu.ecole.ecole2024.EleveList;
import mabuntu.ecole.ecole2024.EleveNew;
import mabuntu.ecole.ecole2024.MatiereList;
import mabuntu.ecole.ecole2024.MatiereNew;
import mabuntu.ecole.ecole2024.MoyTotal;
import mabuntu.ecole.ecole2024.Note;
import mabuntu.ecole.ecole2024.Statistique;

public class EvtMenuList {

    public static Context context;
    public static TextView eleveNew;
    public static TextView eleveList;
    public static TextView matiereNew;
    public static TextView matiereList;
    public static TextView note;
    public static TextView bulletin;
    public static TextView statistique;
    public static TextView moyTotal;
    public static TextView apropos;
    public static TextView ecole;
    public static TextView synchroShare;
    public static TextView synchroSynchro;
    public static TextView imported;
    public static TextView exported;


    public static void clickEleveNew(){
        Intent intent = new Intent(context, EleveNew.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public static void clickEleveList(){
        Intent intent = new Intent(context, EleveList.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public static void clickMatiereNew(){
        Intent intent = new Intent(context, MatiereNew.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public static void clickMatiereList(){
        Intent intent = new Intent(context, MatiereList.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public static void clickNote(){
        Intent intent = new Intent(context, Note.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public static void clickBulletin(){
        Intent intent = new Intent(context, Bulletin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public static void clickStatistique(){
        Intent intent = new Intent(context, Statistique.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public static void clickMoyTotal(){
        Intent intent = new Intent(context, MoyTotal.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void clickEcole(){
        Intent intent = new Intent(context, Ecole.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void clickApropos(){
        Intent intent = new Intent(context, Apropos.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void clickExported(){

    }
    public static void clickImported(){

    }

}

