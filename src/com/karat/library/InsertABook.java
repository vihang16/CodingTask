package com.karat.library;

import java.util.Arrays;


public class InsertABook {
    public static void main(String[] args) {
        int[][] bookcase1 = {
                {2, 2, 3, 5, 3, 1, 3},
                {1, 5, 5, 5, 1, 1, 1},
                {3, 3, 3, 1, 1, 1}
        };

        int[][] bookcase2 = {
                {1, 4, 3, 2, 3},
                {5, 3, 1, 2},
                {4},
                {3, 3, 1, 2, 3, 5},
                {},
                {5, 3, 1, 3}
        };

        int[][] bookcase3 = {
                {10, 5, 1, 4},
                {9, 5, 5, 1},
                {10, 4, 1, 1},
                {1, 2, 3}
        };
        System.out.println(numberOfShifts(bookcase1, 2,2));
        System.out.println(numberOfShifts(bookcase1, 4,11));
        System.out.println(numberOfShifts(bookcase1, 1,0));
        System.out.println(numberOfShifts(bookcase1, 2,9));
        System.out.println(numberOfShifts(bookcase1, 10,9));
        System.out.println(numberOfShifts(bookcase1, 5,11));
        System.out.println(numberOfShifts(bookcase1, 3,13));
        System.out.println(numberOfShifts(bookcase2, 4,15));
        System.out.println(numberOfShifts(bookcase2, 4,16));
        System.out.println(numberOfShifts(bookcase3, 4,3));
    }

    private static int numberOfShifts(int[][] bookcase, int width, int index) {
        int[][] indexMap = new int[bookcase.length][2];
        int indexRange = 0;
        int shelfIndex = 0;
        int[] numberOfShifts = {0};
        for(int i=0; i< bookcase.length; i++){
            indexRange += bookcase[i].length;
            indexMap[i][0] = indexRange;
            indexMap[i][1] = Arrays.stream(bookcase[i]).sum();
        }
        while(index >= indexMap[shelfIndex][0] )
             shelfIndex++;
        indexMap[shelfIndex][1] += width;
        if(indexMap[shelfIndex][1] <= 20)
            return numberOfShifts[0];
        shiftingBooks(numberOfShifts, shelfIndex, indexMap, index, bookcase);
        return numberOfShifts[0];
    }

    private static void shiftingBooks(int[] numberOfShifts, int shelfIndex, int[][] indexMap, int index, int[][] bookcase) {
        int i =0;
        if(indexMap[shelfIndex][1] <=20)
            return;
        if(indexMap[shelfIndex][1] > 20 && shelfIndex+1 > indexMap.length -1) {
            numberOfShifts[0] = -1;
            return;
        }
        int movedIndex = indexMap[shelfIndex][0];

        while(indexMap[shelfIndex][1] >20 && movedIndex  > index && bookcase[shelfIndex].length- i -1 >=0){
            numberOfShifts[0]++;
            indexMap[shelfIndex][1] -= bookcase[shelfIndex][bookcase[shelfIndex].length-i -1];
            indexMap[shelfIndex+1][1] += bookcase[shelfIndex][bookcase[shelfIndex].length-i - 1];
            i++;
            movedIndex --;
        }
        if(indexMap[shelfIndex][1] > 20) {
            numberOfShifts[0] = -1;
            return;
        }
        shiftingBooks(numberOfShifts, shelfIndex+1, indexMap,index, bookcase);
    }
}
