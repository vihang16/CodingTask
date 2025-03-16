package com.karat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindMatchingTiles {

    public static void main(String[] args) {
        String[] titles = {"in the village of magic", "girl from a mystery village", "a mysterious planet of mystery", "magical creatures", "a mystery in the village", "mystery island", "lost in a mist", "the village in the wild", "harper", "the magical tree"};

        String query1 = "qhelvillage";
        String query2 = "the village";
        String query3 = "qhe vilxxe";
        String query4 = "a mxstery";
        System.out.println(findMatchingTitles(titles, query1));
        System.out.println(findMatchingTitles(titles, query2));
        System.out.println(findMatchingTitles(titles, query3));
        System.out.println(findMatchingTitles(titles, query4));

    }

    private static List<String> findMatchingTitles(String[] titles, String query) {
        int incorrectCount =0;
        List<String> result = new ArrayList<>();
        String[] queries = query.split(" ");
        Map<Character, Integer> queryMap = createQueryMap(queries);
        for(String title: titles){
            String[] words = title.split(" ");

        }
        return result;
    }

    private static Map<Character, Integer> createQueryMap(String[] queries) {
        var map = new HashMap<>();
        for(String query: queries){

        }
        return null;
    }
}
