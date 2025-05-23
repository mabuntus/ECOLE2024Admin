package mabuntu.ecole.ecole2024.tools;

import android.os.Environment;

public class FILE {

    public static String login       = "ecole2024-login";
    public static String eleve       = "pressing-habits";
    public static String matiere       = "pressing-pressing";
    public static String note       = "ecole2024-note";
    public static String note1       = "ecole2024-note1";
    public static String note2       = "ecole2024-note2";
    public static String note3       = "ecole2024-note3";
    public static String moy1       = "ecole2024-moy1";
    public static String moy2       = "ecole2024-moy2";
    public static String moy3       = "ecole2024-moy3";
    public static String ecole      = "ecole2024-ecole";
    public static String eleve_matricule = "ecole2024-eleve_matricule";
    public static String mensualite_frais = "ecole2024-mens-frais";
    public static String mensualite_paye = "ecole2024-mens-paye";

    private static String root = Environment.getExternalStorageDirectory().toString();
    public static String images = String.format("%s/ecoleImages", root);

    public static String eleve_photo(String codeElv){
        return String.format("eleve-image-%s", codeElv);
    }
    public static String ecole_photo(){
        return String.format("ecole-image-%s", "ecole-photo");
    }

    public static String ecole_name       = "ecole2024-ecole_name";
    public static String matiere_saved    = "ecole2024-matiere-saved";
    public static String eleve_saved      = "ecole2024-eleve-saved";

    public static String data_elv_code      = "ecole2024-data-eleve-code";


    public static String html       = "ecole2024-html";

    public static String print_head      = "ecole2024-print-head";
    public static String print_end       = "ecole2024-print-end";
    public static String print_body      = "ecole2024-print-body";

}
