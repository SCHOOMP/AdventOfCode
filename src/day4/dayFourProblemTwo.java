package src.day4;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;


public class dayFourProblemTwo {
    public static void main(String[] args) throws IOException {
        File file = new File("src/day4/problemData.txt");

        final List<String> lines = Files.readAllLines( Path.of( file.getPath() ), StandardCharsets.UTF_8 );

        final int part1 = solvePart1(lines);
        final int part2 = solvePart2(lines);

        System.out.println("Part 1 = " + part1);
        System.out.println("Part 2 = " + part2);
    }

    private static int solvePart1(final List<String> lines) {
        int sum = 0;

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(0).length(); j++) {
                if (lines.get(i).charAt(j) == 'X') {
                    sum += countXmasInEightDirections(lines, i, j);
                }
            }
        }

        return sum;
    }

    private static int solvePart2(final List<String> lines) {
        int sum = 0;

        for (int i = 1; i < lines.size() - 1; i++) {
            for (int j = 1; j < lines.get(0).length() - 1; j++) {
                if (lines.get(i).charAt(j) == 'A') {
                    sum += countMasXShaped(lines, i, j);
                }
            }
        }

        return sum;
    }

    private static int countMasXShaped(final List<String> lines, final int i, final int j) {
        int count = 0;

        final Predicate<String> isMasOrSam = s -> s.equals("MAS") || s.equals("SAM");

        final String firstDiagonal = "" + lines.get(i - 1).charAt(j - 1)
                + lines.get(i).charAt(j)
                + lines.get(i + 1).charAt(j + 1);

        final String secondDiagonal = "" + lines.get(i + 1).charAt(j - 1)
                + lines.get(i).charAt(j)
                + lines.get(i - 1).charAt(j + 1);

        count += isMasOrSam.test(firstDiagonal) && isMasOrSam.test(secondDiagonal) ? 1 : 0;

        return count;
    }

    private static int countXmasInEightDirections(final List<String> lines, final int i, final int j) {
        final String targetWord = "XMAS";

        int count = 0;

        final int upperIBound = lines.size() - 1;
        final int upperJBound = lines.get(0).length() - 1;

        final boolean isNorthInRange = isIndexInRange(i - (targetWord.length() - 1), upperIBound);
        final boolean isEastInRange = isIndexInRange(j + (targetWord.length() - 1), upperJBound);
        final boolean isSouthInRange = isIndexInRange(i + (targetWord.length() - 1), upperIBound);
        final boolean isWestInRange = isIndexInRange(j - (targetWord.length() - 1), upperJBound);

        // N
        if (isNorthInRange) {
            final StringBuilder sb = new StringBuilder();
            for (int k = 0; k < targetWord.length(); k++) {
                final char c = lines.get(i - k).charAt(j);
                sb.append(c);
            }
            count += sb.toString().equals(targetWord) ? 1 : 0;
        }

        // E
        if (isEastInRange) {
            final StringBuilder sb = new StringBuilder();
            for (int k = 0; k < targetWord.length(); k++) {
                final char c = lines.get(i).charAt(j + k);
                sb.append(c);
            }
            count += sb.toString().equals(targetWord) ? 1 : 0;
        }

        // S
        if (isSouthInRange) {
            final StringBuilder sb = new StringBuilder();
            for (int k = 0; k < targetWord.length(); k++) {
                final char c = lines.get(i + k).charAt(j);
                sb.append(c);
            }
            count += sb.toString().equals(targetWord) ? 1 : 0;
        }

        // W
        if (isWestInRange) {
            final StringBuilder sb = new StringBuilder();
            for (int k = 0; k < targetWord.length(); k++) {
                final char c = lines.get(i).charAt(j - k);
                sb.append(c);
            }
            count += sb.toString().equals(targetWord) ? 1 : 0;
        }

        // NE
        if (isNorthInRange && isEastInRange) {
            final StringBuilder sb = new StringBuilder();
            for (int k = 0; k < targetWord.length(); k++) {
                final char c = lines.get(i - k).charAt(j + k);
                sb.append(c);
            }
            count += sb.toString().equals(targetWord) ? 1 : 0;
        }

        // SE
        if (isSouthInRange && isEastInRange) {
            final StringBuilder sb = new StringBuilder();
            for (int k = 0; k < targetWord.length(); k++) {
                final char c = lines.get(i + k).charAt(j + k);
                sb.append(c);
            }
            count += sb.toString().equals(targetWord) ? 1 : 0;
        }

        // SW
        if (isSouthInRange && isWestInRange) {
            final StringBuilder sb = new StringBuilder();
            for (int k = 0; k < targetWord.length(); k++) {
                final char c = lines.get(i + k).charAt(j - k);
                sb.append(c);
            }
            count += sb.toString().equals(targetWord) ? 1 : 0;
        }

        // NW
        if (isNorthInRange && isWestInRange) {
            final StringBuilder sb = new StringBuilder();
            for (int k = 0; k < targetWord.length(); k++) {
                final char c = lines.get(i - k).charAt(j - k);
                sb.append(c);
            }
            count += sb.toString().equals(targetWord) ? 1 : 0;
        }

        return count;
    }

    private static boolean isIndexInRange(final int index, final int upperBound) {
        return 0 <= index && index <= upperBound;
    }
}
