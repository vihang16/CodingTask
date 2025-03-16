//https://central.karat.io/questions/1219/guide#1-1
package com.karat.campingtrip;

import java.util.*;


import static java.util.stream.Collectors.*;

public class GroceryList {
    public static void main(String[] args) {
        String[][] products = {
                {"Cheese",          "Dairy"},
                {"Carrots",         "Produce"},
                {"Potatoes",        "Produce"},
                {"Canned Tuna",     "Pantry"},
                {"Romaine Lettuce", "Produce"},
                {"Chocolate Milk",  "Dairy"},
                {"Flour",           "Pantry"},
                {"Iceberg Lettuce", "Produce"},
                {"Coffee",          "Pantry"},
                {"Pasta",           "Pantry"},
                {"Milk",            "Dairy"},
                {"Blueberries",     "Produce"},
                {"Pasta Sauce",     "Pantry"}
        };

        String[] list1 = {"Blueberries", "Milk", "Coffee", "Flour", "Cheese", "Carrots"};
        String[] list2 = {"Blueberries", "Carrots", "Coffee", "Milk", "Flour", "Cheese"};
        String[] list3 = {"Blueberries", "Carrots", "Romaine Lettuce", "Iceberg Lettuce"};
        String[] list4 = {"Milk", "Flour", "Chocolate Milk", "Pasta Sauce"};
        String[] list5 = {"Cheese", "Potatoes", "Blueberries", "Canned Tuna"};

        System.out.println(shopping(products, list1));
        System.out.println(shopping(products, list2));
        System.out.println(shopping(products, list3));
        System.out.println(shopping(products, list4));
        System.out.println(shopping(products, list5));
    }

    public static int shopping(String[][] products, String[] list) {
        Map<String, List<String>> productMap = Arrays.stream(products)
                                             .collect(groupingBy( row -> row[1],
                                                     mapping( row -> row[0], toList())));

        Map<String, String> prodToCatMap = Arrays.stream(products)
                .collect(toMap( row -> row[0], row -> row[1]));
        int initialTrip = 1;
        int optimizedTrip = 0;
        Set<String> shoppingSet = Arrays.stream(list)
                .collect(toSet());
        String prev= prodToCatMap.get(list[0]);
        for(int i=1; i< list.length; i++){
            if(!prodToCatMap.get(list[i]).equals(prev)){
                prev = prodToCatMap.get(list[i]);
                initialTrip++;
            }
        }

        //shoppingSet.remove(list[0]);
        //shoppingSet.remove(list[0]);
        //System.out.println("original trip:"+initialTrip);

        while(!shoppingSet.isEmpty()){
            //System.out.println(prev);
            List<String> otherItems = productMap.get(prev);
            //System.out.println(otherItems);
            for(String otherItem : otherItems){
                if(!shoppingSet.isEmpty() && shoppingSet.contains(otherItem)){
                    shoppingSet.remove(otherItem);
                }
            }
            //System.out.println("shopping set:"+shoppingSet);
            optimizedTrip++;
            if(!shoppingSet.isEmpty())
                prev = prodToCatMap.get(new ArrayList<>(shoppingSet).get(0));
        }
        //System.out.println("optimized trip:"+optimizedTrip);

        return initialTrip - optimizedTrip;
    }
}
