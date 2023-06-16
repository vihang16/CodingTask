//https://central.karat.io/questions/297/guide#1-1
package com.karat.riddler;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SubSudoku {

    public static void main(String[] args) {
        int[][] grid1 = {
                {2, 3, 1},
                {1, 2, 3},
                {3, 1, 2},
        };
        int[][] grid2 = {
                {1, 2, 3},
                {3, 2, 1},
                {3, 1, 2},
        };
        int[][] grid3 = {
                {2, 2, 3},
                {3, 1, 2},
                {2, 3, 1},
        };
        int[][] grid4 = {
                {1},
        };
        int[][] grid5 = {
                {-1, -2, -3},
                {-2, -3, -1},
                {-3, -1, -2},
        };
        int[][] grid6 = {
                {1, 3, 3},
                {3, 1, 2},
                {2, 3, 1},
        };
        int[][] grid7 = {
                {1, 2, 3, 4},
                {4, 3, 2, 1},
                {1, 3, 2, 4},
                {4, 2, 3, 1},
        };
        int[][] grid8 = {
                {0, 3},
                {3, 0},
        };
        int[][] grid9 = {
                {0, 1},
                {1, 0},
        };
        int[][] grid10 = {
                {0, 2},
                {2, 0},
        };
        int[][] grid11 = {
                {1, 2, 3, 4},
                {2, 3, 1, 4},
                {3, 1, 2, 4},
                {4, 2, 3, 1},
        };
        int[][] grid12 = {
                {-1, -2, 12, 1},
                {12, -1, 1, -2},
                {-2, 1, -1, 12},
                {1, 12, -2, -1},
        };
        int[][] grid13 = {
                {2, 3, 3},
                {1, 2, 1},
                {3, 1, 2},
        };
        int[][] grid14 = {
                {1, 3},
                {3, 1},
        };
        int[][] grid15 = {
                {2, 3},
                {3, 2},
        };
        int[][] grid16 = {
                {3, 2},
                {2, 3},
        };

        System.out.println(validateSudoku(grid1));
        System.out.println(validateSudoku(grid2));
        System.out.println(validateSudoku(grid3));
        System.out.println(validateSudoku(grid4));
        System.out.println(validateSudoku(grid5));
        System.out.println(validateSudoku(grid6));
        System.out.println(validateSudoku(grid7));
        System.out.println(validateSudoku(grid8));
        System.out.println(validateSudoku(grid9));
        System.out.println(validateSudoku(grid10));
        System.out.println(validateSudoku(grid11));
        System.out.println(validateSudoku(grid12));
        System.out.println(validateSudoku(grid13));
        System.out.println(validateSudoku(grid14));
        System.out.println(validateSudoku(grid15));
        System.out.println(validateSudoku(grid16));
    }

    private static boolean validateSudoku(int[][] grid) {
        boolean result = true;
        Set<Integer> numbers  = IntStream.range(1, grid.length+1)
                                         .boxed()
                                        .collect(Collectors.toSet());
        Set<Integer> arrayNumbers =  new HashSet<>();
        for(int i=0; i< grid.length; i++){
            for(int j=0; j< grid[0].length; j++){
                arrayNumbers.add(grid[i][j]);
            }
            if(!arrayNumbers.equals(numbers)){
                result = false;
                break;
            }
            arrayNumbers = new HashSet<>();
        }
        if(!result)
            return result;
        arrayNumbers =  new HashSet<>();
        for(int i=0; i< grid.length; i++){
            for(int j=0; j< grid[0].length; j++){
                arrayNumbers.add(grid[j][i]);
            }
            if(!arrayNumbers.equals(numbers)){
                result = false;
                break;
            }
            arrayNumbers = new HashSet<>();
        }

        return result;
    }
}
