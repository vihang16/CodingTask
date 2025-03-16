package com.karat.library;

import java.util.ArrayList;
import java.util.List;

public class ReAllocateBook {
    public static void main(String[] args) {
        int[][] fees1 = {
                {0, 3, 5},
                {3, 0, 1},
                {5, 1, 0}
        };
        int[] current1 = {1, 3, 3};
        int[] needed1 = {4, 1, 2};

        int[][] fees2 = {
                {0, 3, 5},
                {3, 0, 7},
                {5, 7, 0}
        };
        int[] current2 = {1, 3, 3};
        int[] needed2 = {4, 1, 2};

        int[][] fees3 = {
                {0, 3, 3},
                {3, 0, 1},
                {3, 1, 0}
        };
        int[] current3 = {4, 3, 1};
        int[] needed3 = {0, 0, 8};

        int[][] fees4 = {
                {0, 1, 4},
                {1, 0, 3},
                {4, 3, 0}
        };
        int[] current4 = {2, 0, 2};
        int[] needed4 = {2, 0, 2};

        int[][] fees5 = {
                {0, 1, 5, 4},
                {1, 0, 1, 4},
                {5, 1, 0, 5},
                {4, 4, 5, 0}
        };
        int[] current5 = {3, 4, 1, 4};
        int[] needed5 = {4, 3, 3, 2};

        int[][] fees6 = {
                {0, 3, 2, 2},
                {3, 0, 2, 4},
                {2, 2, 0, 1},
                {2, 4, 1, 0}
        };
        int[] current6 = {4, 2, 0, 3};
        int[] needed6 = {2, 1, 2, 4};

        int[][] fees7 = {
                {0, 2, 4, 4},
                {2, 0, 1, 3},
                {4, 1, 0, 2},
                {4, 3, 2, 0}
        };
        int[] current7 = {1, 3, 0, 4};
        int[] needed7 = {3, 2, 1, 2};

        int[][] fees8 = {
                {0, 4, 1, 1},
                {4, 0, 1, 4},
                {1, 1, 0, 5},
                {1, 4, 5, 0}
        };
        int[] current8 = {1, 3, 0, 0};
        int[] needed8 = {0, 1, 1, 2};

        int[][] fees9 = {
                {0, 2, 3, 4},
                {2, 0, 3, 2},
                {3, 3, 0, 5},
                {4, 2, 5, 0}
        };
        int[] current9 = {4, 2, 2, 1};
        int[] needed9 = {2, 4, 3, 0};
        System.out.println(totalCost(current1, needed1, fees1));
    }

    private static int totalCost(int[] current, int[] needed, int[][] fees) {
        List<Transfer> transferList = gettingCost(fees);



        return 0;
    }

    private static List<Transfer> gettingCost(int[][] fees) {
        List<Transfer> transferList = new ArrayList<>();
        for(int i=0; i< fees.length; i++){
            for(int j=0; j< fees[i].length; j++) {
                Transfer t = new Transfer(i, j, fees[i][j]);
                transferList.add(t);
            }
        }
        return  transferList;
    }
}
class Transfer{
    int from;
    int to;
    int cost;

    public Transfer(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}