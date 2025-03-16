//https://central.karat.io/questions/739/guide#2-1
package com.karat.bandersnatch;

import java.util.*;

import static java.util.stream.Collectors.toSet;

public class CQ2 {

    public static void main(String[] args) {
        int[] goodEndings = {10, 15, 25, 34};
        int[] badEndings = {21, 30, 40};

        int[][] choices1 = {{3, 16, 24}};
        int[][] choices2 = {{3, 16, 20}};
        int[][] choices3 = {{3, 2, 19}, {20, 21, 34}};
        int[][] choices4 = {};
        int[][] choices5 = {{9, 16, 26}, {14, 16, 13}, {27, 29, 28}, {28, 15, 34}, {29, 30, 38}};
        int[][] choices6 = {{9, 16, 26}, {13, 31, 14}, {14, 16, 13}, {27, 12, 24}, {32, 34, 15}};
        int[][] choices7 = {{3, 9, 10}};

        System.out.println(findGoodEndings(goodEndings, badEndings, choices1));
        System.out.println(findGoodEndings(goodEndings, badEndings, choices2));
        System.out.println(findGoodEndings(goodEndings, badEndings, choices3));
        System.out.println(findGoodEndings(goodEndings, badEndings, choices4));
        System.out.println(findGoodEndings(goodEndings, badEndings, choices5));
        System.out.println(findGoodEndings(goodEndings, badEndings, choices6));
        System.out.println(findGoodEndings(goodEndings, badEndings, choices7));
    }

    private static List<Integer> findGoodEndings(int[] goodEndings, int[] badEndings, int[][] choices) {
            List<Integer> goodEndingList = new ArrayList<>();
            Set<Integer> goodEndingSet   = convertArrayToSet(goodEndings);
            Set<Integer> badEndingSet    = convertArrayToSet(badEndings);
            Map<Integer, Map<Integer, Boolean>> choiceMap =  new HashMap<>();
            for(int[] choice : choices){
                Map<Integer, Boolean> map = new HashMap<>();
                map.put(choice[1], false);
                map.put(choice[2], false);

                choiceMap.put(choice[0], map);
            }

            dfs(1, goodEndingList, goodEndingSet, badEndingSet, choiceMap, new ArrayList<>());
            return goodEndingList;
    }


    private static void dfs(int page, List<Integer> goodEndingList, Set<Integer> goodEndingSet, Set<Integer> badEndingSet, Map<Integer, Map<Integer, Boolean>> choiceMap, List<Integer> readPages) {
        readPages.add(page);
        //System.out.println("page:"+page +" read pages:"+readPages);
        if(goodEndingSet.contains(page)){
            goodEndingList.add(page);
            readPages.remove(readPages.size() - 1);
            return;
        }if(badEndingSet.contains(page)) {
            readPages.remove(readPages.size() - 1);
            return;
        }
        if(choiceMap.containsKey(page)){
            boolean allChoiceDone = true;
            Map<Integer, Boolean> nextChoices = choiceMap.get(page);
            for(Map.Entry<Integer, Boolean> nextPage : nextChoices.entrySet()){
                if(!nextPage.getValue()){
                    allChoiceDone = false;
                    nextChoices.put(nextPage.getKey(), true);
                    dfs(nextPage.getKey(),goodEndingList, goodEndingSet, badEndingSet, choiceMap, readPages);
                }
                if(allChoiceDone)
                    return;
            }
        }else
            dfs(page+1, goodEndingList,goodEndingSet, badEndingSet,choiceMap, readPages);
        //System.out.println("page:"+page +" read pages:"+readPages);
        readPages.remove(readPages.size() - 1);
    }

    private static Set<Integer> convertArrayToSet(int[] endings) {
        return Arrays
                .stream(endings)
                .boxed()
                .collect(toSet());
    }
}
