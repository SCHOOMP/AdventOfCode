package src.day5;

import java.io.*;
import java.util.*;

public class dayFiveProblemOne {
    public static void main(String[] args) {
        String filePath = "src/day5/problemData";
        try {
            List<String> lines = readInputFromFile(filePath);
            List<String[]> orderingRules = new ArrayList<>();
            List<List<Integer>> updates = new ArrayList<>();
            parseInput(lines, orderingRules, updates);
            int result = calculateMiddlePageSum(orderingRules, updates);
            System.out.println("Sum of middle page numbers: " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readInputFromFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.trim());
            }
        }
        return lines;
    }

    private static void parseInput(List<String> lines, List<String[]> orderingRules, List<List<Integer>> updates) {
        boolean readingRules = true;
        for (String line : lines) {
            if (line.isEmpty()) {
                readingRules = false;
                continue;
            }
            if (readingRules) {
                orderingRules.add(line.split("\\|"));
            } else {
                List<Integer> update = new ArrayList<>();
                for (String page : line.split(",")) {
                    update.add(Integer.parseInt(page));
                }
                updates.add(update);
            }
        }
    }

    private static int calculateMiddlePageSum(List<String[]> orderingRules, List<List<Integer>> updates) {
        int sum = 0;
        for (List<Integer> update : updates) {
            if (isValidUpdate(update, orderingRules)) {
                sum += findMiddlePage(update);
            }
        }
        return sum;
    }

    private static boolean isValidUpdate(List<Integer> update, List<String[]> orderingRules) {
        Set<Integer> updatePages = new HashSet<>(update);
        for (String[] rule : orderingRules) {
            int before = Integer.parseInt(rule[0]);
            int after = Integer.parseInt(rule[1]);
            if (updatePages.contains(before) && updatePages.contains(after)) {
                if (update.indexOf(before) > update.indexOf(after)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int findMiddlePage(List<Integer> update) {
        int size = update.size();
        return update.get(size / 2);
    }
}
