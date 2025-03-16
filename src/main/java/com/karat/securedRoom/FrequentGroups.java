package com.karat.securedRoom;

import java.util.*;

public class FrequentGroups {

    public static void main(String[] args) {
        String[][] records = new String[][] {
                {"Curtis", "2", "enter"},
                {"John", "1510", "exit"},
                {"John", "455", "enter"},
                {"John", "512", "exit"},
                {"Jennifer", "715", "exit"},
                {"Steve", "815", "enter"},
                {"John", "930", "enter"},
                {"Steve", "1000", "exit"},
                {"Paul", "1", "enter"},
                {"Angela", "1115", "enter"},
                {"Curtis", "1510", "exit"},
                {"Angela", "2045", "exit"},
                {"Nick", "630", "exit"},
                {"Jennifer", "30", "enter"},
                {"Nick", "30", "enter"},
                {"Paul", "2145", "exit"},
                {"Ben", "457", "enter"},
                {"Ben", "458", "exit"},
                {"Robin", "459", "enter"},
                {"Robin", "500", "exit"},
        };

        String[][] records2 = new String[][] {
                {"Paul", "1545", "exit"},
                {"Curtis", "1410", "enter"},
                {"Curtis", "222", "enter"},
                {"Curtis", "1630", "exit"},
                {"Paul", "10", "enter"},
                {"Paul", "1410", "enter"},
                {"John", "330", "enter"},
                {"Jennifer", "330", "enter"},
                {"Jennifer", "1410", "exit"},
                {"John", "1410", "exit"},
                {"Curtis", "330", "exit"},
                {"Paul", "330", "exit"},
        };
        System.out.println(frequentGroups(records));
    }

    private static String frequentGroups(String[][] records) {
        List<Employee> employeeList = new ArrayList<>();
        int max =1;
        Map<Set<String>, List<List<Integer>>> resultMap = new HashMap<>();
        Set<String> employees = new HashSet<>();
        for(String[] employeeEntry : records){
            employeeList.add(new Employee(employeeEntry[0], Integer.parseInt(employeeEntry[1]), employeeEntry[2]));
        }
        Collections.sort(employeeList, Comparator.comparingInt(i -> i.time));

        employeeList.forEach(System.out::println);
        Set<String> employeeSet = new HashSet<>();
        for(int i=0; i< employeeList.size() - 1; i++){
            if( employeeList.get(i).type.equals("enter")){
                employeeSet.add(employeeList.get(i).name);
                if(resultMap.containsKey(employeeSet)){
                    System.out.println("inside enter contains key:"+employeeList.get(i));
                    List<Integer> accessTimeList =  new ArrayList<>();
                    accessTimeList.add(employeeList.get(i).time);
                    resultMap.get(employeeSet).add(accessTimeList);
                }else {
                    Set<String> newEmployeeSet = Set.copyOf(employeeSet);
                    //System.out.println("inside enter not contains key:"+employeeList.get(i));
                    List<List<Integer>> arrayList = new ArrayList<>();
                    List<Integer> accessTimeList =  new ArrayList<>();
                    accessTimeList.add(employeeList.get(i).time);
                    arrayList.add(accessTimeList);
                    resultMap.put(newEmployeeSet, arrayList);
                }
            } else if (employeeList.get(i).type.equals("exit")) {
                System.out.println("inside exit contains key:"+employeeList.get(i));
                System.out.println(employeeSet);
                System.out.println(resultMap.keySet());
                List<List<Integer>> accessTimeList = resultMap.get(employeeSet);
                List<Integer> lastAcessTimes = accessTimeList.get(accessTimeList.size() - 1);
                lastAcessTimes.add(employeeList.get(i).time);
                accessTimeList.add(lastAcessTimes);
                resultMap.put(employeeSet, accessTimeList);
                employeeSet.remove(employeeList.get(i).name);
            }

        }
        //System.out.println(resultMap);
        return "";
    }
}

class Employee{
    String name;
    int time;
    String type;

    public Employee(String name, int time, String type) {
        this.name =  name;
        this.time = time;
        this.type = type;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", time=" + time +
                ", type='" + type + '\'' +
                '}';
    }
}
