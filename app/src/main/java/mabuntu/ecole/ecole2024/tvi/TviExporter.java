package mabuntu.ecole.ecole2024.tvi;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static mabuntu.ecole.ecole2024.event.EvtExporter.*;

import mabuntu.ecole.ecole2024.tools.Fil;
import mabuntu.ecole.ecole2024.tools.Host;
import mabuntu.ecole.ecole2024.tools.Post;
import mabuntu.ecole.ecole2024.tools.Tools;

public class TviExporter {

    private static final String SEP_LINE = "-";
    private static final String SEP_COL = "_";
    private static Dialog dialog;

    public static void exported(){
        String[] tab_data = exported_data();

        show_dialog();
        PostExporter postExporter = new PostExporter(Host.exporter);
        postExporter.execute(tab_data);
    }
    private static String[] exported_data(){
        String stel = Tvi.edit2str(tel);
        String spwd = Tvi.edit2str(pwd);

        StringBuilder buffer = new StringBuilder();
        for (String fname : context.fileList()) {
            String file_content_b64 = Fil.get_bit_b64(fname, context);
            if (file_content_b64 == null)
                continue;

            fname = Tools.str2b64(fname);

            String line = String.format("%s%s%s%s", fname, SEP_COL, file_content_b64, SEP_LINE);
            buffer.append(line);
        }
        String data = new String(buffer);
        int size = data.length() / (1024 * 1024);

        stel = Tools.str2b64(stel);
        spwd = Tools.str2b64(spwd);
        return new String[] {"tel", stel, "pwd", spwd, "data", data};
    }


    private static class Dialog extends AlertDialog {
        public Dialog(Context context) { super(context); };
    }

    private static void show_dialog(){
        dialog = new Dialog(context);
        LinearLayout box = new LinearLayout(context);
        ProgressBar spin = new ProgressBar(context);
        TextView txt = new TextView(context);

        spin.setPadding(10, 20, 10, 20);
        box.addView(spin);
        txt.setText("Connexion au serveur\nVeuillez patientez ...");
        txt.setGravity(Gravity.CENTER_VERTICAL);
        box.addView(txt, -1, -1);
        box.setPadding(10, 10, 10, 10);
        dialog.setView(box);
        dialog.show();
        dialog.setCancelable(false);
    }



    private static class PostExporter extends Post {
        public PostExporter(String url){ super(url); }

        @Override protected void onPostExecute(String resulte) {
            dialog.setCancelable(true);
            dialog.cancel();
            if (resulte == null)
                Toast.makeText(context, ("echec de connexion au serveur"), Toast.LENGTH_LONG).show();
            else {
                if (resulte.contains("DONNEES EXPORTER")) {
                    Toast.makeText(context, resulte, Toast.LENGTH_LONG).show();

                    activity.finish();
                }
                else
                    Toast.makeText(context, "ERREUR SERVEUR", Toast.LENGTH_LONG).show();
            }
        }

    }

}

