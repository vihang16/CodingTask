package com.karat.allInTheFamily;

import java.util.*;

public class CQ2 {

    public static void main(String[] args) {
        int[][] pairs = new int[][] {
                {1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5}, {15, 21},
                {4, 8}, {4, 9}, {9, 11}, {14, 4}, {13, 12}, {12, 9},
                {15, 13}
        };
        /*System.out.println(hasCommonAncestor(pairs, 3,8));
        System.out.println(hasCommonAncestor(pairs, 5, 8));//    => true
        System.out.println(hasCommonAncestor(pairs, 6, 8));//    => true
        System.out.println(hasCommonAncestor(pairs, 6, 9));//    => true
        System.out.println(hasCommonAncestor(pairs, 3, 8));//    => false
        System.out.println(hasCommonAncestor(pairs, 1, 3));//    => false
        System.out.println(hasCommonAncestor(pairs, 3, 1));//    => false */
        System.out.println(hasCommonAncestor(pairs, 7, 11));//   => true
        /*System.out.println( hasCommonAncestor(pairs, 6, 5));//    => true
        System.out.println(hasCommonAncestor(pairs, 5, 6));//    => true
        System.out.println(hasCommonAncestor(pairs, 3, 6));//   => true
        System.out.println(hasCommonAncestor(pairs, 21, 11));// => true*/
    }

    private static boolean hasCommonAncestor(int[][] pairs, int node1, int node2) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int[] pair: pairs){
            map.compute(pair[1], (k,v) ->{
                v = v == null ? new HashSet<>(): v;
                v.add(pair[0]);
                return v;
            } );
        }
        Set<Integer> parent1 = getAllChildrent(node1, map);
        Set<Integer> parent2 = getAllChildrent(node2, map);
        for(int data: parent1)
            if(parent2.contains(data))
                return true;
        return false;
    }
    private static Set<Integer> getAllChildrent(Integer key, Map<Integer, Set<Integer>> parentChildMap){
        Set<Integer> children =  parentChildMap.getOrDefault(key, new HashSet<>());
        Set<Integer> result = new HashSet<>();
        result.addAll(children);
        LinkedList<Integer> childrenList = new LinkedList<>(children);
        while( !childrenList.isEmpty()){
            int c = childrenList.pop();
            Set<Integer> grandChildren = parentChildMap.get(c);
            if(grandChildren != null){
                childrenList.addAll(new LinkedList<>(grandChildren));
                children.addAll(grandChildren);
            }
        }
        return children;
    }
}
