//https://central.karat.io/questions/739/guide#1-1
package com.karat.bandersnatch;

import java.util.*;

import static java.util.stream.Collectors.toSet;

public class CQ1 {
    public static void main(String[] args) {
        int[] endings = {6, 15, 21, 30};
        int[][] choices1 = {{3, 14, 2}};
        int[][] choices2 = {{5, 11, 28}, {9, 19, 29}, {14, 16, 20}, {18, 7, 22}, {25, 6, 30}};
        int[][] choices3 = {};
        int[][] choices4 = {{2, 10, 15}, {3, 4, 10}, {4, 3, 15}, {10, 3, 15}};

        System.out.println(findEnding(endings, choices1, 1));
        System.out.println(findEnding(endings, choices1, 2));
        System.out.println(findEnding(endings, choices2, 1));
        System.out.println(findEnding(endings, choices2, 2));
        System.out.println(findEnding(endings, choices3, 1));
        System.out.println(findEnding(endings, choices3, 2));
        System.out.println(findEnding(endings, choices4, 1));
        System.out.println(findEnding(endings, choices4, 2));
    }

    private static int findEnding(int[] endings, int[][] choices, int option) {
        Set<Integer> endingSet = Arrays
                                .stream(endings)
                                .boxed()
                                .collect(toSet());
        int end = -1;
        Map<Integer, List<Integer>> choiceMap =  new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        for(int[] choice : choices){
            List<Integer> list = new ArrayList<>();
            list.add(choice[1]);
            list.add(choice[2]);
            choiceMap.put(choice[0], list);
        }
        int pageStart = 1;
        while(true){
            if(visited.contains(pageStart))
                return -1;
            visited.add(pageStart);
            //System.out.println(pageStart);
            if(endingSet.contains(pageStart))
                return pageStart;
            while(choiceMap.containsKey(pageStart)){

                pageStart = choiceMap.get(pageStart).get(option-1);
                if(endingSet.contains(pageStart))
                    return pageStart;
                if(visited.contains(pageStart))
                    return -1;
                visited.add(pageStart);

                //System.out.println("choice page:"+pageStart);
                //visited.add(pageStart);
            }
            pageStart++;


        }

    }
}
