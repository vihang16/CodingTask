//https://central.karat.io/questions/1506/guide#3-1

package com.karat.goldrush;

import java.util.*;

public class CatchMeIfYouCan {

    public static void main(String[] args) {
        int[][] board_0 = {
                {2, 0, 1, 2},
                {0, 1, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] board_1 = {
                {0, 1, 1},
                {0, 1, 1},
                {0, 0, 2},
                {0, 1, 1},
                {0, 1, 1},
        };

        int[][] board_2 = {
                {0, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 2},
                {0, 0, 2, 1, 1, 1, 0},
                {0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1},
        };

        int[][] board_3 = {
                {1, 1, 1, 1, 0, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 1, 0, 1},
                {1, 0, 1, 1, 1, 1, 1, 0, 1},
                {1, 2, 1, 1, 1, 1, 1, 2, 1},
                {1, 0, 1, 1, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 0, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 1, 1, 1, 1},
        };

        int[][] board_4 = {
                {0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        int[][] board_5 = {
                {1, 1, 1, 2, 0, 0, 1},
                {1, 1, 1, 0, 1, 0, 1},
                {1, 1, 1, 0, 1, 0, 1},
                {1, 1, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0, 0},
                {1, 1, 1, 0, 0, 0, 1},
        };
        System.out.println(get_treasure(board_0,0, 1, 2,1));
        System.out.println(get_treasure(board_0, 0,  1,  2,  0));//  => 2
        System.out.println(get_treasure(board_1, 0,  0,  4,  0));//  => -1
        System.out.println(get_treasure(board_2, 0,  0,  5,  1));//  => 8
        System.out.println(get_treasure(board_3, 0,  4,  9,  4));//  => 8
        System.out.println(get_treasure(board_4, 0,  0,  0,  10));// => -1
        System.out.println(get_treasure(board_4, 0,  0,  0,  9));//  => 5
        System.out.println(get_treasure(board_4, 0,  0,  0,  8));//  => 4
        System.out.println(get_treasure(board_5, 4,  0,  5,  5));//  => 7
        System.out.println(get_treasure(board_5, 4,  6,  5,  5));//  => 7
    }

    private static int get_treasure(int[][] board, int tomRow, int tomCol, int jerryRow, int jerryCol) {
        Queue<Coordinates> tomSteps = new LinkedList<>();
        int colLength = board[0].length;
        int rowLength =  board.length;
        //System.out.println("row len:"+rowLength +" col len:"+colLength);
        Map<Coordinates, Integer> tomMap = new HashMap<>();
        boolean[][] visited = new boolean[rowLength][colLength];
        createTomMap(visited, tomMap, tomSteps, tomRow, tomCol, board, colLength, rowLength);
        Queue<Coordinates> jerryStepQueue = new LinkedList<>();
        return createJerryMap(jerryStepQueue, jerryRow, jerryCol, board, colLength, rowLength, tomMap);
    }

    private static int createJerryMap(Queue<Coordinates> jerryStepQueue, int jerryRow, int jerryCol, int[][] board, int colLength, int rowLength, Map<Coordinates, Integer> tomMap) {
        Coordinates coordinates = new Coordinates(jerryRow, jerryCol);
        jerryStepQueue.add(coordinates);
        Map<Coordinates, Integer> jerryMap = new HashMap<>();
        jerryMap.put(coordinates, 0);

        while(!jerryStepQueue.isEmpty()){
            coordinates = jerryStepQueue.poll();
            int distance = jerryMap.get(coordinates);
            int existingRow = coordinates.row;
            int existingCol = coordinates.col;
            if((existingRow +1 < rowLength && board[existingRow +1][existingCol] == 2)
                    || (existingRow - 1 >= 0 && board[existingRow -1][existingCol] == 2)
                    || (existingCol - 1 >= 0 && board[existingRow][existingCol - 1] == 2)
                    || (existingCol + 1 < colLength && board[existingRow][existingCol+1] == 2)){
                return distance +1;
            }
            if(existingRow + 1 < rowLength && board[existingRow +1][existingCol] != 1
                && tomMap.get(new Coordinates(existingRow +1, existingCol)) > distance +1
                    && !jerryMap.containsKey(new Coordinates(existingRow + 1, existingCol))){
                Coordinates newCoordinates = new Coordinates(existingRow+1, existingCol);
                jerryMap.put(newCoordinates, distance+1);
                jerryStepQueue.add(newCoordinates);
            }
            if(existingRow - 1 >= 0 && board[existingRow -1][existingCol] != 1
                    && tomMap.get(new Coordinates(existingRow -1, existingCol)) > distance +1
                    && !jerryMap.containsKey(new Coordinates(existingRow - 1, existingCol))){
                Coordinates newCoordinates = new Coordinates(existingRow-1, existingCol);
                jerryMap.put(newCoordinates, distance+1);
                jerryStepQueue.add(newCoordinates);
            }
            if(existingCol + 1 < colLength && board[existingRow][existingCol+1] != 1
                    && tomMap.get(new Coordinates(existingRow, existingCol+1)) > distance +1
                    && !jerryMap.containsKey(new Coordinates(existingRow, existingCol+1))){
                Coordinates newCoordinates = new Coordinates(existingRow, existingCol+1);
                jerryMap.put(newCoordinates, distance+1);
                jerryStepQueue.add(newCoordinates);
            }
            if(existingCol - 1 >= 0 && board[existingRow][existingCol - 1] != 1
                    && tomMap.get(new Coordinates(existingRow, existingCol - 1)) > distance + 1
                    && !jerryMap.containsKey(new Coordinates(existingRow , existingCol - 1))){
                Coordinates newCoordinates = new Coordinates(existingRow, existingCol - 1);
                jerryMap.put(newCoordinates, distance+1);
                jerryStepQueue.add(newCoordinates);
            }
        }
        return -1;
    }

    private static void createTomMap(boolean[][] visited, Map<Coordinates, Integer> tomMap, Queue<Coordinates> tomSteps, int tomRow, int tomCol, int[][] board, int colLength, int rowLength) {
        tomMap.put(new Coordinates(tomRow, tomCol), 0);
        tomSteps.offer(new Coordinates(tomRow, tomCol));
        while(!tomSteps.isEmpty()){
            Coordinates coordinates = tomSteps.poll();
            int distance = tomMap.get(coordinates);
            visited[coordinates.row][coordinates.col] = true;
            if(coordinates.row + 1 < rowLength && board[coordinates.row + 1][coordinates.col] != 1 && !visited[coordinates.row+1][coordinates.col]){
                addToTomPath(tomMap, tomSteps, coordinates.row + 1, coordinates.col, visited, distance);
            }
            if(coordinates.row - 1 >= 0 && board[coordinates.row - 1][coordinates.col] != 1 && !visited[coordinates.row - 1][coordinates.col]){
                addToTomPath(tomMap, tomSteps, coordinates.row - 1, coordinates.col, visited, distance);
            }
            if(coordinates.col + 1 < colLength && board[coordinates.row][coordinates.col+1] != 1 && !visited[coordinates.row][coordinates.col + 1]){
                addToTomPath(tomMap, tomSteps, coordinates.row, coordinates.col + 1, visited, distance);
            }
            if( coordinates.col -1 >=0 && board[coordinates.row][coordinates.col-1] != 1 && !visited[coordinates.row][coordinates.col - 1]){
                addToTomPath(tomMap, tomSteps, coordinates.row, coordinates.col - 1, visited, distance);
            }
        }

    }

    private static void addToTomPath(Map<Coordinates, Integer> tomMap, Queue<Coordinates> tomSteps, int row, int col, boolean[][] visited, int distance) {
        Coordinates newCoordinates = new Coordinates(row, col);
        tomSteps.offer(newCoordinates);
        visited[newCoordinates.row][newCoordinates.col] = true;
        tomMap.put(newCoordinates, distance+1);
    }

    static class Coordinates{
        int row;
        int col;

        public Coordinates(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Coordinates{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinates that = (Coordinates) o;
            return row == that.row && col == that.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}
