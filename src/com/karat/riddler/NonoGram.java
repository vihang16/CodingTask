//https://central.karat.io/questions/297/guide#2-1
package com.karat.riddler;

public class NonoGram {

    public static void main(String[] args) {

        char[][] matrix1 = {
                {'W','W','W','W'},
                {'B','W','W','W'},
                {'B','W','B','B'},
                {'W','W','B','W'},
                {'B','B','W','W'}};

        int[][] rows1_1 = {{},{1},{1,2},{1},{2}};
        int[][] columns1_1 = {{2,1},{1},{2},{1}};

        int[][] rows1_2 = {{},{},{1},{1},{1,1}};
        int[][] columns1_2 = {{2},{1},{2},{1}};

        int[][] rows1_3 = {{},{1},{3},{1},{2}};
        int[][] columns1_3 = {{3},{1},{2},{1}};

        int[][] rows1_4 = {{},{1,1},{1,2},{1},{2}};
        int[][] columns1_4 = {{2,1},{1},{2},{1}};

        int[][] rows1_5 = {{},{1},{1},{1},{2}};
        int[][] columns1_5 = {{2,1},{1},{2},{1}};

        int[][] rows1_6 = {{},{1},{2,1},{1},{2}};
        int[][] columns1_6 = {{2,1},{1},{2},{1}};

        int[][] rows1_7 = {{},{1},{1,2},{1},{2,1}};
        int[][] columns1_7 = {{2,1},{1},{2},{1}};

        int[][] rows1_8 = {{1},{1},{1,2},{1},{2}};
        int[][] columns1_8 = {{2,1},{1},{2},{1}};

        char[][] matrix2 = {
                {'W','W'},
                {'B','B'},
                {'B','B'},
                {'W','B'}};

        int[][] rows2_1 = {{},{2},{2},{1}};
        int[][] columns2_1 = {{1,1},{3}};

        int[][] rows2_2 = {{},{2},{2},{1}};
        int[][] columns2_2 = {{3},{3}};

        int[][] rows2_3 = {{},{},{},{}};
        int[][] columns2_3 = {{},{}};

        int[][] rows2_4 = {{},{2},{2},{1}};
        int[][] columns2_4 = {{2,1},{3}};

        int[][] rows2_5 = {{},{2},{2},{1}};
        int[][] columns2_5 = {{2},{3}};

        int[][] rows2_6 = {{},{2},{2},{1}};
        int[][] columns2_6 = {{2},{1,1}};

        char[][] matrix3 = {
                {'B','W','B','B','W','B'}};

        int[][] rows3_1 = {{1,2,1}};
        int[][] columns3_1 = {{1},{},{1},{1},{},{1}};

        int[][] rows3_2 = {{1,2,2}};
        int[][] columns3_2 = {{1},{},{1},{1},{},{1}};

        System.out.println(validateNonogram(matrix1, rows1_1, columns1_1));
    }

    private static boolean validateNonogram(char[][] matrix, int[][] rows, int[][] columns) {
        boolean result  = true;
        for(int i=0; i < rows.length; i++){
            char[] row = matrix[i];
            int[] currentRow = rows[i];
            for(int j=0; j<rows[i].length; j++){

            }

        }
        return  result;
    }
}
