//https://central.karat.io/questions/1506/guide#2-1
package com.karat.goldrush;

import java.util.LinkedList;
import java.util.Queue;

public class FindTheTreasure {

    public static void main(String[] args) {

        int[] instructions_2_1 = {1, 1, 1, 9};
        int[] instructions_2_2 = {1, 1, 2, 9};
        int[] instructions_2_3 = {2, 4, 1, 2, 10, 2, 3, 1, 9};

        System.out.println(findTreasure(instructions_2_1, 0)); // Output: 3
        System.out.println(findTreasure(instructions_2_1, 1)); // Output: 2

        System.out.println(findTreasure(instructions_2_2, 0)); // Output: -1
        System.out.println(findTreasure(instructions_2_2, 1)); // Output: 2
        System.out.println(findTreasure(instructions_2_2, 2)); // Output: 2

        System.out.println(findTreasure(instructions_2_3, 0)); // Output: 5
        System.out.println(findTreasure(instructions_2_3, 1)); // Output: 4
        System.out.println(findTreasure(instructions_2_3, 2)); // Output: 3
    }

    public static int findTreasure(int[] instructions, int money) {
        int n = instructions.length;

        // Create a 2D array to track visited states
        boolean[][] visited = new boolean[n][money + 1];

        // Create a queue for BFS
        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(0, money, 0)); // Start from room 0 with the given money

        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            int currentRoom = currentState.room;
            int currentMoney = currentState.money;
            int steps = currentState.steps;
            System.out.println("current state:"+currentState);
            // Check if we have reached the treasure room
            if (currentRoom == n - 1) {
                return steps;
            }

            // Move forward with the current instruction
            int nextRoom = currentRoom + instructions[currentRoom];
            if (nextRoom < n && !visited[nextRoom][currentMoney]) {
                visited[nextRoom][currentMoney] = true;
                queue.offer(new State(nextRoom, currentMoney, steps + 1));
            }

            // If we have money, try changing the instruction and move forward
            if (currentMoney > 0) {
                for (int change = 1; change <= currentMoney; change++) { // Allow changing by 2 units
                    int modifiedInstruction = instructions[currentRoom] + change;
                    nextRoom = currentRoom + modifiedInstruction;
                    int updatedMoney = currentMoney - change;
                    if (nextRoom < n && updatedMoney >= 0 && !visited[nextRoom][updatedMoney]) {
                        visited[nextRoom][updatedMoney] = true;
                        queue.offer(new State(nextRoom, updatedMoney, steps + 1));
                    }
                }
            }
        }

        // If we reach here, it's not possible to reach the treasure room
        return -1;
    }


    static class State {
        int room;
        int money;
        int steps;

        @Override
        public String toString() {
            return "State{" +
                    "room=" + room +
                    ", money=" + money +
                    ", steps=" + steps +
                    '}';
        }

        public State(int room, int money, int steps) {
            this.room = room;
            this.money = money;
            this.steps = steps;
        }
    }
}
