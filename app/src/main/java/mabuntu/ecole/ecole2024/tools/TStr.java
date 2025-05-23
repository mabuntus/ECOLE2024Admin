package mabuntu.ecole.ecole2024.tools;

public class TStr {

    public static String clean_str(String fname_pdf){
        fname_pdf = fname_pdf.replace(" ", "");
        fname_pdf = fname_pdf.replace("\"", "");
        fname_pdf = fname_pdf.replace("'", "");
        return fname_pdf;
    }

}
