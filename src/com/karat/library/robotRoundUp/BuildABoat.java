package com.karat.library.robotRoundUp;

import java.util.*;

public class BuildABoat {
    public static void main(String[] args) {
        String requiredParts1 = "sensors,case,speaker,wheels";
        String requiredParts2 = "sensors,case,speaker,wheels,claw";

        String[] allParts = {
                "Rocket_claw",
                "Rocket_sensors",
                "Dustie_case",
                "Rust_sensors",
                "Bolt_sensors",
                "Rocket_case",
                "Rust_case",
                "Bolt_speaker",
                "Rocket_wheels",
                "Rocket_speaker",
                "Dustie_case",
                "Dustie_arms",
                "Rust_claw",
                "Dustie_case",
                "Dustie_speaker",
                "Bolt_case",
                "Bolt_wheels",
                "Rust_legs",
                "Bolt_sensors"
        };
        System.out.println(getRobot(allParts, requiredParts1));
    }

    private static List<String> getRobot(String[] allParts, String requiredParts) {
        List<String> result = new ArrayList<>();
        Map<String, Set<String>> robotPartsMap = new HashMap<>();
        Set<String> parts = new HashSet<>();
        for(String part: allParts){
            String[] robotAndParts =  part.split("_");
            robotPartsMap.compute(robotAndParts[0], (k,v) ->{
             v = v == null?new HashSet<>(): v;
             v.add(robotAndParts[1]);
             return v;
            }
            );
        }
        for(String requiredPart:  requiredParts.split(","))
            parts.add(requiredPart);
        
        System.out.println(robotPartsMap);
        return result;
    }
}
