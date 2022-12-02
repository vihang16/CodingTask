package com.karat.securedRoom;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HighFreqEntry {
    public static void main(String[] args) {
        String[][] badgeTimes = new String[][] {
                {"Paul", "1355"},
                {"Jennifer", "1910"},
                {"Jose", "835"},
                {"Jose", "830"},
                {"Paul", "1315"},
                {"Chloe", "0"},
                {"Chloe", "1910"},
                {"Jose", "1615"},
                {"Jose", "1640"},
                {"Paul", "1405"},
                {"Jose", "855"},
                {"Jose", "930"},
                {"Jose", "915"},
                {"Jose", "730"},
                {"Jose", "940"},
                {"Jennifer", "1335"},
                {"Jennifer", "730"},
                {"Jose", "1630"},
                {"Jennifer", "5"},
                {"Chloe", "1909"},
                {"Zhang", "1"},
                {"Zhang", "10"},
                {"Zhang", "109"},
                {"Zhang", "110"},
                {"Amos", "1"},
                {"Amos", "2"},
                {"Amos", "400"},
                {"Amos", "500"},
                {"Amos", "503"},
                {"Amos", "504"},
                {"Amos", "601"},
                {"Amos", "602"},
                {"Paul", "1416"},
        };
        System.out.println(mostAccessed(badgeTimes));
    }

    private static Map<String, List<Integer>> mostAccessed(String[][] badgeTimes) {
        Map<String, Set<Integer>> employeeLogMap = new HashMap<>();
        Map<String, List<Integer>>  result =  new HashMap<>();
        int timer = 100;
        for(String[] employeeEntry : badgeTimes){
            String employee = employeeEntry[0];
            int time = Integer.parseInt(employeeEntry[1]);
            employeeLogMap.compute(employee, (k,v) ->{
                v = v == null? new TreeSet<>():v;
                v.add(time);
                return v;
            });
        }
        for(String employee : employeeLogMap.keySet()){
            List<Integer> accessTimes = new ArrayList<>(employeeLogMap.get(employee));
            int i = 0;
            int j = 1;
            while( i <= accessTimes.size() -3){
                if( j < accessTimes.size() && accessTimes.get(j) - accessTimes.get(i) <= timer){
                    j++;
                }else if(j< accessTimes.size() && accessTimes.get(j) - accessTimes.get(i) > timer &&  j - i +1 >3){
                    if(!result.containsKey(employee))
                        result.put(employee, new ArrayList<>(IntStream
                                                            .range(i, j)
                                                            .map(accessTimes::get)
                                                            .boxed()
                                                            .collect(Collectors.toList())));
                    i++;
                    j++;
                }else{
                    i++;
                    j++;
                }
            }
            if(accessTimes.get(accessTimes.size() - 1) - accessTimes.get(accessTimes.size() -3) <=timer && !result.containsKey(employee) )
                result.put(employee,new ArrayList<>(IntStream
                        .range(i-1, j-1)
                        .map(accessTimes::get)
                        .boxed()
                        .collect(Collectors.toList())));
        }

        System.out.println(employeeLogMap);
        return result;
    }
}
