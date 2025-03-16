package com.karat.flippy;

public class OneDReversal {
    public static void main(String[] args) {
        char[] board1 = { 'X', 'O', 'O', 'O', ' ', ' ', 'O', 'O', 'X', 'O', 'X', 'X', 'O', ' ' };
        char[] board2 = { 'X', 'X', 'O', ' ', 'O', 'O', 'O', 'O', ' ', 'X', 'O', 'O', ' ' };
        char[] board3 = { ' ', 'O', 'X'};
        char[] board4 = { 'X', 'O' };
        char[] board5 = { 'X', 'O', ' ' };
        char[] board6 = { 'X', 'O', ' ', 'O', 'O', 'X', 'O', ' ', ' ' };
        char[] board7 = { 'X', 'O', 'O', ' ', 'O', 'O', 'O' };
        char[] board8 = { 'O', 'O', ' ', 'X' };
        char[] board9 = { 'X', 'O', ' ', 'X', 'O', ' ', 'O', 'X' };
        char[] board10 = { 'X', 'O', 'X', ' ' };
        System.out.println(oneDReversal(board1));
        System.out.println(oneDReversal(board2));
        System.out.println(oneDReversal(board3));
        System.out.println(oneDReversal(board4));
        System.out.println(oneDReversal(board5));
        System.out.println(oneDReversal(board6));
        System.out.println(oneDReversal(board7));
        System.out.println(oneDReversal(board8));
        System.out.println(oneDReversal(board9));
        System.out.println(oneDReversal(board10));
    }

    private static String oneDReversal(char[] board) {
        int xIndex = -1;
        int zeroIndex = -1;
        int space = -1;
        int max = 0;
        int resultIndex = -1;
        for(int  i=0; i<board.length; i++){
            if(board[i] == 'X'){
                xIndex = i;
                if(space != -1){
                     if(xIndex - space - 1 > max){
                         max = xIndex - space - 1;
                         resultIndex = space;
                     }
                     space = -1;
                }

            } else if (board[i] == ' ') {
                space = i;
                if(xIndex != -1){
                    if(space - xIndex -1 > max){
                        resultIndex = space;
                        max = space - xIndex -1;
                    }
                    xIndex = -1;
                }
            }
        }
        return resultIndex +" "+max;


    }
}
