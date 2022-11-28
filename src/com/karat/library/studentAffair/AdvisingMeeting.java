package com.karat.library.studentAffair;

import java.util.*;

public class AdvisingMeeting {
    public static void main(String[] args) {
        String[][] pairs1 = {
                {"Foundations of Computer Science", "Operating Systems"},
                {"Data Structures", "Algorithms"},
                {"Computer Networks", "Computer Architecture"},
                {"Algorithms", "Foundations of Computer Science"},
                {"Computer Architecture", "Data Structures"},
                {"Software Design", "Computer Networks"}
        };

        String[][] pairs2 = {
                {"Algorithms", "Foundations of Computer Science"},
                {"Data Structures", "Algorithms"},
                {"Foundations of Computer Science", "Logic"},
                {"Logic", "Compilers"},
                {"Compilers", "Distributed Systems"},
        };


        String[][] pairs3 = {
                {"Data Structures", "Algorithms"}
        };
        System.out.println(findMiddleCourse(pairs1));
    }

    private static String findMiddleCourse(String[][] pairs) {
        Map<String, String> courseMap = new HashMap<>();
        LinkedList<String> list = new LinkedList<>();
        String first = "";
        for(String[] pair: pairs){
            courseMap.put(pair[0], pair[1]);
        }
        for(Map.Entry<String, String> map : courseMap.entrySet()){
            if(!courseMap.containsValue(map.getKey())){
                first = map.getKey();
                break;
            }

        }

        while(!list.contains(first)){
            list.add(first);
            first = courseMap.getOrDefault(first, first);
        }
        System.out.println(list);
        System.out.println(courseMap);
        return "";
    }
}
