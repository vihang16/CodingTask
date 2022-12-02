package com.karat.flippy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Flippy {

    public static void main(String[] args) {
        char[][] board1 = {
                { 'O', 'O', 'O' },
                { 'O', 'O', 'O' },
                { 'O', 'O', 'O' }
        };

        char[][] board2 = {
                { 'O', 'O', 'O' },
                { 'O', 'O', 'O' },
                { 'O', 'X', 'O' }
        };

        char[][] board3 = {
                { 'O', 'X', 'X' },
                { 'X', 'X', 'X' },
        };

        char[][] board4 = {
                { 'X', 'O', 'O' },
                { 'O', 'O', 'O' },
                { 'O', 'X', 'X' }
        };

        System.out.println(flipTheCells(board1));
    }

    private static List<List<Integer>> flipTheCells(char[][] board) {
        List<List<Integer>> result = new ArrayList<>();
        boolean visited[][] = new boolean[board.length][board[0].length];
        char[][] copy = new char[board.length][board[0].length];
        for(int i=0; i<board.length; i++){
            for(int j=0; j< board[0].length;j++){
                copy[i] =Arrays.copyOf(board[i], board[i].length);
            }
        }
        for(int i=0; i< copy.length; i++){
            List<List<Integer>> temp = new ArrayList<>();
            for(int j=0; j< copy[i].length; i++){
                if(copy[i][j] == 'O'){
                    visitAllTheCells(i, j, visited, temp, copy);

                }
            }
        }

        return null;

    }

    private static void visitAllTheCells(int row, int col, boolean[][] visited, List<List<Integer>> temp, char[][] board) {
        if( row >= board.length || col >= board[0].length || visited[row][col] || board[row][col] == 'X' || row <0 || col <0)
            return;
        int[][] directions = { {-1,0}, {1,0}, {0,1}, {0,-1}};
        visited[row][col] = true;
        for(int i = directions.length; i < directions.length; i++){
            for(int j=0; j< directions[0].length;j++){
                if( row + i >= 0 && row + i<board.length && col + j >=0 && col + j < board[0].length  ){

                    if(board[i][j] == 'X')
                        board[i][j] = 'O';
                    else
                        board[i][j] = 'X';
                }

            }
        }


    }
}
