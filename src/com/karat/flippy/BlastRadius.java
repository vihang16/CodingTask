//https://central.karat.io/questions/552/guide#2-1
package com.karat.flippy;

import java.util.ArrayList;
import java.util.List;

public class BlastRadius {
    public static void main(String[] args) {
        char[][] board1 = {
                { ' ', ' ', ' ', ' ' },
                { ' ', 'W', 'O', 'O' },
                { ' ', 'O', ' ', 'O' },
                { 'W', ' ', ' ', 'W' },
        };

        char[][] board2 = {
                { ' ', ' ', 'O', ' ' },
                { ' ', 'W', ' ', 'O' },
                { ' ', 'W', 'O', 'O' },
                { ' ', 'O', ' ', 'O' },
        };

        char[][] board3 = {
                { 'O', 'O', 'O' },
                { 'O', 'O', 'O' },
                { 'O', 'O', ' ' }
        };

        char[][] board4 = {
                { 'O', 'O', 'O' },
                { 'O', ' ', 'O' },
                { 'O', 'O', ' ' }
        };
        System.out.println(blast(board1, 1));
    }

    private static List<Integer[]> blast(char[][] board, int radius) {
        List<int[]> spaceIndex =  new ArrayList<>();
        List<int[]> zeroIndex = new ArrayList<>();
        for(int i=0;i< board.length; i++){
            for(int j=0; j< board[0].length; j++){
              if(board[i][j] == ' ')
                  spaceIndex.add(new int[]{i, j});
              else if (board[i][j] == 'O')
                  zeroIndex.add(new int[]{i,j});

            }
        }
        for(int[] space : spaceIndex){
            int row = space[0];
            int col = space[1];
            findAdjacentEffectiveCells(row, col, new ArrayList<>(), radius);
        }
       return null;
    }
    private static void findAdjacentEffectiveCells(int row, int col, List<Integer[]> allNearByCells, int radius){

    }
}
