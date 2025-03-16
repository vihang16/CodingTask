//https://central.karat.io/questions/104/guide#1-1
package com.karat.teasureMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindLegalMoves {

    public static void main(String[] args) {
        int[][] board = new int[][] {
                {0,  0,  0, -1, -1},
                {0,  0, -1,  0,  0},
                {0, -1,  0, -1,  0},
                {0,  0, -1,  0,  0},
                {0,  0,  0,  0,  0},
                {0,  0,  0,  0,  0},
                {0,  0,  0,  0,  0},
        };

        int[] start1 = new int[] {1, 1};
        int[] start2 = new int[] {5, 3};
        int[] start3 = new int[] {5, 1};
        int[] start4 = new int[] {6, 0};
        int[] start5 = new int[] {6, 4};
        int[] start6 = new int[] {0, 0};
        int[] start7 = new int[] {2, 2};
        List<int[]> ans = null;

        ans = findLegalMoves(board, start1);
        printList(ans);
        ans = findLegalMoves(board, start2);
        printList(ans);
        ans = findLegalMoves(board, start3);
        printList(ans);
        ans = findLegalMoves(board, start4);
        printList(ans);
        ans = findLegalMoves(board, start5);
        printList(ans);
        ans = findLegalMoves(board, start6);
        printList(ans);
        ans = findLegalMoves(board, start7);
        printList(ans);
        //System.out.println();
    }

    private static void printList(List<int[]> ans) {
        System.out.println("--------------");
        for(int[] a : ans)
            System.out.println(Arrays.toString(a));
    }

    private static List<int[]> findLegalMoves(int[][] board, int[] start) {
        int[][] moves = { {1,0}, { -1, 0}, {0,1}, {0,-1}};
        List<int[]> legalMoves = new ArrayList<>();
        for(int[] move : moves){
            int row =  move[0];
            int col = move[1];
            int nextRow = row + start[0];
            int nextCol = col + start[1];
            if(nextRow >= 0 && nextRow < board.length && nextCol >=0 && nextCol < board[0].length && board[nextRow][nextCol] != -1)
                legalMoves.add(new int[]{nextRow, nextCol});
        }
        return legalMoves;
    }
}
