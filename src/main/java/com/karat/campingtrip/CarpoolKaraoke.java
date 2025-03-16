package com.karat.campingtrip;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

/**
You and your friends are driving to a Campground to go camping. Only 2 of you have cars, so you will be carpooling.

Routes to the campground are linear, so each location will only lead to 1 location and there will be no loops or detours. Both cars will leave from their starting locations at the same time. The first car to pass someone's location will pick them up. If both cars arrive at the same time, the person can go in either car.

Roads are provided as a directed list of connected locations with the duration (in minutes) it takes to drive between the locations.
[Origin, Destination, Duration it takes to drive]

Given a list of roads, a list of starting locations and a list of people/where they live, return a collection of who will be in each car upon arrival to the Campground.
------------------------------------------------------
Bridgewater--(30)-->Caledonia--(15)-->New Grafton--(5)-->Campground
                                       ^
Liverpool---(10)---Milton-----(30)-----^

roads1 = [
    ["Bridgewater", "Caledonia", "30"], <= The road from Bridgewater to Caledonia takes 30 minutes to drive.
    ["Caledonia", "New Grafton", "15"],
    ["New Grafton", "Campground", "5"],
    ["Liverpool", "Milton", "10"],
    ["Milton", "New Grafton", "30"]
]
starts1 = ["Bridgewater", "Liverpool"]
people1 = [
    ["Jessie", "Bridgewater"], ["Travis", "Caledonia"],
    ["Jeremy", "New Grafton"], ["Katie", "Liverpool"]
]

Car1 path: (from Bridgewater): [Bridgewater(0, Jessie)->Caledonia(30, Travis)->New Grafton(45)->Campground(50)]
Car2 path: (from Liverpool): [Liverpool(0, Katie)->Milton(10)->New Grafton(40, Jeremy)->Campground(45)]

Output (In any order/format):
    [Jessie, Travis], [Katie, Jeremy]
--------------------------------------
Riverport->Chester->Campground
             ^
Halifax------^

roads2 = [["Riverport", "Chester", "40"], ["Chester", "Campground", "60"], ["Halifax", "Chester", "40"]]
starts2 = ["Riverport", "Halifax"]
people2 = [["Colin", "Riverport"], ["Sam", "Chester"], ["Alyssa", "Halifax"]]

Output (In any order/format):
    [Colin, Sam], [Alyssa] OR [Colin], [Alyssa, Sam]
----------------------------------------
Riverport->Bridgewater->Liverpool->Campground

roads3 = [["Riverport", "Bridgewater", "1"], ["Bridgewater", "Liverpool", "1"], ["Liverpool", "Campground", "1"]]
starts3_1 = ["Riverport", "Bridgewater"]
starts3_2 = ["Bridgewater", "Riverport"]
starts3_3 = ["Riverport", "Liverpool"]
people3 = [["Colin", "Riverport"], ["Jessie", "Bridgewater"], ["Sam", "Liverpool"]]

Output (starts3_1/starts3_2):  [Colin], [Jessie, Sam] - (Cars can be in any order)
Output (starts3_3): [Jessie, Colin], [Sam]
----------------------------------------
All Test Cases: (Cars can be in either order)
carpool(roads1, starts1, people1) => [Jessie, Travis], [Katie, Jeremy]
carpool(roads2, starts2, people2) => [Colin, Sam], [Alyssa] OR [Colin], [Alyssa, Sam]
carpool(roads3, starts3_1, people3) => [Colin], [Jessie, Sam]
carpool(roads3, starts3_2, people3) => [Jessie, Sam], [Colin]
carpool(roads3, starts3_3, people3) => [Jessie, Colin], [Sam]
----------------------------------------
Complexity Variable:
n = number of roads
 **/
public class CarpoolKaraoke {

    public static void main(String[] args) {
        String[][] roads1 = {
                {"Bridgewater", "Caledonia", "30"}, {"Caledonia", "New Grafton", "15"},
                {"New Grafton", "Campground", "5"}, {"Liverpool", "Milton", "10"},
                {"Milton", "New Grafton", "30"}
        };
        String[] starts1 = {"Bridgewater", "Liverpool"};
        String[][] people1 = {
                {"Jessie", "Bridgewater"}, {"Travis", "Caledonia"},
                {"Jeremy", "New Grafton"}, {"Katie", "Liverpool"}
        };

        String[][] roads2 = {{"Riverport", "Chester", "40"}, {"Chester", "Campground", "60"}, {"Halifax", "Chester", "40"}};
        String[] starts2 = {"Riverport", "Halifax"};
        String[][] people2 = {{"Colin", "Riverport"}, {"Sam", "Chester"}, {"Alyssa", "Halifax"}};

        String[][] roads3 = {{"Riverport", "Bridgewater", "1"}, {"Bridgewater", "Liverpool", "1"}, {"Liverpool", "Campground", "1"}};
        String[] starts3_1 = {"Riverport", "Bridgewater"};
        String[] starts3_2 = {"Bridgewater", "Riverport"};
        String[] starts3_3 = {"Riverport", "Liverpool"};
        String[][] people3 = {{"Colin", "Riverport"}, {"Jessie", "Bridgewater"}, {"Sam", "Liverpool"}};

        System.out.println(carpool(roads1, starts1, people1));
        System.out.println(carpool(roads2, starts2, people2));
        System.out.println(carpool(roads3, starts3_1, people3));
        System.out.println(carpool(roads3, starts3_2, people3));
        System.out.println(carpool(roads3, starts3_3, people3));
    }

    private static Set<Set<String>> carpool(String[][] roads, String[] starts, String[][] people) {

        Map<String, String>  townToPeopleMap = Arrays
                                                .stream(people)
                                                .collect(toMap( row -> row[1], row -> row[0]));
        Map<String, Destination> startToEndMap =  Arrays
                                                  .stream(roads)
                                                  .collect(toMap( row -> row[0], row -> new Destination(row[1], Integer.parseInt(row[2]))
                                                  ));
        Set<Set<String>> resultantPeople =  new HashSet<>();
        Set<String> startPointSet  = Arrays
                                    .stream(starts)
                                    .collect(Collectors.toSet());
        Map<String, Map<String, Integer>> personPickUpMap = new HashMap<>();
        Map<String, Set<String>> startPointAndAccompanyMap = new HashMap<>();
        for(String startPoint : starts){
            Set<String> result = new HashSet<>();
            Map<String, Integer> pickUpPersonMap = new HashMap<>();
            personPickUpMap.put(startPoint, pickUpPersonMap);
            String copyOfStartPoint =  new String(startPoint);
            //System.out.println("start point:"+startPoint);
            int distance = 0;
            result.add(townToPeopleMap.get(copyOfStartPoint));
            while(copyOfStartPoint != null && !copyOfStartPoint.equals("Campground")){
                //System.out.println("copy of start point:"+copyOfStartPoint);
                distance += startToEndMap.get(copyOfStartPoint).distance;
                pickUpPersonMap.put(startToEndMap.get(copyOfStartPoint).destination, distance);
                if(townToPeopleMap.containsKey(copyOfStartPoint) && !startPointSet.contains(copyOfStartPoint)){
                    result.add(townToPeopleMap.get(copyOfStartPoint));
                }
                copyOfStartPoint = startToEndMap.get(copyOfStartPoint).destination;

            }
            startPointAndAccompanyMap.put(startPoint, result);
            resultantPeople.add(result);
          //  System.out.println("personPickUpMap:"+personPickUpMap);

        }
        optimizedPeopleBasedOnDistance( personPickUpMap, starts, startPointAndAccompanyMap, townToPeopleMap);

        return resultantPeople;
        }

    private static void optimizedPeopleBasedOnDistance(Map<String, Map<String, Integer>> personPickUpMap, String[] starts, Map<String, Set<String>> startPointAndAccompanyMap, Map<String, String> townToPeopleMap) {
       var firstStartPointMap =  personPickUpMap.get(starts[0]);
       var secondValueMap = personPickUpMap.get(starts[1]);
       var firstValueAccompanyPeople = startPointAndAccompanyMap.get(starts[0]);
       var secondValeAccompanyPeople =  startPointAndAccompanyMap.get(starts[1]);

       for(Map.Entry<String, Integer> firstValue : firstStartPointMap.entrySet()){
           String personName = townToPeopleMap.get(firstValue.getKey());
           //System.out.println("firstValue:"+firstValue);
          // System.out.println("secondValueMap:"+secondValueMap);
           if(secondValeAccompanyPeople.contains(personName) && secondValueMap.getOrDefault(firstValue.getKey(), Integer.MAX_VALUE) <= firstValue.getValue()){
               firstValueAccompanyPeople.remove(personName);
           }
       }
        for(Map.Entry<String, Integer> secondValue : secondValueMap.entrySet()){
            String personName = townToPeopleMap.get(secondValue.getKey());
            //System.out.println("firstValue:"+firstValue);
            // System.out.println("secondValueMap:"+secondValueMap);
            if(firstValueAccompanyPeople.contains(personName) && firstStartPointMap.getOrDefault(secondValue.getKey(), Integer.MAX_VALUE) <= secondValue.getValue()){
                secondValeAccompanyPeople.remove(personName);
            }
        }


    }
}

class Destination{
    String destination;
    int distance;

    public Destination(String destination, int distance) {
        this.destination = destination;
        this.distance = distance;
    }
}
