//https://central.karat.io/questions/1453/guide#2-1

package com.karat.cateringService;

import java.util.*;
import java.util.stream.Collectors;

public class RestaurantRecommendation {
    public static void main(String[] args) {
        int[][] likes_data_1 = {
                {10, 7, 1, 8, 6},
                {8, 3, 7, 2, 1},
                {6, 7, 10, 4, 5},
                {4, 3, 8, 6},
                {1, 6, 10, 4, 8}
        };
        int[] customer_likes_1_1 = {1, 4, 10};
        int[][] likes_data_2 = {
                {11, 15, 8, 3},
                {10, 13, 12, 5, 6, 1},
                {2, 9, 5, 15, 11, 8},
                {14, 5, 13, 11, 4, 8},
                {14, 8, 9, 15, 2},
                {2, 13, 1, 10, 4, 7},
                {11, 2, 12, 6, 15},
                {2, 10, 8, 15, 12, 5},
                {9, 15, 2, 14, 5},
                {4, 6, 3, 13, 12}
        };
        int[] customer_likes_2_1 = {11, 7};
        int[] customer_likes_2_2 = {1, 13};
        int[] customer_likes_2_3 = {2, 8};
        int[][] likes_data_3 = {
                {4, 10, 6, 13, 2, 5},
                {10, 3, 14},
                {9, 4, 8, 14, 15, 5, 12},
                {14, 1, 7, 12, 15, 10, 5},
                {15, 1, 4, 10, 7, 13},
                {1, 10, 8, 14, 6, 15, 3},
                {8, 15, 6, 1, 14, 3},
                {1, 8, 15, 10},
                {14, 10, 7, 9, 6, 3, 13},
                {11, 3, 15}
        };
        int[] customer_likes_3_1 = {1, 5, 7, 12, 14};
        int[] customer_likes_3_2 = {1, 2, 5, 10, 14};
        int[] customer_likes_3_3 = {1, 9, 11, 12, 15};
        int[] customer_likes_3_4 = {11, 15};
        System.out.println(recommendations(likes_data_1, customer_likes_1_1));
        System.out.println(recommendations(likes_data_2, customer_likes_2_1));
        System.out.println(recommendations(likes_data_2, customer_likes_2_2));
        System.out.println(recommendations(likes_data_2, customer_likes_2_3));
        System.out.println(recommendations(likes_data_3, customer_likes_3_1));
        System.out.println(recommendations(likes_data_3, customer_likes_3_2));
        System.out.println(recommendations(likes_data_3, customer_likes_3_3));
        System.out.println(recommendations(likes_data_3, customer_likes_3_4));

    }

    private static int recommendations(int[][] likesData, int[] customerLikes) {
        Set<Integer> customerLikesSet = Arrays
                                        .stream(customerLikes)
                                        .boxed()
                                        .collect(Collectors.toSet());
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int [] otherCustomerDatas : likesData){
            Set<Integer> otherCustomerLikesSet = Arrays
                                                .stream(otherCustomerDatas)
                                                .boxed()
                                                .collect(Collectors.toSet());
            otherCustomerLikesSet.retainAll(customerLikesSet);
            if(otherCustomerLikesSet.size() >=2)
                for(int otherCustomerData : otherCustomerDatas){
                    if(!customerLikesSet.contains(otherCustomerData))
                        freqMap.merge(otherCustomerData, 1, (prev,one) -> prev + one);
                }
        }

        int max = -1;
        int result =  -1;
        for(int customerData : freqMap.keySet()){
            if(max < freqMap.get(customerData)){
                max = freqMap.get(customerData);
                result =  customerData;
            }
        }

        return result;
    }
}
