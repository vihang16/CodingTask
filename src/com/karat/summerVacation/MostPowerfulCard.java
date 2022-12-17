//https://central.karat.io/questions/1462/guide#2-1
package com.karat.summerVacation;

import java.util.*;

public class MostPowerfulCard {
    public static void main(String[] args) {
        String[][] matchups_1 = {
                {"giant", "wizard"},
                {"giant", "nymph"},
                {"wizard", "elf"},
                {"nymph", "muse"},
                {"orc", "elf"},
                {"orc", "goblin"},
                {"orc", "snake"}
        };
        String[][] matchups_2 = {
                {"wizard", "elf"},
                {"elf", "elemental"},
                {"roc", "wizard"},
                {"roc", "nymph"},
                {"nymph", "satyr"},
                {"nymph", "muse"},
                {"orc", "elf"}
        };
        String[][] matchups_3 = {
                {"vampire", "beholder"},
                {"beholder", "elf"},
                {"elf", "orc"},
                {"orc", "snake"},
                {"snake", "hamster"},
                {"dragon", "roc"},
                {"dragon", "goblin"},
                {"dragon", "nymph"},
                {"dragon", "giant"}
        };
        String[][] matchups_4 = {
                {"dragon", "roc"},
                {"orc", "elf"},
                {"nymph", "muse"},
                {"snake", "hamster"},
                {"vampire", "beholder"},
                {"elf", "elemental"}
        };
        String[][] matchups_5 = {
                {"giant", "wizard"},
                {"giant", "nymph"},
                {"giant", "orc"},
                {"wizard", "orc"},
                {"wizard", "nymph"},
                {"orc", "nymph"},
                {"elf", "goblin"},
                {"elf", "spider"},
                {"elf", "rat"},
                {"elf", "kobold"}
        };
        String[][] matchups_6 = {
                {"giant", "wizard"},
                {"giant", "nymph"},
                {"giant", "orc"},
                {"wizard", "orc"},
                {"wizard", "nymph"},
                {"orc", "nymph"},
                {"elf", "goblin"},
                {"elf", "spider"},
                {"elf", "rat"},
                {"elf", "kobold"},
                {"human", "elf"}
        };
        String[][] matchups_7 = {
                {"giant", "wizard"},
                {"giant", "orc"},
                {"wizard", "orc"},
                {"wizard", "nymph"},
                {"dinosaur", "human"},
                {"orc", "nymph"},
                {"elf", "goblin"},
                {"elf", "kobold"},
                {"human", "elf"},
                {"elf", "spider"},
                {"elf", "rat"},
                {"giant", "nymph"}
        };
        System.out.println(bestCard(matchups_1));
        System.out.println(bestCard(matchups_2));
        System.out.println(bestCard(matchups_3));
        System.out.println(bestCard(matchups_4));
        System.out.println(bestCard(matchups_5));
        System.out.println(bestCard(matchups_6));
        System.out.println(bestCard(matchups_7));
    }

    private static String bestCard(String[][] matchups) {
        Map<String, Set<String>> cardOrderMap =  new HashMap<>();
        int max = Integer.MIN_VALUE;
        String result = "";
        for(String[] matchUp: matchups){
            cardOrderMap.compute(matchUp[0], (k,v) ->{
               v = v == null? new HashSet<>():v;
               v.add(matchUp[1]);
               return v;
            });
        }
        //System.out.println(cardOrderMap);
        for(String card: cardOrderMap.keySet()){
            Set<String> allLeastPowerfulCards = bfs(card, cardOrderMap);
            cardOrderMap.put(card, allLeastPowerfulCards);
        }
        for(String card: cardOrderMap.keySet()){
            if(cardOrderMap.get(card).size() > max){
                max = cardOrderMap.get(card).size();
                result = card;
            }
        }
        //System.out.println(cardOrderMap);
        return result;
    }

    private static Set<String> bfs(String card, Map<String, Set<String>> cardOrderMap) {
        Set<String> result = new HashSet<>();
        LinkedList<String> allChildList = new LinkedList<>(cardOrderMap.get(card));
        result.addAll(allChildList);
        while(!allChildList.isEmpty()){
            String subChilds = allChildList.pop();
            if(cardOrderMap.containsKey(subChilds))
               allChildList.addAll(cardOrderMap.get(subChilds));
            result.add(subChilds);
        }
        return  result;
    }
}
