package com.karat.accuraterules;

public class ScreenSaver {
    public static void main(String[] args) {
        int[] size_1 = {6, 18};
        int[] pos_1_1 = {3, 1};
        int[] pos_1_2 = {4, 1};
        int[] pos_1_3 = {4, 1};

        int[] size_2 = {10, 12};
        int[] pos_2_1 = {0, 0};
        int[] pos_2_2 = {7, 9};
        int[] pos_2_3 = {6, 8};
        int[] pos_2_4 = {2, 0};

        int[] size_3 = {3, 3};
        int[] pos_3_1 = {0, 0};
        int[] pos_3_2 = {0, 1};
        int[] pos_3_3 = {1, 1};

        int[] size_4 = {3, 10};
        int[] pos_4_1 = {1, 1};
        int[] pos_4_2 = {1, 0};

        int[] size_5 = {6, 6};
        int[] pos_5_1 = {1, 1};
        int[] pos_5_2 = {1, 1};

        int[] size_6 = {50, 51};
        int[] pos_6_1 = {0, 1};
        System.out.println(screensaver(size_1[0], size_1[1], pos_1_1[0], pos_1_1[1], "up-right"));
    }

    public static int screensaver(int rows, int cols, int startX, int startY, String direction) {
        // Direction: up-right, up-left, down-right, down-left
        // Directions mapped to (dx, dy)
        int[][] directions = {
                {-1, 1},  // up-right
                {-1, -1}, // up-left
                {1, 1},   // down-right
                {1, -1}   // down-left
        };

        // Direction mapping to index
        int dirIndex = -1;
        if (direction.equals("up-right")) dirIndex = 0;
        else if (direction.equals("up-left")) dirIndex = 1;
        else if (direction.equals("down-right")) dirIndex = 2;
        else if (direction.equals("down-left")) dirIndex = 3;

        // Current position of the top-left corner of the square
        int x = startX;
        int y = startY;

        // Steps counter
        int steps = 0;

        // A set to track visited positions to detect an infinite loop
        boolean[][] visited = new boolean[rows][cols];

        // Directions for moving
        int dx = directions[dirIndex][0];
        int dy = directions[dirIndex][1];

        // Loop until we reach a corner or we detect an infinite loop
        while (true) {
            // Check if current position is a corner
            if ((x == 0 && y == 0) || (x == 0 && y == cols - 1) || (x == rows - 1 && y == 0) || (x == rows - 1 && y == cols - 1)) {
                return steps;
            }

            // If we've visited this position before, return -1 to avoid infinite loop
            if (visited[x][y]) {
                return -1;
            }

            // Mark the current position as visited
            visited[x][y] = true;

            // Move the square in the current direction
            x += dx;
            y += dy;

            // Bounce off the walls if needed
            if (x < 0 || x >= rows) {
                dx = -dx;  // Reverse vertical direction
                x += dx;
            }
            if (y < 0 || y >= cols) {
                dy = -dy;  // Reverse horizontal direction
                y += dy;
            }

            // Increment the step counter
            steps++;
        }
    }
}
