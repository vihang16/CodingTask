//https://central.karat.io/questions/1453/guide#1-1
package com.karat.cateringService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;



public class CustomerReports {
    public static void main(String[] args) {
        String[][] transactions_1 = {
                {"customer_4", "event_34", "7000"},
                {"customer_4", "event_37", "6000"},
                {"customer_6", "event_15", "3000"},
                {"customer_6", "event_36", "7000"},
                {"customer_6", "event_49", "4000"},
                {"customer_6", "event_67", "6000"},
                {"customer_6", "event_85", "6000"}
        };
        String[][] transactions_2 = {
                {"customer_6", "event_49", "4000"},
                {"customer_6", "event_85", "6000"},
                {"customer_4", "event_34", "7000"},
                {"customer_6", "event_67", "6000"},
                {"customer_6", "event_15", "3000"},
                {"customer_6", "event_36", "7000"},
                {"customer_4", "event_37", "6000"}
        };
        String[][] transactions_3 = {
                {"customer_3", "event_70", "4000"},
                {"customer_3", "event_71", "6900"},
                {"customer_1", "event_40", "1600"},
                {"customer_6", "event_74", "6900"},
                {"customer_8", "event_75", "7400"},
                {"customer_1", "event_43", "6400"},
                {"customer_3", "event_52", "6300"},
                {"customer_6", "event_25", "3500"},
                {"customer_1", "event_62", "2500"}
        };

        var mapResult  =customerReport(transactions_1);
        printMap(mapResult);
        mapResult  =customerReport(transactions_2);
        printMap(mapResult);
        mapResult  =customerReport(transactions_3);
        printMap(mapResult);

    }

    private static void printMap(Map<String,int[]> mapResutlt) {
        for(String key : mapResutlt.keySet()){
            System.out.println(key +" " + Arrays.toString(mapResutlt.get(key)));
        }
        System.out.println("----------");
    }

    private static Map<String, int[]> customerReport(String[][] transactions) {
        var map = new HashMap<String, int[]>();
        for(String[] transaction : transactions){
            map.compute(transaction[0], (k,v) ->{
               v = v == null? new int[2] : v;
               v[0] = v[0] + 1;
               v[1] = v[1] + Integer.parseInt(transaction[2]);
               return v;
            });
        }
        return map;
    }
}
