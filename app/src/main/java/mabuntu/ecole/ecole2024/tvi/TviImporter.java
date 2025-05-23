package mabuntu.ecole.ecole2024.tvi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static mabuntu.ecole.ecole2024.event.EvtImporter.*;

import mabuntu.ecole.ecole2024.MenuList;
import mabuntu.ecole.ecole2024.event.EvtImporter;
import mabuntu.ecole.ecole2024.tools.Fil;
import mabuntu.ecole.ecole2024.tools.Host;
import mabuntu.ecole.ecole2024.tools.Post;
import mabuntu.ecole.ecole2024.tools.Tools;

public class TviImporter {

    private static Dialog dialog;
    private static final String SEP_LINE = "-";
    private static final String SEP_COL = "_";

    public static void imported(){
        show_dialog();
        PostImporter postImporter = new PostImporter(Host.importer);
        postImporter.execute(imported_data());
    }
    private static String[] imported_data(){
        String stel = Tvi.edit2str(tel);
        String spwd = Tvi.edit2str(pwd);
        stel = Tools.str2b64(stel);
        spwd = Tools.str2b64(spwd);

        return new String[] { "tel", stel, "pwd", spwd};
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



    private static class PostImporter extends Post {
        public PostImporter(String url){ super(url); }

        @Override protected void onPostExecute(String resulte) {
            dialog.setCancelable(true);
            dialog.cancel();
            if (resulte == null)
                Toast.makeText(context, ("echec de connexion au serveur"), Toast.LENGTH_LONG).show();
            else if (resulte.length() < 1024)
                Toast.makeText(context, resulte, Toast.LENGTH_LONG).show();
            else
                poste_process(resulte);
        }

        private void poste_process(String result){
            try {
                String[] lines = result.split(SEP_LINE);
                for (String line : lines) {
                    String[] tab = line.split(SEP_COL);

                    if (tab.length == 2) {
                        String fname = tab[0];
                        String file_content_b64 = tab[1];

                        fname = Tools.b642str(fname);
                        Fil.put_from_b64_str(context, fname, file_content_b64);
                    }
                }

                EvtImporter.activity.finish();
                Intent intent = new Intent(context, MenuList.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            } catch (Exception e){
                Log.println(Log.DEBUG, "error", e.getMessage());
            }
        }
    }

}

