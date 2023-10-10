package com.karat.accuraterules;

import java.util.Map;

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
      //  Map<String, Map<String, String>> paymentIdEmailMap =
        return result;
    }
}
