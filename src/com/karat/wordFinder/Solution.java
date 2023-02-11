package com.karat.wordFinder;

/*
You are running a classroom and suspect that some of your students are passing around the answer to a multiple-choice question in 2D grids of letters. The word may start anywhere in the grid, and consecutive letters can be either immediately below or immediately to the right of the previous letter.

Given a grid and a word, write a function that returns the location of the word in the grid as a list of coordinates. If there are multiple matches, return any one.

grid1 = [
    ['b', 'b', 'b', 'a', 'l', 'l', 'o', 'o'],
    ['b', 'a', 'c', 'c', 'e', 's', 'c', 'n'],
    ['a', 'l', 't', 'e', 'w', 'c', 'e', 'w'],
    ['a', 'l', 'o', 's', 's', 'e', 'c', 'c'],
    ['w', 'o', 'o', 'w', 'a', 'c', 'a', 'w'],
    ['i', 'b', 'w', 'o', 'w', 'w', 'o', 'w']
]
word1_1 = "access"      # [(1, 1), (1, 2), (1, 3), (2, 3), (3, 3), (3, 4)]
word1_2 = "balloon"     # [(0, 2), (0, 3), (0, 4), (0, 5), (0, 6), (0, 7), (1, 7)]

word1_3 = "wow"         # [(4, 3), (5, 3), (5, 4)] OR
                        # [(5, 2), (5, 3), (5, 4)] OR
                        # [(5, 5), (5, 6), (5, 7)]

word1_4 = "sec"         # [(3, 4), (3, 5), (3, 6)] OR
                        # [(3, 4), (3, 5), (4, 5)]

word1_5 = "bbaal"       # [(0, 0), (1, 0), (2, 0), (3, 0), (3, 1)]


grid2 = [
  ['a'],
]
word2_1 = "a"

grid3 = [
    ['c', 'a'],
    ['t', 't'],
    ['h', 'a'],
    ['a', 'c'],
    ['t', 'g']
]
word3_1 = "cat"
word3_2 = "hat"

grid4 = [
    ['c', 'c', 'x', 't', 'i', 'b'],
    ['c', 'a', 't', 'n', 'i', 'i'],
    ['a', 'x', 'n', 'x', 'p', 't'],
    ['t', 'x', 'i', 'x', 't', 't']
]
word4_1 = "catnip"      # [(1, 0), (1, 1), (1, 2), (1, 3), (1, 4), (2, 4)] OR
                        # [(0, 1), (1, 1), (1, 2), (1, 3), (1, 4), (2, 4)]


All test cases:

search(grid1, word1_1) => [(1, 1), (1, 2), (1, 3), (2, 3), (3, 3), (3, 4)]
search(grid1, word1_2) => [(0, 2), (0, 3), (0, 4), (0, 5), (0, 6), (0, 7), (1, 7)]
search(grid1, word1_3) => [(4, 3), (5, 3), (5, 4)] OR
                          [(5, 2), (5, 3), (5, 4)] OR
                          [(5, 5), (5, 6), (5, 7)]
search(grid1, word1_4) => [(3, 4), (3, 5), (3, 6)] OR
                          [(3, 4), (3, 5), (4, 5)]
search(grid1, word1_5) => [(0, 0), (1, 0), (2, 0), (3, 0), (3, 1)]

search(grid2, word2_1) => [(0, 0)]

search(grid3, word3_1) => [(0, 0), (0, 1), (1, 1)]
search(grid3, word3_2) => [(2, 0), (3, 0), (4, 0)]

search(grid4, word4_1) => [(1, 0), (1, 1), (1, 2), (1, 3), (1, 4), (2, 4)] OR
                          [(0, 1), (1, 1), (1, 2), (1, 3), (1, 4), (2, 4)]

Complexity analysis variables:

r = number of rows
c = number of columns
w = length of the word

*/

import java.io.*;
import java.util.*;

public class Solution {



    // Q2
    private static List<List<Integer>> pathQueue;
    //O(r*c*w) O(r*c)
    private static List<List<Integer>> solution2(char[][] graph, String word) {
        char[] charList = word.toCharArray();
        pathQueue = new LinkedList<>();

        for (int x = 0; x < graph.length; x++) {
            for (int y = 0; y < graph[0].length; y++) {
                if (dfsCheck(graph, x, y, charList, 0)) {
                    return pathQueue;
                }
            }
        }
        return new LinkedList<List<Integer>>();
    }

    private static boolean dfsCheck(char[][] graph, int x, int y, char[] charList, int index) {
        if (index == charList.length) {
            return true;
        }
        if (x >= graph.length || y >= graph[0].length || graph[x][y] != charList[index]) {
            return false;
        }
        pathQueue.add(Arrays.asList(x, y));

        boolean check = dfsCheck(graph, x + 1, y, charList, index + 1) ||
                dfsCheck(graph, x, y + 1, charList, index + 1);

        if (!check) {
            pathQueue.remove(Arrays.asList(x, y));
        }
        return check;
    }


    private static void printQueue(List<List<Integer>> input) {
        for (List<Integer> item : input) {
            System.out.printf("(" + item.get(0) + ", " + item.get(1) + ")");
        }
        System.out.println();
    }

    public static void main(String[] argv) {
        char[][] grid1 = {
                {'b', 'b', 'b', 'a', 'l', 'l', 'o', 'o'},
                {'b', 'a', 'c', 'c', 'e', 's', 'c', 'n'},
                {'a', 'l', 't', 'e', 'w', 'c', 'e', 'w'},
                {'a', 'l', 'o', 's', 's', 'e', 'c', 'c'},
                {'w', 'o', 'o', 'w', 'a', 'c', 'a', 'w'},
                {'i', 'b', 'w', 'o', 'w', 'w', 'o', 'w'},
        };
        String word1_1 = "access";
        String word1_2 = "balloon";
        String word1_3 = "wow";
        String word1_4 = "sec";
        String word1_5 = "bbaal";

        char[][] grid2 = {
                {'a'},
        };
        String word2_1 = "a";

        char[][] grid3 = {
                {'c', 'a'},
                {'t', 't'},
                {'h', 'a'},
                {'a', 'c'},
                {'t', 'g'},
        };
        String word3_1 = "cat";
        String word3_2 = "hat";

        char[][] grid4 = {
                {'c', 'c', 'x', 't', 'i', 'b'},
                {'c', 'a', 't', 'n', 'i', 'i'},
                {'a', 'x', 'n', 'x', 'p', 't'},
                {'t', 'x', 'i', 'x', 't', 't'},
        };
        String word4_1 = "catnip";

        printQueue(solution2(grid1, word1_1));
        printQueue(solution2(grid1, word1_2));
        printQueue(solution2(grid1, word1_3));
        printQueue(solution2(grid1, word1_4));
        printQueue(solution2(grid1, word1_5));

        printQueue(solution2(grid2, word2_1));

        printQueue(solution2(grid3, word3_1));
        printQueue(solution2(grid3, word3_2));

        printQueue(solution2(grid4, word4_1));
    }
}

