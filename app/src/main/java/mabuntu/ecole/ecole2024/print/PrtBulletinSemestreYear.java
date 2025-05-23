package mabuntu.ecole.ecole2024.print;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.itextpdf.html2pdf.HtmlConverter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import mabuntu.ecole.ecole2024.model_table.MEcole;
import mabuntu.ecole.ecole2024.model_table.MEleve;
import mabuntu.ecole.ecole2024.model_table.MMoy1;
import mabuntu.ecole.ecole2024.model_table.MMoy2;
import mabuntu.ecole.ecole2024.model_table.MYear;
import mabuntu.ecole.ecole2024.tools.FILE;
import mabuntu.ecole.ecole2024.tools.Fil;
import mabuntu.ecole.ecole2024.tools.TDate;
import mabuntu.ecole.ecole2024.tools.TNum;
import mabuntu.ecole.ecole2024.tools.TStr;
import mabuntu.ecole.ecole2024.tools.Tools;
import mabuntu.tombolia.pressing.R;

public class PrtBulletinSemestreYear {

    public static String head(Context context, MEcole mecole){
        String str_head = Fil.rawFile2Str(context, R.raw.html_yearly_bulletin_head);
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

        return str_head;
    }

    public static String eleve(Context context, MEleve elv, MYear myear, int effectif){
        String str_eleve = Fil.rawFile2Str(context, R.raw.html_yearly_bulletin_eleve);
        String photobase64 = Fil.file2base64(context, FILE.eleve_photo(elv.code)).replace(("\n"), ("")).replace(("\r"), (""));

        str_eleve = str_eleve.replace("photo.png", String.format("data:image/png;base64,%s", photobase64));

        str_eleve = str_eleve.replace("$matricule$", elv.matricule);
        str_eleve = str_eleve.replace("$prenom$", elv.prenom);
        str_eleve = str_eleve.replace("$nom$", elv.nom);
        str_eleve = str_eleve.replace("$rang$", myear.rang);
        str_eleve = str_eleve.replace("$moyenne$", myear.moy);
        str_eleve = str_eleve.replace("$mention$", mention_20(myear.moy));
        str_eleve = str_eleve.replace("$classe$", elv.classe);
        str_eleve = str_eleve.replace("$effectif$", TNum.iS(effectif));
        return str_eleve;
    }

    private static String mention(String smoy, String strimestre){
        if (strimestre.contains("semestre"))
            return mention_20(smoy);
        else
            return mention_10(smoy);
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

    public static String table_footer(Context context, MYear myear, MMoy1 moy1, MMoy2 moy2){
        String str_table_footer = Fil.rawFile2Str(context, R.raw.html_yearly_bulletin_table_footer_semestre);

        str_table_footer = str_table_footer.replace("$total_tri1$", myear.total_tri1);
        str_table_footer = str_table_footer.replace("$total_tri2$", myear.total_tri2);
        str_table_footer = str_table_footer.replace("$total_year$", myear.total);

        str_table_footer = str_table_footer.replace("$moy_tri1$", myear.moy_tri1);
        str_table_footer = str_table_footer.replace("$moy_tri2$", myear.moy_tri2);
        str_table_footer = str_table_footer.replace("$moy_year$", myear.moy);

        str_table_footer = str_table_footer.replace("$rang_tri1$", moy1 == null ? "0" : moy1.code_matiere);
        str_table_footer = str_table_footer.replace("$rang_tri2$", moy2 == null ? "0" : moy2.code_matiere);
        str_table_footer = str_table_footer.replace("$rang_year$", myear.rang);

        return str_table_footer;
    }

    public static String footer(Context context){
        String str_footer = Fil.rawFile2Str(context, R.raw.html_yearly_bulletin_footer);
        str_footer = str_footer.replace("Enseignant(e)", " ");
        str_footer = str_footer.replace("Directeur", "Directeur General");

        str_footer = str_footer.replace("$date$", String.format("Conakry, le %s", TDate.dateFull()));

        return str_footer;
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

