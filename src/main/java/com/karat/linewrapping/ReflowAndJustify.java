package com.karat.linewrapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
We are building a word processor and we would like to implement a "reflow" functionality that also applies full justification to the text.

Given an array containing lines of text and a new maximum width, re-flow the text to fit the new width. Each line should have the exact specified width. If any line is too short, insert '-' (as stand-ins for spaces) between words as equally as possible until it fits.

Note: we are using '-' instead of spaces between words to make testing and visual verification of the results easier.


lines = [ "The day began as still as the",
          "night abruptly lighted with",
          "brilliant flame" ]

reflowAndJustify(lines, 24) "reflow lines and justify to length 24" =>

        [ "The--day--began-as-still",
          "as--the--night--abruptly",
          "lighted--with--brilliant",
          "flame" ] // <--- a single word on a line is not padded with spaces

reflowAndJustify(lines, 25) "reflow lines and justify to length 25" =>

        [ "The-day-began-as-still-as"
          "the-----night----abruptly"
          "lighted---with--brilliant"
          "flame" ]

reflowAndJustify(lines, 26) "reflow lines and justify to length 26" =>

        [ "The--day-began-as-still-as",
          "the-night-abruptly-lighted",
          "with----brilliant----flame" ]

reflowAndJustify(lines, 40) "reflow lines and justify to length 40" =>

        [ "The--day--began--as--still--as-the-night",
          "abruptly--lighted--with--brilliant-flame" ]

reflowAndJustify(lines, 14) "reflow lines and justify to length 14" =>

        ['The--day-began',
         'as---still--as',
         'the------night',
         'abruptly',
         'lighted---with',
         'brilliant',
         'flame']

reflowAndJustify(lines, 15) "reflow lines and justify to length 15" =>

        ['The--day--began',
         'as-still-as-the',
         'night--abruptly',
         'lighted----with',
         'brilliant-flame']

lines2 = [ "a b", "c d" ]

reflowAndJustify(lines2, 20) "reflow lines2 and justify to length 20" =>

        ['a------b-----c-----d']

reflowAndJustify(lines2, 4) "reflow lines2 and justify to length 4" =>

        ['a--b',
         'c--d']

reflowAndJustify(lines2, 2) "reflow lines2 and justify to length 2" =>

        ['a',
         'b',
         'c',
         'd']

All Test Cases:
                 lines, reflow width
reflowAndJustify(lines, 24)
reflowAndJustify(lines, 25)
reflowAndJustify(lines, 26)
reflowAndJustify(lines, 40)
reflowAndJustify(lines, 14)
reflowAndJustify(lines, 15)
reflowAndJustify(lines2, 20)
reflowAndJustify(lines2, 4)
reflowAndJustify(lines2, 2)

n = number of words OR total characters
 */
public class ReflowAndJustify {

    public static void main(String[] args) {
        String[] lines = {"The day began as still as the","night abruptly lighted with","brilliant flame"};
        String[] lines2 = {"a b","c d"};
        System.out.println(reflowAndJustify(Arrays.asList(lines), 24));
    }

    public static List<String> reflowAndJustify(List<String> lines, int width) {
        // Step 1: Combine lines into a single text
        String text = String.join(" ", lines);
        String[] words = text.split(" ");
        List<String> result = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        // Step 2: Reflow text into lines
        for (String word : words) {
            if (currentLine.length() == 0) {
                currentLine.append(word);
            } else if (currentLine.length() + word.length() < width) {
                currentLine.append("-").append(word);
            } else {
                result.add(currentLine.toString());
                currentLine = new StringBuilder(word);
            }
        }
        result.add(currentLine.toString());

        // Step 3: Justify each line
        for (int i = 0; i < result.size(); i++) {
            String line = result.get(i);
            int dashCount = (int) line.chars().filter(ch -> ch == '-').count();
            String[] wordsInLine = line.split("-");
            int size = line.length();
            int missingChars = width - size;

            // Distribute extra dashes
            if (dashCount > 0) {
                String[] dashes = new String[dashCount];
                Arrays.fill(dashes, "-");

                for (int x = 0; x < missingChars; x++) {
                    dashes[x % dashCount] += "-";
                }

                // Construct justified line
                StringBuilder justifiedLine = new StringBuilder();
                for (int j = 0; j < wordsInLine.length - 1; j++) {
                    justifiedLine.append(wordsInLine[j]).append(dashes[j]);
                }
                justifiedLine.append(wordsInLine[wordsInLine.length - 1]);
                result.set(i, justifiedLine.toString());
            }
        }

        return result;
    }
}
