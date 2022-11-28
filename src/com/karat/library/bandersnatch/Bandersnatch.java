package com.karat.library.bandersnatch;

import java.util.*;
import java.util.stream.Collectors;

public class Bandersnatch {

    public static void main(String[] args) {
        Bandersnatch b = new Bandersnatch();
        int[] endings1 = {5, 10};
        int[][] choices1_1 = {{3, 7, 9}, {9, 10, 8}};
        int[][] choices1_2 = {{3, 1, 2}};
        int[][] choices1_3 = {{3, 4, 1}};
        int[][] choices1_4 = {};

        int[] endings2 = {10, 15, 21, 25, 30};
        int[][] choices2_1 = {{3, 2, 19}, {7, 9, 15}, {14, 23, 28}, {20, 13, 4}, {24, 10, 30}, {29, 21, 25}};
        int[][] choices2_2 = {{9, 16, 26}, {14, 16, 13}, {27, 14, 24}};

        int[] endings3 = {14, 15, 23, 32, 37};
        int[][] choices3_1 = {{7, 8, 30}, {8, 10, 28}, {9, 18, 15}, {10, 27, 11}, {30, 25, 13}};

        int[] endings4 = {35};
        int[][] choices4_1 = {{20, 25, 30}, {31, 19, 32}};
        System.out.println(mostRead(endings1, choices1_1));
    }

    private static String mostRead(int[] endings, int[][] choices) {

        Set<Integer> endingsSet = Arrays.stream(endings)
                                 .boxed()
                                 .collect(Collectors.toSet());
        Map<Integer, Map<Integer, Boolean>> choicesMap = new HashMap<>();
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int[] choice: choices){
            int data = choice[0];
            int first = choice[1];
            int second = choice[2];
            Map<Integer, Boolean> map = new HashMap<>();
            map.put(first, false);
            map.put( second, false);
            choicesMap.put(data, map);
        }

        dfs(endingsSet, choicesMap, freqMap, 1, new ArrayList<>());
        System.out.println(freqMap);
        return "";

    }

    private static void dfs(Set<Integer> endingsSet, Map<Integer, Map<Integer, Boolean>> choicesMap, Map<Integer, Integer> pageCountMap, int currentPage, List<Integer> sequence) {
            sequence.add(currentPage);
            if(choicesMap.containsKey(currentPage)){
                List<Integer> list = new ArrayList<>(choicesMap.get(currentPage).keySet());
                Map<Integer, Boolean> nextPages =  choicesMap.get(currentPage);
                if(isThisPageProcessed(nextPages, list.get(0)) && isThisPageProcessed(nextPages, list.get(1))){
                    System.out.println("both pages are processed:"+currentPage+" sequence:"+ sequence);
                    sequence.remove(sequence.size() - 1);
                    return;
                }
                if(!isThisPageProcessed(nextPages, list.get(0))){
                    nextPages.put(list.get(0), true);
                    dfs(endingsSet,choicesMap, pageCountMap, list.get(0),sequence );
                    nextPages.put(list.get(0), false);
                }if(!isThisPageProcessed(nextPages, list.get(1))){
                    nextPages.put(list.get(1), true);
                    dfs(endingsSet,choicesMap, pageCountMap, list.get(1),sequence );
                    nextPages.put(list.get(1), false);
                }

                
            } else  if (endingsSet.contains(currentPage)) {
                System.out.println(sequence);
                for(int page: sequence)
                    pageCountMap.put(page, pageCountMap.getOrDefault(page, 0) + 1);
                //sequence = new ArrayList<>();
                
            } else{
                //sequence.add(currentPage);
                dfs(endingsSet, choicesMap, pageCountMap, currentPage+1, sequence);
            }
        System.out.println("before removing sequence:"+sequence);
        sequence.remove(sequence.size() - 1);
    }

    private static boolean isThisPageProcessed(Map<Integer, Boolean> nextPages, Integer page) {
        return nextPages.get(page);
    }

    private static boolean processBothPages(Map<Integer, Boolean> nextPages, List<Integer> list) {
        return nextPages.get(list.get(0)) && nextPages.get(list.get(1));
    }
}
