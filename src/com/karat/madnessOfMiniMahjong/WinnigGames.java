//https://central.karat.io/questions/446/guide#2-1
package com.karat.madnessOfMiniMahjong;

public class WinnigGames {
    public static void main(String[] args) {
        int[][] grid1 = {{4, 4, 4, 4},
                {5, 5, 5, 4},
                {2, 5, 7, 5}};
        int[][] grid2 = {{0, 3, 3, 3, 3, 3, 3},
                {0, 1, 1, 1, 1, 1, 3},
                {0, 2, 2, 0, 2, 1, 4},
                {0, 1, 2, 2, 2, 1, 3},
                {0, 1, 1, 1, 1, 1, 3},
                {0, 0, 0, 0, 0, 0, 0}};
        int[][] grid3 = {{0}};
        int[][] grid4 = {{1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}};
        System.out.println(disappear(grid1, 0, 0));
        System.out.println(disappear(grid1, 1, 1));
        System.out.println(disappear(grid1, 1, 0));
        System.out.println(disappear(grid2, 0, 0));
        System.out.println(disappear(grid2, 3, 0));
        System.out.println(disappear(grid2, 1, 1));
        System.out.println(disappear(grid2, 2, 2));
        System.out.println(disappear(grid2, 0, 3));
        System.out.println(disappear(grid3, 0, 0));
        System.out.println(disappear(grid4, 0, 0));
    }

    private static int disappear(int[][] grid, int i, int j) {
        int val = grid[i][j];
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int[] totalCount = {0};

        dfs(visited, i,j,grid, totalCount, val );
        return totalCount[0];
    }

    private static void dfs(boolean[][] visited, int i, int j, int[][] grid, int[] totalCount, int val) {
        if( i >= grid.length || i < 0 || j <0 || j >= grid[0].length )
            return;
        if(  grid[i][j] != val || visited[i][j]  )
            return;
        totalCount[0] += 1;

        visited[i][j] = true;
        dfs(visited, i+1, j, grid, totalCount, val);
        dfs(visited, i-1, j, grid, totalCount, val);
        dfs(visited, i, j+1, grid, totalCount, val);
        dfs(visited, i, j-1, grid, totalCount, val);
    }
}
