package com.karat.library.bandersnatch;

import java.util.List;

public class CQ2 {

    public static void main(String[] args) {
        int[] goodEndings = {10, 15, 25, 34};
        int[] badEndings = {21, 30, 40};

        int[][] choices1 = {{3, 16, 24}};
        int[][] choices2 = {{3, 16, 20}};
        int[][] choices3 = {{3, 2, 19}, {20, 21, 34}};
        int[][] choices4 = {};
        int[][] choices5 = {{9, 16, 26}, {14, 16, 13}, {27, 29, 28}, {28, 15, 34}, {29, 30, 38}};
        int[][] choices6 = {{9, 16, 26}, {13, 31, 14}, {14, 16, 13}, {27, 12, 24}, {32, 34, 15}};
        int[][] choices7 = {{3, 9, 10}};

        System.out.println(findGoodEndings(goodEndings, badEndings, choices1));
    }

    private static List<Integer> findGoodEndings(int[] goodEndings, int[] badEndings, int[][] choices) {

    }
}
