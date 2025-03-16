/**
 * java code to find number path in matrix where matrix contains value 0 and 1 only.
 * 1 means you can pass that cell, 0 means you can not. you start from top left and need to reach bottom right.you can move only right and down, do it in recursive way
 */
import java.util.LinkedList;
import java.util.Queue;
public class NumberOfPathsInMatrix {

    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 1}, {1, 1, 1,}, {1, 1, 1}};
        NumberOfPathsInMatrix pathFinder = new NumberOfPathsInMatrix();
        //int numPaths = pathFinder.findPaths(matrix, 0, 0);
        int numPaths = pathFinder.allPathDFS(matrix, matrix.length, matrix[0].length );
      //  int numPaths = pathFinder.findPathsBFS(matrix, matrix.length, matrix[0].length );
        System.out.println("Number of paths: " + numPaths);
    }

    public int allPathDFS(int[][] matrix, int rows, int cols){
        boolean[][] visited = new boolean[rows][cols];
        int[] result = {0};

        return dfs(0,0, visited, matrix, rows, cols, result);
    }
    private int dfs(int row, int col, boolean[][] visited, int[][] matrix, int numRows, int numCols, int[] result) {
        if (row == numRows - 1 && col == numCols - 1) {
            // We have reached the bottom-right cell
            result[0] +=1;
            System.out.println("result:"+result[0]);
            return 1;
        }

        visited[row][col] = true;

        int numPaths = 0;

        // Check the neighbors of the current cell
        int[][] directions = {{1, 0}, {0, 1}};
        for (int[] dir : directions) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            if (nextRow >= 0 && nextRow < numRows && nextCol >= 0 && nextCol < numCols &&
                    matrix[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]) {
                numPaths += dfs(nextRow, nextCol, visited, matrix, numRows, numCols, result);
            }
        }

        visited[row][col] = false;

        return numPaths;
    }

    public int findPaths(int[][] matrix, int row, int col) {
        // Check if we have reached the bottom right corner of the matrix
        System.out.println("row:"+row +" cols:"+col);
        if (row == matrix.length - 1 && col == matrix[0].length - 1) {
            return 1;
        }



        // Check if we are still within the bounds of the matrix and can move down
        int downPaths = 0;
        if (row < matrix.length - 1 && matrix[row + 1][col] == 1) {
            downPaths = findPaths(matrix, row + 1, col);
        }

        // Check if we are still within the bounds of the matrix and can move right
        int rightPaths = 0;
        if (col < matrix[0].length - 1 && matrix[row][col + 1] == 1) {
            rightPaths = findPaths(matrix, row, col + 1);
        }

        // Return the total number of paths we found by moving down and/or right
        return downPaths + rightPaths;
    }

    public int findPathsBFS(int[][] matrix, int numRows, int numCols) {
        boolean[][] visited = new boolean[numRows][numCols];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        int numPaths = 0;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];

            if (row == numRows - 1 && col == numCols - 1) {
                // We have reached the bottom-right cell
                numPaths++;
                continue;
            }

            // Check the neighbors of the current cell
            int[][] directions = {{1, 0}, {0, 1}};
            for (int[] dir : directions) {
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];

                if (nextRow >= 0 && nextRow < numRows && nextCol >= 0 && nextCol < numCols &&
                        matrix[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]) {
                    queue.offer(new int[]{nextRow, nextCol});
                    visited[nextRow][nextCol] = true;
                }
            }
        }

        return numPaths;
    }
}

