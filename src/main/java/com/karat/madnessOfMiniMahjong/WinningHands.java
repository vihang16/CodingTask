//https://central.karat.io/questions/446/guide#1-1
package com.karat.madnessOfMiniMahjong;

import java.util.HashMap;
import java.util.Map;

public class WinningHands {

    public static void main(String[] args) {
        String tiles1 = "88844";
        String tiles2 = "99";
        String tiles3 = "55555";
        String tiles4 = "22333333";
        String tiles5 = "73797439949499477339977777997394947947477993";
        String tiles6 = "111333555";
        String tiles7 = "42";
        String tiles8 = "888";
        String tiles9 = "100100000";
        String tiles10 = "346664366";
        String tiles11 = "8999998999899";
        String tiles12 = "17610177";
        String tiles13 = "600061166";
        String tiles14 = "6996999";
        String tiles15 = "03799449";
        String tiles16 = "64444333355556";
        System.out.println(complete(tiles1));
        System.out.println(complete(tiles2));
        System.out.println(complete(tiles3));
        System.out.println(complete(tiles4));
        System.out.println(complete(tiles5));
        System.out.println(complete(tiles6));
        System.out.println(complete(tiles7));
        System.out.println(complete(tiles8));
        System.out.println(complete(tiles9));
        System.out.println(complete(tiles10));
        System.out.println(complete(tiles11));
        System.out.println(complete(tiles12));
        System.out.println(complete(tiles13));
        System.out.println(complete(tiles14));
        System.out.println(complete(tiles15));
        System.out.println(complete(tiles16));

    }

    private static boolean complete(String tiles) {
        Map<Character, Integer> map = new HashMap<>();
        int pairs = 0;
        for(char c : tiles.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        for(char c: map.keySet()){
            if( map.get(c) % 3 == 2)
                pairs++;
            else if(map.get(c) % 3 != 0)
                return false;
        }
        return pairs == 1;

    }
}
