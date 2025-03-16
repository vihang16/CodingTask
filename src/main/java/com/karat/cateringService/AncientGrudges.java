//https://central.karat.io/questions/1453/guide#3-1
package com.karat.cateringService;

import java.util.*;

import static java.util.stream.Collectors.*;

public class AncientGrudges {

    public static void main(String[] args) {
        String[][] enemies_1 = {
                {"1", "2"}
        };
        String[][] enemies_2 = {
                {"1", "2"},
                {"4", "5"},
                {"1", "3"}
        };
        String[][] guests_1 = {
                {"Alice", "1"},
                {"Bob", "1"},
                {"Dima", "2"},
                {"Miriam", "2"}
        };
        String[][] guests_2 = {
                {"Alice", "1"},
                {"Bob", "1"},
                {"Esther", "5"},
                {"Dima", "4"},
                {"Joumana", "3"},
                {"Miriam", "2"},
                {"Abe", "3"},
                {"Klaus", "4"},
                {"Noor", "1"}
        };
        String[][] table_layout_1 = {
                {"T1", "T2"},
                {"T2", "T3"}
        };
        String[][] table_layout_2 = {
                {"T1", "T2"},
                {"T2", "T3"},
                {"T1", "T5"}
        };
        String[][] seating_1 = {
                {"Alice", "T1"},
                {"Bob", "T1"},
                {"Dima", "T3"},
                {"Miriam", "T2"}
        };
        String[][] seating_2 = {
                {"Alice", "T1"},
                {"Bob", "T1"},
                {"Dima", "T3"},
                {"Miriam", "T3"}
        };
        String[][] seating_3 = {
                {"Alice", "T1"},
                {"Bob", "T2"},
                {"Esther", "T1"},
                {"Dima", "T3"},
                {"Joumana", "T2"},
                {"Miriam", "T1"},
                {"Abe", "T3"},
                {"Klaus", "T2"},
                {"Noor", "T3"}
        };
        String[][] seating_4 = {
                {"Alice", "T1"},
                {"Bob", "T2"},
                {"Esther", "T1"},
                {"Dima", "T3"},
                {"Joumana", "T2"},
                {"Miriam", "T3"},
                {"Abe", "T3"},
                {"Klaus", "T3"},
                {"Noor", "T3"}
        };
        String[][] seating_5 = {
                {"Alice", "T1"},
                {"Bob", "T2"},
                {"Esther", "T4"},
                {"Dima", "T3"},
                {"Joumana", "T4"},
                {"Miriam", "T4"},
                {"Abe", "T4"},
                {"Klaus", "T3"},
                {"Noor", "T2"}
        };
        String[][] seating_6 = {
                {"Alice", "T10"},
                {"Dima", "T10"}
        };
        System.out.println(isValidArrangement(enemies_1, guests_1, table_layout_2, seating_1));
    }

    private static boolean isValidArrangement(String[][] enemies, String[][] guests, String[][] tableLayout, String[][] seatings) {
        Map<String, List<String>> enemiesMap =  new HashMap<>();
        Map<String, List<int[]>> tableLayoutMap = new HashMap<>();
        Map<String, Set<String>> enemyDoesNotSeatTogetherMap = new HashMap<>();
        Map<String, String> guestMap = Arrays
                .stream(guests)
                .collect(toMap(e -> e[0], e -> e[1]));
        Map<String, List<String>> guestToFamilyName = Arrays
                .stream(guests)
                .collect(groupingBy(e-> e[1],
                        mapping(e -> e[0], toList())));

        for(String[] enemy : enemies){
            enemiesMap.merge(enemy[0], new ArrayList<>(), (prev, newO) -> prev).add(enemy[1]);
            enemiesMap.merge(enemy[1], new ArrayList<>(), (prev, newO) -> prev).add(enemy[0]);
        }

        for(int i=0; i< tableLayout.length; i++){
            for(int j=0; j< tableLayout[0].length; j++){
                int[] location = new int[2];
                location[0] = i;
                location[1] = j;
                tableLayoutMap.merge(tableLayout[i][j], new ArrayList<>(), (prev, newO) -> prev).add(location);
            }
        }

        for(String[] seatiing : seatings){
            String name  = seatiing[0];
            String table = seatiing[1];
            List<int[]> tableLocations = tableLayoutMap.get(table);
            List<String> enemiesOfPerson = enemiesMap.get(name);
            if(checkIfEnemySittingNearBy(enemiesOfPerson, enemyDoesNotSeatTogetherMap, tableLayout, table))
                return false;
         }
        System.out.println(tableLayoutMap);
        return true;
    }

    private static boolean checkIfEnemySittingNearBy(List<String> enemiesOfPerson, Map<String, Set<String>> enemyDoesNotSeatTogetherMap, String[][] tableLayout, String table) {
        for(String enemy: enemiesOfPerson){
            if(enemyDoesNotSeatTogetherMap.containsKey(enemy)){

            }
        }
        return false;
    }
}
