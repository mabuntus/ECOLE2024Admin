package mabuntu.ecole.ecole2024.model_table;

public class MMoy {

    public String codeElv;
    public String rang;
    public String total;
    public String moy;
    public int trimestre;

    public MMoy(){
        this("", "", "", 1);
    }

    public MMoy(String codeElv,
                String total,
                String moy,
                int trimestre){
        this.codeElv = codeElv;
        this.total = total;
        this.moy = moy;
        this.rang = "";
        this.trimestre = trimestre;
    }

}
