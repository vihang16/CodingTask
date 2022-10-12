//https://central.karat.io/questions/536/guide#1-1
package com.karat.library;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SpherePuzzle {

    public static void main(String[] args) {
        SpherePuzzle sp = new SpherePuzzle();
       /* System.out.println(sp.minDistance("..b.r..r.R.B...b"));
        System.out.println(sp.minDistance("RBGYygbr"));
        System.out.println(sp.minDistance(".........."));*/
        System.out.println(sp.minDistance("abcbabbcbaAabcabcabbBabbabcabcbbC"));
        System.out.println(sp.minDistance("rRBGYygbr"));
    }

    private int minDistance(String s) {
        Map<Character, Integer> sphereMap = new HashMap<>();
        Map<Character, Integer> sphereDistanceMap = new HashMap<>();
        Map<Character, Integer> holeMap = new HashMap<>();
        for(int i=0; i< s.toCharArray().length;i++){
            if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'){
                sphereMap.put(s.charAt(i),i);
                char c = String.valueOf(s.charAt(i)).toLowerCase().charAt(0);
                if(holeMap.containsKey(c)){
                    int index = holeMap.get(c);
                    int dist  = Math.abs(index - i);
                    if(sphereDistanceMap.containsKey(s.charAt(i)) && sphereDistanceMap.get(s.charAt(i)) > dist){
                        sphereDistanceMap.put(s.charAt(i), dist);
                    }else
                        sphereDistanceMap.put(s.charAt(i),dist);
                }
            }else if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z'){
                holeMap.put(s.charAt(i), i);
                char c = String.valueOf(s.charAt(i)).toUpperCase().charAt(0);
                if(sphereMap.containsKey(c)){
                    int index = sphereMap.get(c);
                    int dist  = Math.abs(index - i);
                    if(sphereDistanceMap.containsKey(c) && sphereDistanceMap.get(c) > dist){
                        sphereDistanceMap.put(c, dist);
                    }else if(!sphereDistanceMap.containsKey(c))
                        sphereDistanceMap.put(c,dist);
                }
            }
        }
        return sphereDistanceMap
                .values()
                .stream()
                .mapToInt(x ->x)
                .sum();
    }
}
