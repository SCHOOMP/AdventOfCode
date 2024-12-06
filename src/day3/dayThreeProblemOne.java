package src.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dayThreeProblemOne {
    public static void main(String[] args) throws FileNotFoundException {
        int total = 0;
        File file = new File("src/day3/problemData.txt");

        // Read the file content as a single string
        StringBuilder content = new StringBuilder();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            content.append(scanner.nextLine()).append(" ");
        }
        scanner.close();

        // Define the pattern for "mul(X, Y)" format where X and Y are integers
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

        // Search for the pattern in the content
        Matcher matcher = pattern.matcher(content.toString());
        while (matcher.find()) {
            // Extract X and Y using capturing groups
            String x = matcher.group(1); // First captured group (X)
            String y = matcher.group(2); // Second captured group (Y)

            System.out.println("Found match: " + matcher.group());
            System.out.println("X: " + x + ", Y: " + y);
            total = ((Integer.parseInt(x) * Integer.parseInt(y)) + total);
        }
        System.out.println("Total :" + total);
    }
}
