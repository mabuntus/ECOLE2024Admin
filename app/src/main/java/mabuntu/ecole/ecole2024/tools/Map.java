package mabuntu.ecole.ecole2024.tools;


public class Map {

    private String key;
    private String value;

    public Map(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey(){ return key; }
    public String key(){ return key; }

    public void key(String key) { this.key = key; }
    public void setKey(String key) { this.key = key; }

    public String getValue(){ return value;}
    public String val(){ return value;}

    public void setValue(String value) { this.value = value; }
    public void setVal(String value) { this.value = value; }

    public String key(Map[] maps, String key){
        for (Map o : maps)
            if (o.key().contentEquals(key)) return o.val();
        return null;
    }

    public String val(Map[] maps, String val){
        for (Map o : maps)
            if (o.val().contentEquals(val)) return o.key();
        return null;
    }

}

