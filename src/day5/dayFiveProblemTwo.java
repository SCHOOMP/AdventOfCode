package src.day5;

import java.io.*;
import java.util.*;

public class dayFiveProblemTwo {
    public static void main(String[] args) {
        String filePath = "src/day5/problemData";
        try {
            List<String> lines = readInputFromFile(filePath);
            List<String[]> orderingRules = new ArrayList<>();
            List<List<Integer>> updates = new ArrayList<>();
            parseInput(lines, orderingRules, updates);
            List<List<Integer>> incorrectUpdates = new ArrayList<>();
            for (List<Integer> update : updates) {
                if (!isValidUpdate(update, orderingRules)) {
                    incorrectUpdates.add(update);
                }
            }
            int sum = 0;
            for (List<Integer> update : incorrectUpdates) {
                List<Integer> correctedUpdate = reorderUpdate(update, orderingRules);
                sum += findMiddlePage(correctedUpdate);
            }
            System.out.println("Sum of middle page numbers after reordering: " + sum);
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

    private static List<Integer> reorderUpdate(List<Integer> update, List<String[]> orderingRules) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        Set<Integer> updatePages = new HashSet<>(update);
        for (int page : update) {
            graph.put(page, new ArrayList<>());
            inDegree.put(page, 0);
        }
        for (String[] rule : orderingRules) {
            int before = Integer.parseInt(rule[0]);
            int after = Integer.parseInt(rule[1]);
            if (updatePages.contains(before) && updatePages.contains(after)) {
                graph.get(before).add(after);
                inDegree.put(after, inDegree.get(after) + 1);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int page : inDegree.keySet()) {
            if (inDegree.get(page) == 0) {
                queue.add(page);
            }
        }
        List<Integer> sorted = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            sorted.add(current);
            for (int neighbor : graph.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }
        return sorted;
    }

    private static int findMiddlePage(List<Integer> update) {
        int size = update.size();
        return update.get(size / 2);
    }
}
