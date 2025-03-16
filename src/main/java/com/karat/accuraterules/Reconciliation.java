package com.karat.accuraterules;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class Reconciliation {

    public static void main(String[] args) {
        String[][] users = {
                {"John A.", "john.a@mail.com", "Top", "3"},
                {"James S.", "j.s@mail.com", "Economy", "2"},
                {"Stacy C.", "stacy.c@test.com", "Economy", "2"},
                {"Bobby B.", "bob@mail.com", "Medium", "10"},
                {"Michelle X.", "mix@test.com", "Medium", "10"},
                {"Linda F.", "l.f@mail.com", "Top", "10"},
                {"Betty T.", "b.t@mail.com", "ThreeEco", "1"},
                {"Nancy L.", "n.l@test.com", "TwoEco", "1"},
                {"Daniel O.", "d.o@mail.com", "OneEco", "1"},
                {"Mike E.", "m.e@mail.com", "FourEco", "1"},
                {"Matthew R.", "mr@test.com", "OneEco", "5"},
                {"Albert K.", "albert@test.com", "OneEco", "5"}
        };

        String[][] payments = {
                {"1", "john2@mail.com", "33"},
                {"2", "michelle@mail.com", "60"},
                {"3", "stacy.c@test.com", "8"},
                {"4", "james@mail.com", "8"},
                {"5", "bob@mail.com", "60"},
                {"6", "email not found", "110"},
                {"7", "email not found", "1"},
                {"8", "email not found", "2"},
                {"9", "email not found", "3"},
                {"99", "email not found", "4"},
                {"10", "mr@test.com", "5"},
                {"11", "a@mail.com", "5"}
        };

        String[][] prices = {
                {"Economy", "4"},
                {"Top", "11"},
                {"Medium", "6"},
                {"OneEco", "1"},
                {"TwoEco", "2"},
                {"ThreeEco", "3"},
                {"FourEco", "4"}
        };
        System.out.println(reconciliation(prices, users, payments));
    }

    private static String reconciliation(String[][] prices, String[][] users, String[][] payments) {
        String result = "";
        Map<String, Integer> paymentToUserMap = new HashMap<>();
        Map<String, Integer> classToPriceMap = Arrays
                .stream(prices)
                .collect(toMap(x -> x[0], x -> Integer.parseInt(x[1])));
        Map<String, String> usersToEmailMap = Arrays
                .stream(users)
                .collect(toMap(x ->x[1], x-> x[0]));
        Map<Integer, List<String>> pricesPaidToUserMap = Arrays
                .stream(users)
                .collect(Collectors.groupingBy(
                        i -> classToPriceMap.get(i[2]) * Integer.parseInt(i[3]), // Compute the key as multiplication
                        Collectors.mapping(i -> i[0], Collectors.toList()) // Collect values into List
                ));
        Map<String, Integer> usersToPricePaidMap = Arrays
                .stream(users)
                .collect(toMap(i -> i[0],
                        i ->classToPriceMap.get(i[2]) * Integer.parseInt(i[3])// Compute the key as multiplication

                ));
        Map<String, Map<Integer, Integer>> paymentIdToEmailMap = Arrays
                .stream(payments)
                .collect(Collectors.groupingBy(
                        entry -> entry[1], // Group by email
                        Collectors.toMap(
                                entry -> Integer.parseInt(entry[0]), // Payment ID as key
                                entry -> Integer.parseInt(entry[2])
                        )));
        Map<Integer, Integer> paymentIdToPaymentMap = Arrays
                .stream(payments)
                .collect(toMap(i -> Integer.parseInt(i[0]), i->Integer.parseInt(i[2])));
        System.out.println(classToPriceMap);
        System.out.println(usersToEmailMap);
        System.out.println(pricesPaidToUserMap);
        System.out.println(paymentIdToEmailMap);
        System.out.println(usersToPricePaidMap);
        for(var userToEmail: usersToEmailMap.entrySet()){
            if(paymentIdToEmailMap.containsKey(userToEmail.getKey())){
                paymentToUserMap.put(usersToEmailMap.get(userToEmail.getKey()), new ArrayList<>(paymentIdToEmailMap.get(userToEmail.getKey()).keySet()).get(0));
                usersToPricePaidMap.remove(usersToEmailMap.get(userToEmail.getKey()));            }
        }
        for(var paymentIdToPayment: paymentIdToPaymentMap.entrySet()){
            var values = pricesPaidToUserMap.get(paymentIdToPayment.getValue());
            for(var val : values){
                if(!paymentToUserMap.containsKey(val)){
                    paymentToUserMap.put(val, paymentIdToPayment.getKey());
                }
            }
        }
        System.out.println(paymentToUserMap);
        return result;
    }
}
