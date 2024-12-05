package src.Day_Two;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class dayTwoProblemTwo {
    public static void main(String[] args) throws FileNotFoundException {
        List<List<Integer>> lines = new ArrayList<>();
        File file = new File("src/Day_Two/problemData.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] numbers = line.split("\\s+");
            List<Integer> lineNumbers = new ArrayList<>();
            for (String num : numbers) {
                lineNumbers.add(Integer.parseInt(num));
            }
            lines.add(lineNumbers);
        }

        int validLineCount = 0;
        int fixableLineCount = 0;

        for (List<Integer> levels : lines) {
            if (isValidSequence(levels)) {
                validLineCount++;
            } else if (isFixableSequence(levels)) {
                fixableLineCount++;
            }
        }

        System.out.println("Number of valid lines: " + validLineCount);
        System.out.println("Number of fixable lines: " + fixableLineCount);
        System.out.println("Total count of valid + fixable lines: " + (validLineCount + fixableLineCount));
    }

    public static boolean isValidSequence(List<Integer> levels) {
        if (levels.size() < 2) return false; // A single level can't meet the requirements

        boolean isIncreasing = true;
        boolean isDecreasing = true;

        for (int i = 0; i < levels.size() - 1; i++) {
            int current = levels.get(i);
            int next = levels.get(i + 1);
            int difference = next - current;

            if (Math.abs(difference) < 1 || Math.abs(difference) > 3) {
                return false;
            }

            if (difference < 0) {
                isIncreasing = false;
            } else if (difference > 0) {
                isDecreasing = false;
            }
        }

        // A valid sequence must be strictly increasing or decreasing
        return isIncreasing || isDecreasing;
    }

    public static boolean isFixableSequence(List<Integer> levels) {
        for (int i = 0; i < levels.size(); i++) {
            // Create a new list without the current element
            List<Integer> modifiedLevels = new ArrayList<>(levels);
            modifiedLevels.remove(i);

            // Check if the modified list is valid
            if (isValidSequence(modifiedLevels)) {
                return true;
            }
        }
        return false;
    }
}
