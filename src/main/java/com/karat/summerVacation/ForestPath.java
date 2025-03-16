package com.karat.summerVacation;

import java.util.*;

public class ForestPath {
    static List<int[]> pathList = new ArrayList<>();

    public static void main(String[] args) {
        String[][] forest1 = {
                {"B", "B", "O", "R", "G"},
                {"G", "B", "O", "G", "B"},
                {"O", "R", "Y", "R", "G"},
                {"B", "Y", "G", "R", "R"}
        };
        String[][] forest2 = {
                {"G", "Y", "O", "O", "R", "B"},
                {"B", "B", "B", "G", "G", "G"}
        };
        String[][] forest3 = {
                {"G", "Y", "O", "R", "B"},
                {"B", "B", "B", "G", "G"}
        };
        String[][] forest4 = {
                {"G", "G", "G", "G", "G"},
                {"G", "Y", "Y", "Y", "G"},
                {"G", "Y", "O", "Y", "G"},
                {"G", "Y", "R", "Y", "G"},
                {"G", "G", "B", "G", "G"}
        };
        String[][] forest5 = {
                {"B", "R", "O", "B", "O"},
                {"Y", "R", "G", "Y", "B"},
                {"Y", "G", "B", "Y", "Y"},
                {"Y", "R", "R", "G", "O"},
                {"R", "Y", "O", "Y", "O"}
        };
        String[][] forest6 = {
                {"R", "Y", "O"},
                {"O", "Y", "Y"},
                {"Y", "Y", "Y"},
                {"R", "R", "R"},
                {"R", "B", "G"},
                {"G", "Y", "O"}
        };
        String[][] forest7 = {
                {"B", "G", "G", "B", "O"},
                {"G", "G", "G", "Y", "B"},
                {"Y", "G", "B", "Y", "Y"},
                {"R", "R", "R", "Y", "O"},
                {"R", "O", "B", "Y", "O"}
        };
        System.out.println(longestWalk(forest1));
        System.out.println(longestWalk(forest2));
        System.out.println(longestWalk(forest3));
        System.out.println(longestWalk(forest4));
        System.out.println(longestWalk(forest5));
        System.out.println(longestWalk(forest6));
        System.out.println(longestWalk(forest7));
    }

    public static int longestWalk(String[][] forests) {
        pathList = new ArrayList<>();

        String[] sequence = { "G", "Y", "O", "R", "B"};
        List<int[]> greenCoordinates = new ArrayList<>();
        boolean[][] visited;
        for(int i = 0; i< forests.length; i++){
            for(int j=0; j<forests[0].length; j++){
                if(forests[i][j].equals("G")){
                    int[] coordinate = {i, j};
                    greenCoordinates.add(coordinate);
                }

            }
        }
        for(int[] coordinate : greenCoordinates){
            visited =  new boolean[forests.length][forests[0].length];
            dfsForest(visited, sequence,new int[]{0}, forests, coordinate[0], coordinate[1], new ArrayList<>());
        }


        return pathList.size();
    }

    private static void dfsForest(boolean[][] visited, String[] sequence, int[] sequenceIndex, String[][] forests, int row, int col, List<int[]> existingSize) {

        if( row < 0 || row >= forests.length || col < 0 || col >= forests[0].length || visited[row][col]) {
            if(sequenceIndex[0] == sequence.length - 1 && pathList.size() < existingSize.size() ){
                pathList = new ArrayList<>(existingSize);
                //System.out.println("existing size:"+existingSize.size());
            }
            return;
        }
        if(sequenceIndex[0] == sequence.length - 1 && !forests[row][col].equals(sequence[sequence.length - 1]) && pathList.size() < existingSize.size() ){
                pathList = new ArrayList<>(existingSize);
                //System.out.println("existing size:"+existingSize.size());
                return;
        }
        if(sequenceIndex[0]+1 < sequence.length && forests[row][col].equals(sequence[sequenceIndex[0]+1])){
            sequenceIndex[0] += 1;
            existingSize.add(new int[]{row, col});
            visited[row][col] = true;
            callSubsequenst( visited, sequence, sequenceIndex, forests, row, col, existingSize);
            return;
        }
        if(sequenceIndex[0] < sequence.length && forests[row][col].equals(sequence[sequenceIndex[0]])){
            visited[row][col] = true;
            existingSize.add(new int[]{row, col});
           /* System.out.println("current path so far");
            for(int[] path: existingSize){

                System.out.print(Arrays.toString(path) +" ");
                System.out.println();
            }*/
            callSubsequenst( visited, sequence, sequenceIndex, forests, row, col, existingSize);
        }
    }
    static void callSubsequenst(boolean[][] visited, String[] sequence, int[] sequenceIndex, String[][] forests, int row, int col, List<int[]> existingSize){
        int[][] directions = { {0,1}, {1,0}, {0, -1}, {-1, 0}, {-1,-1}, {-1,1},{1,1}, {1,-1}};
        for(int i=0; i< directions.length; i++){
            dfsForest(visited, sequence, sequenceIndex, forests, row + directions[i][0], col + directions[i][1], existingSize);
        }
        visited[row][col] = false;
        if(existingSize.size() >=2){
            int existingRow = existingSize.get(existingSize.size() -1)[0];
            int existingCol = existingSize.get(existingSize.size() -1)[1];
            int pastCol = existingSize.get(existingSize.size() -2)[1];
            int pastRow = existingSize.get(existingSize.size() -2)[0];
            if(!forests[existingRow][existingCol].equals(forests[pastRow][pastCol])){
                sequenceIndex[0] -=1;
            }
        }
        if(existingSize.size() >=1)
            existingSize.remove(existingSize.size() - 1);

    }
}
