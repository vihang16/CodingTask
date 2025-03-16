package com.karat.discjockey;

import kotlin.jvm.internal.MagicApiIntrinsics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
We have a catalog of song titles (and their lengths) that we play at a local radio station.  We have been asked to play two of those songs in a row, and they must add up to exactly seven minutes long.

Given a list of songs and their durations, write a function that returns the names of any two distinct songs that add up to exactly seven minutes.  If there is no such pair, return an empty collection.

Example:
song_times_1 = [
    ("Stairway to Heaven", "8:05"), ("Immigrant Song", "2:27"),
    ("Rock and Roll", "3:41"), ("Communication Breakdown", "2:29"),
    ("Good Times Bad Times", "2:48"), ("Hot Dog", "3:19"),
    ("The Crunge", "3:18"), ("Achilles Last Stand", "10:26"),
    ("Black Dog", "4:55")
]
find_pair(song_times_1) => ["Rock and Roll", "Hot Dog"] (3:41 + 3:19 = 7:00)

Additional Input:
song_times_2 = [
    ("Stairway to Heaven", "8:05"), ("Immigrant Song", "2:27"),
    ("Rock and Roll", "3:41"), ("Communication Breakdown", "2:29"),
    ("Good Times Bad Times", "2:48"), ("Black Dog", "4:55"),
    ("The Crunge", "3:18"), ("Achilles Last Stand", "10:26"),
    ("The Ocean", "4:31"), ("Hot Dog", "3:19"),
]
song_times_3 = [
    ("Stairway to Heaven", "8:05"), ("Immigrant Song", "2:27"),
    ("Rock and Roll", "3:41"), ("Communication Breakdown", "2:29"),
    ("Hey Hey What Can I Do", "4:00"), ("Poor Tom", "3:00"),
    ("Black Dog", "4:55")
]
song_times_4 = [
    ("Hey Hey What Can I Do", "4:00"), ("Rock and Roll", "3:41"),
    ("Communication Breakdown", "2:29"), ("Going to California", "3:30"),
    ("Black Mountain Side", "2:00"), ("Black Dog", "4:55")
]
song_times_5 = [("Celebration Day", "3:30"), ("Going to California", "3:30")]
song_times_6 = [
  ("Rock and Roll", "3:41"), ("If I lived here", "3:59"),
  ("Day and night", "5:03"), ("Tempo song", "1:57")
]


Complexity Variable:
n = number of song/time pairs

All Test Cases - snake_case:
find_pair(song_times_1) => ["Rock and Roll", "Hot Dog"]
find_pair(song_times_2) => ["Rock and Roll", "Hot Dog"] or ["Communication Breakdown", "The Ocean"]
find_pair(song_times_3) => ["Hey Hey What Can I Do", "Poor Tom"]
find_pair(song_times_4) => []
find_pair(song_times_5) => ["Celebration Day", "Going to California"]
find_pair(song_times_6) => ["Day and night", "Tempo song"]

All Test Cases - camelCase:
findPair(songTimes1) => ["Rock and Roll", "Hot Dog"]
findPair(songTimes2) => ["Rock and Roll", "Hot Dog"] or ["Communication Breakdown", "The Ocean"]
findPair(songTimes3) => ["Hey Hey What Can I Do", "Poor Tom"]
findPair(songTimes4) => []
findPair(songTimes5) => ["Celebration Day", "Going to California"]
findPair(songTimes6) => ["Day and night", "Tempo song"]


 */
public class ItTakesFewMinutes {

    public static void main(String[] args) {
        String[][] songTimes1 = {
                {"Stairway to Heaven", "8:05"}, {"Immigrant Song", "2:27"},
                {"Rock and Roll", "3:41"}, {"Communication Breakdown", "2:29"},
                {"Good Times Bad Times", "2:48"}, {"Hot Dog", "3:19"},
                {"The Crunge", "3:18"}, {"Achilles Last Stand", "10:26"},
                {"Black Dog", "4:55"}
        };
        String[][] songTimes2 = {
                {"Stairway to Heaven", "8:05"}, {"Immigrant Song", "2:27"},
                {"Rock and Roll", "3:41"}, {"Communication Breakdown", "2:29"},
                {"Good Times Bad Times", "2:48"}, {"Black Dog", "4:55"},
                {"The Crunge", "3:18"}, {"Achilles Last Stand", "10:26"},
                {"The Ocean", "4:31"}, {"Hot Dog", "3:19"}
        };
        String[][] songTimes3 = {
                {"Stairway to Heaven", "8:05"}, {"Immigrant Song", "2:27"},
                {"Rock and Roll", "3:41"}, {"Communication Breakdown", "2:29"},
                {"Hey Hey What Can I Do", "4:00"}, {"Poor Tom", "3:00"},
                {"Black Dog", "4:55"}
        };
        String[][] songTimes4 = {
                {"Hey Hey What Can I Do", "4:00"}, {"Rock and Roll", "3:41"},
                {"Communication Breakdown", "2:29"}, {"Going to California", "3:30"},
                {"Black Mountain Side", "2:00"}, {"Black Dog", "4:55"}
        };
        String[][] songTimes5 = {
                {"Celebration Day", "3:30"}, {"Going to California", "3:30"}
        };
        String[][] songTimes6 = {
                {"Rock and Roll", "3:41"}, {"If I lived here", "3:59"},
                {"Day and night", "5:03"}, {"Tempo song", "1:57"}
        };
        
        System.out.println(findPair(songTimes1));
        System.out.println(findPair(songTimes2));
        System.out.println(findPair(songTimes3));
        System.out.println(findPair(songTimes4));
        System.out.println(findPair(songTimes5));
        System.out.println(findPair(songTimes6));
    }

    private static List<String> findPair(String[][] songTimes) {
        List<String> result = new ArrayList<>();
        int desiredDuration = 420;
        Map<Integer, List<String>> durationSongMap = new HashMap<>();
        for(String[] song : songTimes){
            String songName = song[0];
            String[] duration = song[1].split(":");
            int totalDuration = Integer.parseInt(duration[0])*60 + Integer.parseInt(duration[1]);
            durationSongMap.compute(totalDuration, (k,v) ->{
                v = v == null? new ArrayList<>(): v;
                v.add(songName);
                return v;
            });
        }
        for(var map : durationSongMap.entrySet()){
            int duration =  map.getKey();
            int remainingDuration = desiredDuration - duration;
            if(durationSongMap.containsKey(remainingDuration)){
                var songs = durationSongMap.get(remainingDuration);
                if(duration == remainingDuration && songs.size() >1){
                    result.add(songs.get(0));
                    result.add(map.getValue().get(1));
                }else if(duration != remainingDuration){
                    result.add(songs.get(0));
                    result.add(map.getValue().get(0));
                }

                break;
            }
        }
        return result;
    }
}
