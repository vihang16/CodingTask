package com.karat.allInTheFamily;

import java.util.*;

public class CQ3 {

    public static void main(String[] args) {
        int[][] pairs1 = new int[][] {
                {2, 3}, {3, 15}, {3, 6}, {5, 6}, {5, 7},
                {4, 5}, {4, 8}, {4, 9}, {9, 11}, {14, 4}
        };

        int[][] pairs2 = new int[][] {
                {2, 3}, {3, 15}, {3, 6}, {5, 6}, {5, 7},
                {4, 5}, {4, 8}, {4, 9}, {9, 11}, {14, 2}, {1, 9}
        };
        /*System.out.println(findEarliestAncestor(pairs1,8));
        System.out.println(findEarliestAncestor(pairs1, 7)); //=> 14
        System.out.println(findEarliestAncestor(pairs1, 6) );//=> 14
        System.out.println(findEarliestAncestor(pairs1, 15) );//=> 2
        System.out.println(findEarliestAncestor(pairs1, 14)); //=> null or -1
        System.out.println(findEarliestAncestor(pairs1, 11));// => 14
        System.out.println(findEarliestAncestor(pairs2,8));*/
        System.out.println(findEarliestAncestor(pairs2, 7)); //=> 14
       /* System.out.println(findEarliestAncestor(pairs2, 6) );//=> 14
        System.out.println(findEarliestAncestor(pairs2, 15) );//=> 2
        System.out.println(findEarliestAncestor(pairs2, 14)); //=> null or -1
        System.out.println(findEarliestAncestor(pairs2, 11));// => 14*/

    }

    private static int findEarliestAncestor(int[][] pairs, int node) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        int result = -1;
        for(int[] pair: pairs){
            map.compute(pair[1], (k,v) ->{
                v = v == null ? new HashSet<>(): v;
                v.add(pair[0]);
                return v;
            } );
        }
        Map<Integer, Integer> resultMap = getAllChildrent(node, map);
        for(int nodes : resultMap.keySet()){
            if(resultMap.get(nodes) > max){
                max = resultMap.get(nodes);
                result = nodes;
            }
        }
        return result;
    }

    private static Map<Integer, Integer> getAllChildrent(Integer key, Map<Integer, Set<Integer>> parentChildMap){
        Set<Integer> children =  parentChildMap.getOrDefault(key, new HashSet<>());
        Map<Integer, Integer> resultMap = new HashMap<>();
        for(int node : children)
            resultMap.put(node, 1);
        Set<Integer> result = new HashSet<>();
        result.addAll(children);
        System.out.println(parentChildMap);
        LinkedList<Integer> childrenList = new LinkedList<>(children);
        while( !childrenList.isEmpty()){
            int c = childrenList.pop();
            Set<Integer> grandChildren = parentChildMap.getOrDefault(c, new HashSet<>());

            for(int grandChild : grandChildren)
                        resultMap.put(grandChild, resultMap.get(c) + 1);
            childrenList.addAll(new LinkedList<>(grandChildren));
            children.addAll(grandChildren);

        }
        System.out.println(resultMap);
        return resultMap;
    }
}
