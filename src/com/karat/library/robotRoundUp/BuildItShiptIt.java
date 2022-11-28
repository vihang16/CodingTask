package com.karat.library.robotRoundUp;

import java.util.*;

public class BuildItShiptIt {
    public static void main(String[] args) {
        String[][] paths = new String[][] {
                {"A", "B"},
                {"A", "C"},
                {"B", "K"},
                {"C", "K"},
                {"E", "L"},
                {"F", "G"},
                {"J", "M"},
                {"E", "F"},
                {"G", "H"},
                {"G", "I"},
                {"C", "G"},
        };
        System.out.println(pathFinder(paths));

    }

    private static Map<String, Set<String>> pathFinder(String[][] paths) {
        Map<String, Set<String>> pathMap = new HashMap<>();
        Map<String, Set<String>> result = new HashMap<>();
        Map<String, Set<Set<String>>> updatedMap = new HashMap<>();
        for(String[] path: paths){
            pathMap.compute(path[0], (k,v) ->{
               v = v == null? new HashSet<>():v;
               v.add(path[1]);
               return v;
            });
        }
        System.out.println(pathMap);
        for(Map.Entry<String, Set<String>> map : pathMap.entrySet())
            dfs(pathMap, map.getKey(), new HashSet<String>(), updatedMap, map.getKey());
        System.out.println(updatedMap);
        Set<String> keysToRemove =  prepareKeyListToRemove(updatedMap);
        removeNonParentNodes(keysToRemove, updatedMap );
        result = prepareResult(updatedMap);
        return result;
    }

    private static Map<String, Set<String>> prepareResult(Map<String, Set<Set<String>>> updatedMap) {
        Map<String, Set<String>> result = new HashMap<>();
        for(Map.Entry<String, Set<Set<String>>> map: updatedMap.entrySet()){
            String key = map.getKey();
            result.put(key, new HashSet<>());
            for(Set<String> set: map.getValue()){
                String value = set.stream().reduce((prev, next) -> next).orElse(null);
                result.computeIfPresent(key, (k,v) -> v).add(value);
            }
        }
        return result;
    }

    private static void removeNonParentNodes(Set<String> keysToRemove, Map<String, Set<Set<String>>> updatedMap) {
        for(String key: keysToRemove)
             updatedMap.remove(key);
    }

    private static Set<String> prepareKeyListToRemove(Map<String, Set<Set<String>>> updatedMap) {
        Set<String> result = new HashSet<>();
        for(Map.Entry<String, Set<Set<String>>> map: updatedMap.entrySet()){
            String key = map.getKey();
            for(Map.Entry<String, Set<Set<String>>> map1: updatedMap.entrySet()){
                for(Set<String> set: map1.getValue()){
                    if(set.contains(key))
                        result.add(key);
                        //break;
                }
            }

        }
        return result;
    }

    private static void dfs(Map<String, Set<String>> pathMap, String key, Set<String> allPath, Map<String, Set<Set<String>>> updatedMap, String parent) {
        if(!pathMap.containsKey(key)) {
            //System.out.println("before resetting set:"+allPath);
            final Set<String> copy =  new HashSet<>(allPath);
            updatedMap.compute(parent, (k, v) -> {
                v = v == null ? new HashSet<>() : v;
                v.add(copy);
                return v;
            });
            return;
        }
        //allPath.addAll(pathMap.get(key));
        for(String child : pathMap.get(key)){
            allPath.add(child);
            //System.out.println("dfs before set:"+allPath);
            dfs(pathMap, child, allPath, updatedMap, parent);
            allPath = new HashSet<>();
            //System.out.println("dfs after set:"+allPath);
        }
    }
}
