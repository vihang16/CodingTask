//https://central.karat.io/questions/1169/guide#1-1
package com.karat.obstacleCourse;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Mario {

    public static void main(String[] args) {
        int[] obstacles_1 = {4, 6};
        int[] obstacles_2 = {2, 9, 4};

        String instructions_1 = "RRRJJRRR";
        String instructions_2 = "RRRLJ";
        String instructions_3 = "RRRJJRRRL";
        String instructions_4 = "RRRLRJJRRR";
        String instructions_5 = "RRRRRRRRRR";
        String instructions_6 = "RRJJJJ";
        String instructions_7 = "RLRRRJJRRLLJJJLRRRJJRRR";
        String instructions_8 = "RRRJJRLJJJRRR";
        String instructions_9 = "R";
        String instructions_10 = "RJJRRRJ";
        System.out.println(level(obstacles_1,  instructions_1));
        System.out.println(level(obstacles_1,  instructions_2));
        System.out.println(level(obstacles_1,  instructions_3));
        System.out.println(level(obstacles_1,  instructions_4));
        System.out.println(level(obstacles_1,  instructions_5));
        System.out.println(level(obstacles_1,  instructions_6));
        System.out.println(level(obstacles_1,  instructions_7));
        System.out.println(level(obstacles_1,  instructions_8));
        System.out.println(level(obstacles_1,  instructions_9));
        System.out.println(level(obstacles_2,  instructions_10));
    }

    private static boolean level(int[] obstacles, String instructions) {
        int position = 0;
        Set<Integer> obstacleSet = new HashSet<>(Arrays.stream(obstacles).boxed().collect(Collectors.toSet()));
        char prevPosition ='1';
        for(char instruction : instructions.toCharArray()){
            switch (instruction){
                case  'R':
                    position +=1;
                    prevPosition = instruction;
                    if(position == 10)
                        return true;
                    if(obstacleSet.contains(position))
                        return false;
                    break;
                case 'L':
                    position -= 1;
                    prevPosition = instruction;
                    if(obstacleSet.contains(position))
                        return false;
                    break;
                case 'J':
                    if( prevPosition == 'R')
                        position +=2;
                    else if(prevPosition == 'L')
                        position -=2;
                    if(obstacleSet.contains(position))
                        return false;
            }

        }

        if(position < 10)
            return false;

        return true;

    }


}
