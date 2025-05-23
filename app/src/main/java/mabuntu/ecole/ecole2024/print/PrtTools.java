package mabuntu.ecole.ecole2024.print;

public class PrtTools {

    public static String note(String note){
        if (note.contentEquals("0.0") || note.contentEquals("0.00"))
            return "vide";
        return note;
    }
}
