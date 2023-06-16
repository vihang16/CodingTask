//https://central.karat.io/questions/1169/guide#2-1
package com.karat.obstacleCourse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MountainCrossing {
    public static void main(String[] args) {
        int[] altitudes_1 = {0, 1, 2, 1};
        int[][] snow_1 = {
                {1, 0, 1, 0},
                {0, 0, 0, 0},
                {1, 1, 0, 2}
        };

        int[] altitudes_2 = {0, 0, 0, 0};
        int[][] snow_2 = {
                {2, 2, 2, 2},
                {1, 0, 0, 0},
                {1, 0, 0, 0}
        };

        int[] altitudes_3 = {0, 0, 0, 1};
        int[][] snow_3 = {
                {0, 0, 2, 0},
                {1, 1, 0, 0},
                {0, 0, 0, 0},
                {1, 1, 1, 0}
        };

        int[] altitudes_4 = {0, 1, 2, 0};
        int[][] snow_4 = {
                {1, 0, 0, 0},
                {0, 1, 0, 0}
        };

        int[] altitudes_5 = {0, 0, 0};
        int[][] snow_5 = {
                {5, 5, 0},
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        int[] altitudes_6 = {0, 0, 0, 0, 0};
        int[][] snow_6 = {
                {2, 1, 2, 1, 2}
        };
       System.out.println(Arrays.toString(bestDayToCross(altitudes_1, snow_1)));
       System.out.println(Arrays.toString(bestDayToCross(altitudes_2, snow_2)));
       System.out.println(Arrays.toString(bestDayToCross(altitudes_3, snow_3)));
       System.out.println(Arrays.toString(bestDayToCross(altitudes_4, snow_4)));
       System.out.println(Arrays.toString(bestDayToCross(altitudes_5, snow_5)));
       System.out.println(Arrays.toString(bestDayToCross(altitudes_6, snow_6)));
    }

    private static int[] bestDayToCross(int[] altitudes, int[][] snow) {
        int[] days = {-1,-1};
        int[][] altitudeCopy = new int[snow.length][altitudes.length];
        for(int i=0; i< snow.length; i++){
            altitudeCopy[i]= Arrays.copyOf(altitudes, altitudes.length);
        }
        Map<Integer, Integer> snowPositionMap = new HashMap<>();
        populateAltitudeCopy(altitudeCopy, snowPositionMap,altitudes, snow);

        int maxClimb = Integer.MAX_VALUE;
        int day = -1;

        for(int i=0; i< altitudeCopy.length; i++){
            int noOfClimbs = 0;
            boolean isValid = true;
            for(int j=1; j<altitudeCopy[i].length; j++){
                if(Math.abs(altitudeCopy[i][j] - altitudeCopy[i][j-1]) > 1){
                    isValid = false;
                    break;
                }
                noOfClimbs +=Math.abs(altitudeCopy[i][j] - altitudeCopy[i][j-1]);
            }
            if( isValid && noOfClimbs < maxClimb){
                day = i;
                maxClimb = noOfClimbs;
            }
            isValid = true;
        }
        days[0] = day;
        days[1] = maxClimb == Integer.MAX_VALUE? -1: maxClimb;
        return days;
    }

    private static void populateAltitudeCopy(int[][] altitudeCopy, Map<Integer, Integer> snowPositionMap, int[] altitudes, int[][] snow) {
        for(int i=0; i < snow.length; i++ ){
            for (int j=0; j < snow[i].length; j++){

                if(snow[i][j] > 0 ) {
                    snowPositionMap.put(j, snowPositionMap.getOrDefault(j, 0) + i);
                    if(i > 0)
                        altitudeCopy[i][j] = altitudeCopy[i-1][j] + snow[i][j];
                    else
                        altitudeCopy[i][j] += snow[i][j];
                }
                else if (snow[i][j] == 0 && snowPositionMap.containsKey(j) && i-2>= snowPositionMap.get(j)
                        &&  altitudeCopy[i-1][j] > altitudes[j]) {

                    altitudeCopy[i][j] = altitudeCopy[i-1][j] -1;
                }else if(i>0){
                    altitudeCopy[i][j] = altitudeCopy[i-1][j];
                }
            }
        }
    }
}