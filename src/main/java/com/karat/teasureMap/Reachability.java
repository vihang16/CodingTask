package com.karat.teasureMap;

import java.util.LinkedList;

public class Reachability {
    public static void main(String[] args) {
        int[][] board1 = new int[][] {
                { 0,  0,  0, 0, -1 },
                { 0, -1, -1, 0,  0 },
                { 0,  0,  0, 0,  0 },
                { 0, -1,  0, 0,  0 },
                { 0,  0,  0, 0,  0 },
                { 0,  0,  0, 0,  0 },
        };

        int[][] board2 = new int[][] {
                {  0,  0,  0, 0, -1 },
                {  0, -1, -1, 0,  0 },
                {  0,  0,  0, 0,  0 },
                { -1, -1,  0, 0,  0 },
                {  0, -1,  0, 0,  0 },
                {  0, -1,  0, 0,  0 },
        };

        int[][] board3 = new int[][] {
                { 0,  0,  0,  0,  0,  0, 0 },
                { 0, -1, -1, -1, -1, -1, 0 },
                { 0, -1,  0,  0,  0, -1, 0 },
                { 0, -1,  0,  0,  0, -1, 0 },
                { 0, -1,  0,  0,  0, -1, 0 },
                { 0, -1, -1, -1, -1, -1, 0 },
                { 0,  0,  0,  0,  0,  0, 0 },
        };

        int[][] board4 = new int[][] {
                { 0,  0,  0,  0, 0 },
                { 0, -1, -1, -1, 0 },
                { 0, -1, -1, -1, 0 },
                { 0, -1, -1, -1, 0 },
                { 0,  0,  0,  0, 0 },
        };

        int[][] board5 = new int[][] {
                { 0 },
        };

        int[] end1 = new int[] {0, 0};
        int[] end2 = new int[] {5, 0};
        System.out.println(isReachable(board1, end1));
        System.out.println(isReachable(board1, end2));
        System.out.println(isReachable(board2, end1));
        System.out.println(isReachable(board2, end2));
        System.out.println(isReachable(board3, end1));
        System.out.println(isReachable(board4, end1));
        System.out.println(isReachable(board5, end1));
    }

    private static boolean isReachable(int[][] board, int[] end) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        int row = end[0];
        int col = end[1];
        int[][] moves = { {1,0}, { -1, 0}, {0,1}, {0,-1}};
        LinkedList<int[]> list = new LinkedList<>();
        if(row < 0 || row >= board.length || col >= board[0].length || col <0 || board[row][col] == -1 || visited[row][col])
            return false;
        int[] cell = {row, col};
        list.add(cell);
        visited[row][col] = true;
        while( !list.isEmpty()){
            int[] visitedCell = list.pop();
            for(int[] move : moves){
                int nextRow = visitedCell[0] + move[0];
                int nextCol = visitedCell[1] + move[1];
                if(nextRow >= 0 && nextRow < board.length && nextCol < board[0].length && nextCol >=0 && board[nextRow][nextCol] != -1 && !visited[nextRow][nextCol]){
                    int[] nextCell = {nextRow, nextCol};
                    visited[nextRow][nextCol] = true;
                    list.add(nextCell);
                }
            }
        }
        for(int i = 0; i< board.length; i++){
            for(int j = 0; j< board[0].length; j++){
                if(board[i][j] == 0 && !visited[i][j] )
                    return false;

            }
        }
        return true;
    }
}
