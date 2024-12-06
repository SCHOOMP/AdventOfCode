package src.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dayThreeProblemTwo {
    public static void main(String[] args) throws FileNotFoundException {
        int total = 0;
        boolean isMulEnabled = false;
        File file = new File("src/day3/problemData.txt");
        StringBuilder content = new StringBuilder();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            content.append(scanner.nextLine()).append(" ");
        }
        Pattern pattern = Pattern.compile("do\\(\\)|don't\\(\\)|mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(content.toString());
        while (matcher.find()) {
            String match = matcher.group();

            if (match.equals("do()")) {
                isMulEnabled = true;
            } else if (match.equals("don't()")) {
                isMulEnabled = false;
            } else if (match.startsWith("mul") && isMulEnabled) {
                // Process mul instruction only if enabled
                String x = matcher.group(1);
                String y = matcher.group(2);
                System.out.println("Processing mul: " + match + " -> X: " + x + ", Y: " + y);
                total += Integer.parseInt(x) * Integer.parseInt(y);
            }
        }

        System.out.println("Total: " + total);
    }
}
