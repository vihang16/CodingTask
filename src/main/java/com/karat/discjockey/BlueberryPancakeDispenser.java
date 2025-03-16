package com.karat.discjockey;

import java.util.*;

/**
Our local radio station is running a show where the songs are ordered in a very specific way.  The last word of the title of one song must match the first word of the title of the next song - for example, "Silent Running" could be followed by "Running to Stand Still".  No song may be played more than once.

Given a list of songs and a starting song, find the longest chain of songs that begins with that song, and the last word of each song title matches the first word of the next one.  Write a function that returns the longest such chain. If multiple equivalent chains exist, return any of them.

Example:
songs1 = [
    "Down By the River",
    "River of Dreams",
    "Take me to the River",
    "Dreams",
    "Blues Hand Me Down",
    "Forever Young",
    "American Dreams",
    "All My Love",
    "Cantaloop",
    "Take it All",
    "Love is Forever",
    "Young American",
    "Dreamship",
    "Every Breath You Take",
]
song1_1 = "Every Breath You Take"
chaining(songs1, song1_1) => ['Every Breath You Take', 'Take it All', 'All My Love', 'Love is Forever', 'Forever Young', 'Young American', 'American Dreams', 'Dreams']

Additional Input:
song1_2 = "Dreams"
song1_3 = "Blues Hand Me Down"
song1_4 = "Cantaloop"

songs2 = [
    "Bye Bye Love",
    "Nothing at All",
    "Money for Nothing",
    "Love Me Do",
    "Do You Feel Like We Do",
    "Bye Bye Bye",zz
    "Do You Believe in Magic",
    "Bye Bye Baby",
    "Baby Ride Easy",
    "Easy Money",
    "All Right Now",
]
song2_1 = "Bye Bye Bye"
song2_2 = "Bye Bye Love"

songs3 = [
    "Love Me Do",
    "Do You Believe In Magic",
    "Magic You Do",
    "Magic Man",
    "Man In The Mirror"
]
song3_1 = "Love Me Do"

All Test Cases:
chaining(songs1, song1_1) => ['Every Breath You Take', 'Take it All', 'All My Love', 'Love is Forever', 'Forever Young', 'Young American', 'American Dreams', 'Dreams']
chaining(songs1, song1_2) => ['Dreams']
chaining(songs1, song1_3) => ['Blues Hand Me Down', 'Down By the River', 'River of Dreams', 'Dreams']
chaining(songs1, song1_4) => ['Cantaloop']
chaining(songs2, song2_1) => ['Bye Bye Bye', 'Bye Bye Baby', 'Baby Ride Easy', 'Easy Money', 'Money for Nothing', 'Nothing at All', 'All Right Now']
chaining(songs2, song2_2) => ['Bye Bye Love', 'Love Me Do', 'Do You Feel Like We Do', 'Do You Believe in Magic']
chaining(songs3, song3_1) => ['Love Me Do', 'Do You Believe in Magic', 'Magic Man', 'Man In The Mirror']

Complexity Variable:
n = number of songs in the input
 */
public class BlueberryPancakeDispenser {
    public static void main(String[] args) {
        String[] songs1 = {
                "Down By the River",
                "River of Dreams",
                "Take me to the River",
                "Dreams",
                "Blues Hand Me Down",
                "Forever Young",
                "American Dreams",
                "All My Love",
                "Cantaloop",
                "Take it All",
                "Love is Forever",
                "Young American",
                "Dreamship",
                "Every Breath You Take"
        };
        String song1_1 = "Every Breath You Take";
        String song1_2 = "Dreams";
        String song1_3 = "Blues Hand Me Down";
        String song1_4 = "Cantaloop";

        String[] songs2 = {
                "Bye Bye Love",
                "Nothing at All",
                "Money for Nothing",
                "Love Me Do",
                "Do You Feel Like We Do",
                "Bye Bye Bye",
                "Do You Believe in Magic",
                "Bye Bye Baby",
                "Baby Ride Easy",
                "Easy Money",
                "All Right Now"
        };
        String song2_1 = "Bye Bye Bye";
        String song2_2 = "Bye Bye Love";

        String[] songs3 = {
                "Love Me Do",
                "Do You Believe In Magic",
                "Magic You Do",
                "Magic Man",
                "Man In The Mirror"
        };
        String song3_1 = "Love Me Do";

        System.out.println(longestChain(songs1, song1_1));
        System.out.println(longestChain(songs1, song1_2));
        System.out.println(longestChain(songs1, song1_3));
        System.out.println(longestChain(songs1, song1_4));
        System.out.println(longestChain(songs2, song2_1));
        System.out.println(longestChain(songs2, song2_2));
    }

    private static List<String> longestChain(String[] songs, String startSong) {
        List<String> result = new ArrayList<>();
        Map<String, List<String>> startWordToSongMap = new HashMap<>();
        for(var song: songs){
            startWordToSongMap.computeIfAbsent(song.split(" ")[0], k ->new ArrayList<>()).add(song);
        }
        List<String> currentList = new ArrayList<>();
        Set<String> visitedSong = new HashSet<>();
        findLongestChain(startSong, result, startWordToSongMap, currentList, visitedSong);
       // System.out.println(startWordToSongMap);
        return result;
    }

    private static void findLongestChain(String currentSong, List<String> result, Map<String, List<String>> startWordToSongMap, List<String> currentList, Set<String> visitedSong) {
        String lastWord = currentSong.split(" ")[currentSong.split(" ").length - 1];
        //System.out.println("current song:"+currentSong+ " last word:"+lastWord);
        if(!startWordToSongMap.containsKey(lastWord) || visitedSong.contains(currentSong)){
            return;
        }
        currentList.add(currentSong);
        visitedSong.add(currentSong);

        for(var song: startWordToSongMap.get(lastWord)){
            findLongestChain(song,result, startWordToSongMap, currentList, visitedSong);
        }
        if (currentList.size() > result.size()) {
            result.clear();
            result.addAll(currentList);
        }
        currentList.remove(currentSong);
        visitedSong.remove(currentSong);
    }
}
