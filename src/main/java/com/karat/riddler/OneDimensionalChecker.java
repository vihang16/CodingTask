package com.karat.riddler;

import java.util.List;

/**
 Suppose you have a one-dimensional board of two colors of tiles. Red tiles can only move to the right, black tiles can only move to the left. A tile can move 1 space at a time. Either they move to an adjacent empty space, or they can jump over a single tile of the other color to an empty space.

 Eg:
 red = R
 black = B
 empty = _

 R _ B _ => _ R B _ or
 R B _ _

 R B _ _ => _ B R _

 Given a start and end configuration represented as a list of strings, return a list of valid moves to get from start to end (doesn't need to be shortest), or None if none exist. Include the start and end states in the list of moves.

 Example #1:
 start_1 = [R, _, B, B]
 end_1 = [B, _, B, R]
 -> [
 [R, _, B, B],
 [_, R, B, B],
 [B, R, _, B],
 [B, R, B, _],
 [B, _, B, R]
 ]

 Example #2:
 start_2 = [R, R, _, _]
 end_2   = [_, _, R, R]
 -> [
 [R, R, _, _],         [R, R, _, _],
 [R, _, R, _],         [R, _, R, _],
 [_, R, R, _],   or    [R, _, _, R],
 [_, R, _, R],         [_, R, _, R],
 [_, _, R, R]          [_, _, R, R]
 ]

 Example #3:
 start_3 = [R, B, _]
 end_3   = [B, R, _]
 -> None

 Example #4:
 start_4 = [_, B, B]
 end_4   = [B, B, _]
 ->  [_, B, B],
 [B, _, B],
 [B, B, _]

 Example #5:
 start_5 = [R, R, B]
 end_5   = [B, R, _]
 -> None

 Example #6:
 start_6 = [_, R, _]
 end_6   = [B, R, _ ]
 -> None

 Example #7:
 start_7 = [B, _, R]
 end_7   = [R, _, B]
 -> None

 Example #8:
 start_8 = [_, R, R, B]
 end_8   = [B, R, R, _]
 -> None



 All Test Cases:
 validMoves(start_1, end_1)
 validMoves(start_2, end_2)
 validMoves(start_3, end_3)
 validMoves(start_4, end_4)
 validMoves(start_5, end_5)
 validMoves(start_6, end_6)
 validMoves(start_7, end_7)
 validMoves(start_8, end_8)

 n: length of the board
 */
public class OneDimensionalChecker {
    public static void main(String[] args) {
        String[] start_1 = {"R", "_", "B", "B"};
        String[] end_1 = {"B", "_", "B", "R"};

        String[] start_2 = {"R", "R", "_", "_"};
        String[] end_2 = {"_", "_", "R", "R"};

        String[] start_3 = {"R", "B", "_"};
        String[] end_3 = {"B", "R", "_"};

        String[] start_4 = {"_", "B", "B"};
        String[] end_4 = {"B", "B", "_"};

        String[] start_5 = {"R", "R", "B"};
        String[] end_5 = {"B", "R", "_"};

        String[] start_6 = {"_", "R", "_"};
        String[] end_6 = {"B", "R", "_"};

        String[] start_7 = {"B", "_", "R"};
        String[] end_7 = {"R", "_", "B"};

        String[] start_8 = {"_", "R", "R", "B"};
        String[] end_8 = {"B", "R", "R", "_"};
        System.out.println(allValidMoves(start_1, end_1));
    }

    private static List<List<String>> allValidMoves(String[] start, String[] end) {
        
        return null;
    }
}
