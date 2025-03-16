package com.karat.allInTheFamily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CQ1 {

    public static void main(String[] args) {
        int[][] pairs = new int[][] {
                {5, 6}, {1, 3}, {2, 3}, {3, 6}, {15, 12}, {5, 7},
                {4, 5}, {4, 9}, {9, 12}, {30, 16}
        };
        System.out.println(findNodesWithZeroAndOneParents(pairs));
    }

    private static List<List<Integer>> findNodesWithZeroAndOneParents(int[][] pairs) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> nodeWithNoParent = new ArrayList<>();
        List<Integer> nodeWithOneParent =  new ArrayList<>();
        for(int[] pair : pairs){
            int parent = pair[0];
            int child = pair[1];
            map.put(child, map.getOrDefault(child, 0) + 1);
            if( ! map.containsKey(parent))
                map.put(parent, 0);
        }
        for(Map.Entry<Integer, Integer> nodeMap : map.entrySet()){
            if(nodeMap.getValue() == 0)
                nodeWithNoParent.add(nodeMap.getKey());
            else if (nodeMap.getValue() == 1) {
                nodeWithOneParent.add(nodeMap.getKey());
            }
        }
        result.add(nodeWithOneParent);
        result.add(nodeWithNoParent);
        return result;
    }
}
