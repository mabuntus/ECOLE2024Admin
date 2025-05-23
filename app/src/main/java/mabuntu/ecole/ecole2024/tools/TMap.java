package mabuntu.ecole.ecole2024.tools;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class TMap {

    public static HashMap<String, Double> sortByValue(HashMap<String, Double> hashMap) {

        // Create a list from elements of HashMap
        List<java.util.Map.Entry<String, Double> > list = new LinkedList<>(hashMap.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<java.util.Map.Entry<String, Double> >() {
            public int compare(java.util.Map.Entry<String, Double> o1,
                               java.util.Map.Entry<String, Double> o2) {

                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
        for (java.util.Map.Entry<String, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

}
