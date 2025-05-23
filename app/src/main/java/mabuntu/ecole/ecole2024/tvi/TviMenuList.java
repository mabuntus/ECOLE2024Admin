package mabuntu.ecole.ecole2024.tvi;

import static mabuntu.ecole.ecole2024.event.EvtMenuList.*;

import mabuntu.ecole.ecole2024.model_table.MEleve;
import mabuntu.ecole.ecole2024.tools.FILE;
import mabuntu.ecole.ecole2024.tools.Fil;
import mabuntu.ecole.ecole2024.tools.Tools;

public class TviMenuList {

    private static String COL = ",";
    private static String LINE = ";";
    private static String MAP = "@";

    public static String get_files_contents(){
        String ecole = Tools.str2b64("ecole");
        StringBuilder buffer = new StringBuilder();

        buffer.append(ecole).append(LINE);
        buffer.append(get_ecole()).append(LINE);
        buffer.append(Fil.get_bit_b64(FILE.matiere, context)).append(LINE);
        buffer.append(Fil.get_bit_b64(FILE.eleve, context)).append(LINE);

        buffer.append(Fil.get_bit_b64(FILE.note1, context)).append(LINE);
        buffer.append(Fil.get_bit_b64(FILE.note2, context)).append(LINE);
        buffer.append(Fil.get_bit_b64(FILE.note3, context)).append(LINE);

        buffer.append(Fil.get_bit_b64(FILE.moy1, context)).append(LINE);
        buffer.append(Fil.get_bit_b64(FILE.moy2, context)).append(LINE);
        buffer.append(Fil.get_bit_b64(FILE.moy3, context)).append(LINE);

        buffer.append(get_eleve_photos());
        return new String(buffer);
    }

    private static String get_ecole(){
        StringBuilder buffer = new StringBuilder();
        String ecole_info = Fil.get_bit_b64(FILE.ecole, context);
        String ecole_img = Fil.get_bit_b64(FILE.ecole_photo(), context);

        buffer.append(ecole_info).append(MAP).append(ecole_img);
        return new String(buffer);
    }
    private static String get_eleve_photos(){
        StringBuilder buffer = new StringBuilder();
        MEleve[] elvs = MEleve.getAll(context);

        for(MEleve elv : elvs){
            String fname = FILE.eleve_photo(elv.code);
            String fname64 = Tools.str2b64(fname);
            String content = Fil.get_bit_b64(FILE.eleve_photo(elv.code), context);

            buffer.append(fname64).append(MAP).append(content).append(COL);
        }
        return new String(buffer);
    }


}

