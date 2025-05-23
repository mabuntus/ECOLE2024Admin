package mabuntu.ecole.ecole2024.print;

import android.content.Context;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.itextpdf.html2pdf.HtmlConverter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import mabuntu.ecole.ecole2024.cntrol.TCntStr;
import mabuntu.ecole.ecole2024.model_table.MEcole;
import mabuntu.ecole.ecole2024.model_table.MEleve;
import mabuntu.ecole.ecole2024.model_table.MMatiere;
import mabuntu.ecole.ecole2024.model_table.MMoy;
import mabuntu.ecole.ecole2024.tools.FILE;
import mabuntu.ecole.ecole2024.tools.Fil;
import mabuntu.ecole.ecole2024.tools.TNum;
import mabuntu.ecole.ecole2024.tools.TStr;
import mabuntu.tombolia.pressing.R;

public class PrtBulletinTrimestre {

    public static String head(Context context, MEcole mecole, AutoCompleteTextView trimestre){
        String str_head = Fil.rawFile2Str(context, R.raw.html_bulletin_new_head);
        String logobase64 = MEcole.logo_base64(context).replace(("\n"), ("")).replace(("\r"), (""));

        str_head = str_head.replace("ecole-logo.png", String.format("data:image/png;base64,%s", logobase64));

        str_head = str_head.replace("$mepua$", mecole.mepua);
        str_head = str_head.replace("$ire$", mecole.ire);
        str_head = str_head.replace("$dce$", mecole.dce);
        str_head = str_head.replace("$pays$", mecole.pays);
        str_head = str_head.replace("$devise$", mecole.devise);
        str_head = str_head.replace("$session$", "2023-2024");
        str_head = str_head.replace("$ecole_fullname$", mecole.ecole);
        str_head = str_head.replace("$slogan$", mecole.slogan);
        str_head = str_head.replace("$etablissement$", mecole.etablissement);
        str_head = str_head.replace("$cycle$", mecole.cycle);
        str_head = str_head.replace("$adresse$", mecole.addresse);
        str_head = str_head.replace("$telephone$", mecole.telephone);
        str_head = str_head.replace("$trimestre$", trimestre.getText().toString());

        return str_head;
    }

    public static String eleve(Context context, MEleve elv, MMoy mmoy, int effectif, AutoCompleteTextView trimestre){
        String str_eleve = Fil.rawFile2Str(context, R.raw.html_bulletin_new_eleve);
        String photobase64 = Fil.file2base64(context, FILE.eleve_photo(elv.code)).replace(("\n"), ("")).replace(("\r"), (""));

        str_eleve = str_eleve.replace("photo.png", String.format("data:image/png;base64,%s", photobase64));

        str_eleve = str_eleve.replace("$matricule$", elv.matricule);
        str_eleve = str_eleve.replace("$prenom$", elv.prenom);
        str_eleve = str_eleve.replace("$nom$", elv.nom);
        str_eleve = str_eleve.replace("$rang$", mmoy.rang);
        str_eleve = str_eleve.replace("$moyenne$", mmoy.moy);
        str_eleve = str_eleve.replace("$mention$", mention(trimestre, mmoy.moy));
        str_eleve = str_eleve.replace("$classe$", elv.classe);
        str_eleve = str_eleve.replace("$effectif$", TNum.iS(effectif));
        return str_eleve;
    }

    public static String matiere_head(Context context, MMatiere[] matieres, String moy_low, String moy_high){
        String str_matiere_head = Fil.rawFile2Str(context, R.raw.html_bulletin_new_matiere_head);
        String nb_matiere = TNum.iS(matieres.length + 1);

        str_matiere_head = str_matiere_head.replace("$nb_matiere$", nb_matiere);
        str_matiere_head = str_matiere_head.replace("$moyenne_low$", moy_low);
        str_matiere_head = str_matiere_head.replace("$moyenne_high$", moy_high);
        return str_matiere_head;
    }
    public static String matiere_line(Context context){
        String str_matiere_line = Fil.rawFile2Str(context, R.raw.html_bulletin_new_matiere_line);
        return str_matiere_line;
    }

    public static String footer(Context context){
        String str_matiere_line = Fil.rawFile2Str(context, R.raw.html_bulletin_new_footer);
        return str_matiere_line;
    }

    private static String mention(AutoCompleteTextView trimestre, String moy){
        if (TCntStr.istrimestre(trimestre)){
            return mention_10(moy);
        }
        return mention_20(moy);
    }

    private static String mention_10(String moy){
        float note = TNum.f(moy);
        if (note >= 9.0) return "excellent";
        if (note >= 8.0) return "Tres Bien";
        if (note >= 7.0) return "Bien";
        if (note >= 6.0) return "Assez Bien";
        if (note >= 5.0) return "Passable";
        if (note >= 3.0) return "Mediocre";
        if (note >= 2.0) return "Faible";
        return "Tres faible";
    }
    private static String mention_20(String moy){
        float note = TNum.f(moy) / 2.0f;
        return mention_10(Float.toString(note));
    }

    public static void save_bulletin(Context context, String html, String classe, String fname_pdf){
        try {
            fname_pdf = TStr.clean_str(fname_pdf);
            File root = android.os.Environment.getExternalStorageDirectory();
            File dir = new File(String.format("%s%s", root.getAbsolutePath(), "/bulletins"));
            dir.mkdir();

            dir = new File(String.format("%s%s/%s", root.getAbsolutePath(), "/bulletins", classe));
            dir.mkdir();

            dir = new File(String.format("%s%s%s/%s.pdf", root.getAbsolutePath(), "/bulletins/", classe, fname_pdf));
            OutputStream os = new FileOutputStream(dir);

            HtmlConverter.convertToPdf(html, os);
            Toast.makeText(context, "SUCCES IMPRESSION", Toast.LENGTH_LONG).show();
        } catch (Exception e){
            Toast.makeText(context, "echec bulletin", Toast.LENGTH_LONG).show();
            Log.println(Log.DEBUG, "html_to_pdf", e.getMessage());
        }
    }

}
