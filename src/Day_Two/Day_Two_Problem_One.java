package src.Day_Two;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day_Two_Problem_One {
    public static void main(String[] args) {
        int[] leftColumn = null;
        int[] rightColumn = null;
        try {
            File file = new File("/Users/grahamschomp/IdeaProjects/Advent Of Code/src/Day_One/advent_numbers.txt");
            Scanner scanner = new Scanner(file);
            int totalDifference = 0;
            int timesrun = 0;
            leftColumn = new int[1000];
            rightColumn = new int[1000];
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] columns = line.split("\\s+");
                if (columns.length >= 2) {
                    System.out.println("Column 1: " + columns[0] + ", Column 2: " + columns[1] + " Difference: " + totalDifference);
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
        Arrays.sort(leftColumn);
        Arrays.sort(rightColumn);
        System.out.println(Arrays.toString(rightColumn));
        System.out.println(Arrays.toString(leftColumn));
        answerTime(leftColumn, rightColumn);
    }

    public static void answerTime(int[] left, int[] right){
        int incremnt = 0;
        int total = 0;
        for (int i = 0; i < left.length; i++) {

            if (left[incremnt] > right[incremnt]) {
                int addThis = left[incremnt] - right[incremnt];
                total += addThis;
                incremnt++;
            } else {
                int addThis = right[incremnt] - left[incremnt];
                total += addThis;
                incremnt++;
            }
        }
        System.out.print(total + " ");
    }
}
