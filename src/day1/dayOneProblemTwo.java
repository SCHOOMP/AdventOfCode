package src.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class dayOneProblemTwo {
    public static void main(String[] args) {
        int[] leftColumn = null;
        int[] rightColumn = null;
        try {
            File file = new File("src/day1/day_one_inputs.txt");
            Scanner scanner = new Scanner(file);
            int totalDifference = 0;
            int timesrun = 0;
            leftColumn = new int[1000];
            rightColumn = new int[1000];
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] columns = line.split("\\s+");
                if (columns.length >= 2) {
                    leftColumn[timesrun] = Integer.parseInt((columns[0]));
                    rightColumn[timesrun] = Integer.parseInt((columns[1]));
                    totalDifference++;
                    timesrun++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        searchFriend(leftColumn, rightColumn);
    }

    public static void searchFriend(int[] left, int[] right) {
        // Sort the right column to optimize binary search
        int total = 0;
        Arrays.sort(right);
        for (int i = 0; i < left.length; i++) {
            if (left[i] != 0) {
                int index = Arrays.binarySearch(right, left[i]);
                if (index >= 0) {
                    int count = 0;
                    for (int j = 0; j < right.length; j++) {
                        if (right[j] == left[i]) {
                            count++;
                        }
                    }
                    System.out.println("The number " + left[i] + " is found in the right column and repeats " + count + " times.");
                    total = (left[i] * count) + total;
                }
            }
        }
        System.out.println("The total time is " + total);
    }
}
