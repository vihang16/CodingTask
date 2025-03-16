//https://central.karat.io/questions/297/guide#2-1
package com.karat.riddler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        System.out.println(validateNonogram(matrix1, rows1_2, columns1_2));
        System.out.println(validateNonogram(matrix1, rows1_3, columns1_3));
        System.out.println(validateNonogram(matrix1, rows1_4, columns1_4));
        System.out.println(validateNonogram(matrix1, rows1_5, columns1_5));
        System.out.println(validateNonogram(matrix1, rows1_7, columns1_7));
        System.out.println(validateNonogram(matrix1, rows1_6, columns1_6));
        System.out.println(validateNonogram(matrix1, rows1_8, columns1_8));
        System.out.println(validateNonogram(matrix2, rows2_1, columns2_1));
        System.out.println(validateNonogram(matrix2, rows2_2, columns2_2));
        System.out.println(validateNonogram(matrix2, rows2_3, columns2_3));
        System.out.println(validateNonogram(matrix2, rows2_4, columns2_4));
        System.out.println(validateNonogram(matrix2, rows2_5, columns2_5));
        System.out.println(validateNonogram(matrix2, rows2_6, columns2_6));
        System.out.println(validateNonogram(matrix3, rows3_1, columns3_2));
        System.out.println(validateNonogram(matrix2, rows3_2, columns3_2));
    }

    private static boolean validateNonogram(char[][] matrix, int[][] rows, int[][] columns) {

        boolean result  = true;
        for(int i=0; i< matrix.length; i++){
            char[] currentRow = matrix[i];
            int[] currentRowCondition = rows[i];

            if(currentRowCondition.length == 0){
                for(char c:  currentRow){
                    if(c == 'B'){
                        //System.out.println("failing at:"+Arrays.toString(currentRowCondition)+" and current row:"+Arrays.toString(currentRow));
                        return false;
                    }
                }
            }else if(currentRowCondition.length == 1){
                int value = currentRowCondition[0];
                int count =0;
                boolean matchedFound = false;
                while(count < currentRow.length){
                    if(currentRow[count] == 'B' && !matchedFound){
                        String subString =  new String(Arrays.copyOfRange(currentRow, count, count+value));
                        if(!subString.equals("B".repeat(value))){
                            //System.out.println("failing at:"+Arrays.toString(currentRowCondition)+" and current row:"+Arrays.toString(currentRow));
                            return false;
                        }
                        matchedFound= true;
                        //System.out.println("matchefound:"+ matchedFound+" and count:"+count+" value:"+value+" row condition:"+Arrays.toString(currentRow));
                        count+=value;

                    }else if(currentRow[count] == 'B' && matchedFound){
                        //System.out.println("failing at:"+Arrays.toString(currentRowCondition)+" and current row:"+Arrays.toString(currentRow));
                        return false;
                    }else{
                        count++;
                    }

                }
                if(!matchedFound){
                    return false;
                }

            }else{
                //System.out.println("current row condition:"+Arrays.toString(currentRowCondition));
                int count =0;
                for(int k=0; k< currentRowCondition.length && count<currentRow.length;k++){
                    int value = currentRowCondition[k];
                    boolean matchFound = false;
                    //System.out.println("condition:"+Arrays.toString(currentRowCondition)+" and row:"+currentRow+" and count:"+count);
                    while(count< currentRow.length){
                        if(currentRow[count] == 'B'){
                            String subString =  new String(Arrays.copyOfRange(currentRow, count, count+value));
                            count+= value;
                            //System.out.println("match found for condition:"+Arrays.toString(currentRowCondition)+" and row:"+currentRow+" and count:"+count);
                            if( !subString.equals("B".repeat(value))){
                                //System.out.println("failing at:"+Arrays.toString(currentRowCondition)+" and current row:"+Arrays.toString(currentRow));
                                return false;
                            }if(count < currentRow.length && currentRow[count] == 'B'){
                                //System.out.println("failing for subsequent check at:"+Arrays.toString(currentRowCondition)+" and current row:"+Arrays.toString(currentRow));
                                return false;
                            }

                            matchFound =true;
                            //System.out.println("match found:"+matchFound+" row conditon:"+Arrays.toString(currentRowCondition)+" current row:"+currentRow);
                            break;

                        }
                        count++;
                    }
                    if(!matchFound){
                        return false;
                    }
                }
            }
        }

        List<List<Character>> listOfColumns = getAllcolumns(matrix);
        if(!validateAllColumns(listOfColumns, columns))
            return false;

        return  true;
    }

    private static boolean validateAllColumns(List<List<Character>> listOfColumns, int[][] columns) {
        for(int i=0; i< listOfColumns.size(); i++){
            var currentColumn = listOfColumns.get(i);
            int[] currentColumnCondition = columns[i];
            if(currentColumnCondition.length == 0){
                for(char c:  currentColumn){
                    if(c == 'B'){
                        //System.out.println("failing at:"+Arrays.toString(currentColumnCondition)+" and current column:"+currentColumn);
                        return false;
                    }
                }
            }else if(currentColumnCondition.length == 1){
                int value = currentColumnCondition[0];
                int count =0;
                boolean matchedFound = false;
                while(count < currentColumn.size()){
                    if(currentColumn.get(count) == 'B'){
                        //System.out.println("count:"+count+" and value:"+value);
                        var subList= currentColumn.subList(count, count+value);
                        StringBuilder sb = new StringBuilder();
                        for(char c: subList){
                            sb.append(c);
                        }
                        String subString =  sb.toString();
                        //System.out.println("substring:"+subString + " value:"+value+" repeats:"+"B".repeat(value));
                        if(!subString.equals("B".repeat(value))){
                            //System.out.println("failing at:"+Arrays.toString(currentColumnCondition)+" and current column:"+currentColumn);
                            return false;
                        }
                        matchedFound= true;
                        count+=value;
                    }else if(currentColumn.get(count) == 'B' && matchedFound){
                        //System.out.println("failing at:"+Arrays.toString(currentRowCondition)+" and current row:"+Arrays.toString(currentRow));
                        return false;
                    }else{
                        count++;
                    }

                }
                if(!matchedFound){
                    return false;
                }
            }else{
                int count =0;
                for(int k=0; k< currentColumnCondition.length;k++){
                    int value = currentColumnCondition[k];
                    while(count< currentColumn.size()){
                        if(currentColumn.get(count) == 'B'){
                            String subString =  String.valueOf(currentColumn.subList(count, count+value));
                            count+= value;
                            //System.out.println("substring:"+subString +" current value:"+value+" current column:"+currentColumn);
                            if(!subString.equals("B".repeat(value))){
                                //System.out.println("failing at:"+Arrays.toString(currentColumnCondition)+" and current column:"+currentColumn);
                                return false;
                            }if(currentColumn.get(count) == 'B'){
                                //System.out.println("failing at:"+Arrays.toString(currentColumnCondition)+" and current column:"+currentColumn);
                                return false;
                            }
                            break;

                        }
                        count++;
                    }
                }
            }
        }
        return true;
    }

    private static List<List<Character>> getAllcolumns(char[][] matrix) {
        List<List<Character>> allColumns = new ArrayList<>();
        for(int i=0;i<matrix[0].length;i++){
            List<Character> column = new ArrayList<>();
            for(int j=0;j<matrix.length; j++){
                //System.out.println(matrix[j][i]);
                column.add(matrix[j][i]);
            }
            //System.out.println(column);
            allColumns.add(column);
        }
        //System.out.print(allColumns);
        return allColumns;
    }
}
