//https://central.karat.io/questions/1462/guide#1-1
package com.karat.summerVacation;

import java.util.*;

public class VacationRequest {
    public static void main(String[] args) {
        String[][] requests_1 = {
                {"Ahmed", "20", "17", "21", "12"},
                {"Emma", "17", "1", "22"}
        };
        String[] priority_1_1 = {"Ahmed", "Emma"};
        String[] priority_1_2 = {"Emma", "Ahmed"};
        String[][] requests_2 = {
                {"Sara", "1", "21", "11", "15", "18", "7"},
                {"Hana", "1", "11", "18", "25", "17"},
                {"Ren", "21", "7", "15", "2", "25"},
                {"Mateo", "4", "7", "25", "27"}
        };
        String[] priority_2_1 = {"Sara", "Hana", "Ren", "Mateo"};
        String[] priority_2_2 = {"Hana", "Ren", "Sara", "Mateo"};
        String[][] requests_3 = {
                {"Youngwoo", "1"},
                {"Miriam", "1"}
        };
        String[] priority_3_1 = {"Youngwoo", "Miriam"};
        String[] priority_3_2 = {"Miriam", "Youngwoo"};
        System.out.println(vacation(requests_1, priority_1_1));
        System.out.println(vacation(requests_1, priority_1_2));
        System.out.println(vacation(requests_2, priority_2_1));
        System.out.println(vacation(requests_2, priority_2_2));
        System.out.println(vacation(requests_3, priority_3_1));
        System.out.println(vacation(requests_3, priority_3_2));

    }

    private static Map<String, List<String>> vacation(String[][] requests, String[] priorities) {
        Set<String> usedRequest = new HashSet<>();
        Map<String, List<String>> userRequestMap = new HashMap<>();
        Map<String, List<String>> resultMap = new HashMap<>();
        int counter = 3;
        for(String[] request: requests){
            for(int i=1; i< request.length; i++){
                int finalI = i;
                userRequestMap.compute(request[0], (k, v) ->{
                    v = v == null? new ArrayList<>():v;
                    v.add(request[finalI]);
                    return v;
                });
            }
        }
        for(String priority : priorities ){
            resultMap.put(priority, new ArrayList<>());
            for(String day : userRequestMap.get(priority)){
                if(!usedRequest.contains(day) && counter > 0){
                    usedRequest.add(day);
                    resultMap.compute(priority, (k,v) -> v).add(day);
                    counter -=1;
                }

            }
            counter =3;
        }
        return resultMap;
    }
}
