package com.karat.securedRoom;

import java.util.*;

public class EntriesWithoutExit {

    public static void main(String[] args) {
        String[][] records1 = {
                {"Paul", "enter"},
                {"Pauline", "exit"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Martha", "exit"},
                {"Joe", "enter"},
                {"Martha", "enter"},
                {"Steve", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "enter"},
                {"Joe", "enter"},
                {"Curtis", "exit"},
                {"Curtis", "enter"},
                {"Joe", "exit"},
                {"Martha", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "exit"},
                {"Joe", "enter"},
                {"Joe", "enter"},
                {"Martha", "exit"},
                {"Joe", "exit"},
                {"Joe", "exit"},
        };

        String[][] records2 = {
                {"Paul", "enter"},
                {"Paul", "exit"},
        };

        String[][] records3 = {
                {"Paul", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"},
        };

        String[][] records4 = {
                {"Raj", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"},
                {"Paul", "enter"},
                {"Raj", "enter"},
        };
        System.out.println(mismatches(records1));
        System.out.println(mismatches(records2));
        System.out.println(mismatches(records3));
        System.out.println(mismatches(records4));
    }

    private static Set<Set<String>> mismatches(String[][] records) {
        Map<String, String> employeeLogMap = new HashMap<>();
        Set<Set<String>> result = new HashSet<>();
        Set<String> entrySet = new HashSet<>();
        Set<String> exitSet = new HashSet<>();
        for(String[] record: records){
            String employee = record[0];
            String type = record[1];
           // System.out.println(employeeLogMap.get( employee ) == null || employeeLogMap.get(employee).equals("exit"));

            if(type.equals("enter") && (employeeLogMap.get( employee ) == null || employeeLogMap.get(employee).equals("exit"))) {
                employeeLogMap.put(employee, type);

            }else if(type.equals("enter")){
                entrySet.add(employee);
            }
            //System.out.println(type ==  "exit" && employeeLogMap.get(employee) != null && employeeLogMap.get(employee).equals("enter"));
            if (type.equals( "exit" ) && employeeLogMap.get(employee) != null && employeeLogMap.get(employee).equals("enter")){
                employeeLogMap.put(employee, type);

            }else if(type.equals("exit")){
                exitSet.add(employee);
            }

        }
        for(String employee :  employeeLogMap.keySet()){
            if(employeeLogMap.get(employee).equals("enter"))
                entrySet.add(employee);
        }
        //System.out.println(entrySet);
        //System.out.println(exitSet);
        result.add(entrySet);
        result.add(exitSet);

        return  result;
    }
}
