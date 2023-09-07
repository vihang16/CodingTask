//https://central.karat.io/questions/1506/guide#1-1
package com.karat.goldrush;

import java.util.*;
import java.util.stream.Collectors;

public class MostVisitedRoom {
    public static void main(String[] args) {
        String[][] instructions_1 = {
                {"jasmin", "tulip"},
                {"lily", "tulip"},
                {"tulip", "tulip"},
                {"rose", "rose"},
                {"violet", "rose"},
                {"sunflower", "violet"},
                {"daisy", "violet"},
                {"iris", "violet"}
        };

        String[][] instructions_2 = {
                {"jasmin", "tulip"},
                {"lily", "tulip"},
                {"tulip", "violet"},
                {"violet", "violet"}
        };

        String[] treasure_rooms_1 = {"lily", "tulip", "violet", "rose"};
        String[] treasure_rooms_2 = {"lily", "jasmin", "violet"};
        String[] treasure_rooms_3 = {"violet"};
        System.out.println(filterRooms(instructions_1, treasure_rooms_1));
        System.out.println(filterRooms(instructions_1, treasure_rooms_2));
        System.out.println(filterRooms(instructions_2, treasure_rooms_3));
    }

    private static List<String> filterRooms(String[][] instructions, String[] treasureRooms) {
        Set<String> treasureSet = Arrays.stream(treasureRooms)
                                    .collect(Collectors.toSet());
        Map<String, Integer> numberOfRoomsPointer = new HashMap<>();
        Map<String, String> roomPairMap = Arrays.stream(instructions)
                                          .collect(Collectors.toMap( ins -> ins[0], ins -> ins[1]));
        List<String> result = new ArrayList<>();
        for(String[] instruction: instructions){
            if(!instruction[1].equals(instruction[0])) {
                numberOfRoomsPointer.put(instruction[1], numberOfRoomsPointer.getOrDefault(instruction[1], 0) + 1);
            }
        }
        for(Map.Entry<String, Integer> roomAndPointer: numberOfRoomsPointer.entrySet()){
            if(roomAndPointer.getValue() >=2 && roomPairMap.containsKey(roomAndPointer.getKey()) && treasureSet.contains(roomPairMap.get(roomAndPointer.getKey()))){
                result.add(roomAndPointer.getKey());
            }
        }
        return result;
    }
}
