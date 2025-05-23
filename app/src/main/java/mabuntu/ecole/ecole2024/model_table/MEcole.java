package mabuntu.ecole.ecole2024.model_table;

import android.content.Context;
import android.graphics.Bitmap;

import mabuntu.ecole.ecole2024.tools.FILE;
import mabuntu.ecole.ecole2024.tools.Fil;
import mabuntu.ecole.ecole2024.tools.Tools;
import mabuntu.tombolia.pressing.R;

public class MEcole {

    public String code;
    public String mepua;
    public String ire;
    public String dce;
    public String pays;
    public String devise;
    public String ecole;
    public String slogan;
    public String etablissement;
    public String cycle;
    public String addresse;
    public String telephone;

    private static final int TAB_SIZE = 12;
    private static String fname = FILE.ecole;

    private static int niv_code = 0;
    private int niv_mepua = 1;
    private int niv_ire = 2;
    private int niv_dce = 3;
    private int niv_pays = 4;
    private int niv_devise = 5;
    private int niv_ecole = 6;
    private int niv_slogan = 7;
    private int niv_etablissement = 8;
    private int niv_cycle = 9;
    private int niv_addresse = 10;
    private int niv_telephone = 11;


    private static String code_str = "ecole-code";
    public MEcole(){
        this("ecole-code",  " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ");
    }
    public MEcole(String code,
                  String mepua,
                  String ire,
                  String dce,
                  String pays,
                  String devise,
                  String ecole,
                  String slogan,
                  String etablissement,
                  String cycle,
                  String addresse,
                  String telephone){
        this.code = code;
        this.mepua = mepua;
        this.ire = ire;
        this.dce = dce;
        this.pays = pays;
        this.devise = devise;
        this.ecole = ecole;
        this.slogan = slogan;
        this.etablissement = etablissement;
        this.cycle = cycle;
        this.addresse = addresse;
        this.telephone = telephone;
    }

    public static boolean add(Context context, MEcole ecole){
        return TDb.add(context, fname, ecole.code, ecole.mepua, ecole.ire, ecole.dce, ecole.pays, ecole.devise, ecole.ecole, ecole.slogan, ecole.etablissement
                , ecole.cycle, ecole.addresse, ecole.telephone);
    }


    public static boolean add(Context context,
                           String code,
                           String mepua,
                           String ire,
                           String dce,
                           String pays,
                           String devise,
                           String ecole,
                           String slogan,
                           String etablissement,
                           String cycle,
                           String addresse,
                           String telephone){
        return TDb.add(context, fname, code, mepua, ire, dce, pays, devise, ecole, slogan, etablissement, cycle, addresse, telephone);
    }
    public static boolean create(Context context){
        MEcole mEcole = new MEcole();
        mEcole.code = code_str;
        mEcole.mepua = "MEPUA";
        mEcole.ire = "Conakry";
        mEcole.dce = "Matoto";
        mEcole.pays = "Republique de Guinee";
        mEcole.devise = "Travail - Justice - Solidarite";
        mEcole.ecole = "GS ECOLE";
        mEcole.slogan = "Chemin de la REUSSITE";
        mEcole.etablissement = "Etablissement d'Enseignement General";
        mEcole.cycle = "Maternelle - Primaire - Secondaire";
        mEcole.addresse = "Matoto Centre";
        mEcole.telephone = "224 622 99 58 63 / 224 655 88 31 21";
        return add(context, mEcole);
    }
    public static void create_logo(Context context){
        Bitmap bitmap = Fil.res2bitmap(context, R.mipmap.ecole_foreground);
        Fil.bitmap2file(context,  bitmap, FILE.ecole_photo());
    }
    public static void set_logo(Context context, Bitmap bitmap){
        Fil.bitmap2file(context,  bitmap, FILE.ecole_photo());
    }
    public static Bitmap get_logo(Context context){
        return Fil.file2bitmap(context, FILE.ecole_photo());
    }
    public static String logo_base64(Context context){
        return Fil.file2base64(context, FILE.ecole_photo());
    }

    public static MEcole fromCode(Context context){
        String[] t = TDb.get(context, fname, niv_code, code_str);
        if (t == null || t.length != TAB_SIZE) {
            create(context);
            create_logo(context);
            t = TDb.get(context, fname, niv_code, code_str);
            return (t == null || t.length != TAB_SIZE) ? null : tab(t);
        }
        else {
            return tab(t);
        }
    }

    public static boolean updt(Context context, MEcole ecole){
        return TDb.updt(context, fname, ecole.code, ecole.mepua, ecole.ire, ecole.dce
                , ecole.pays, ecole.devise, ecole.ecole, ecole.slogan, ecole.etablissement, ecole.cycle, ecole.addresse, ecole.telephone);
    }

    public static MEcole tab(String[] tab){
        String code = Tools.b642str(tab[0]);
        String mepua = Tools.b642str(tab[1]);
        String ire = Tools.b642str(tab[2]);
        String dce = Tools.b642str(tab[3]);
        String pays = Tools.b642str(tab[4]);
        String devise = Tools.b642str(tab[5]);
        String ecole = Tools.b642str(tab[6]);
        String slogan = Tools.b642str(tab[7]);
        String etablissement = Tools.b642str(tab[8]);
        String cycle = Tools.b642str(tab[9]);
        String adresse = Tools.b642str(tab[10]);
        String telephone = Tools.b642str(tab[11]);
        return new MEcole(code, mepua, ire, dce, pays, devise, ecole, slogan, etablissement, cycle, adresse, telephone);
    }



}
