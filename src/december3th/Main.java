package december3th;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<String> memoryLines = readMemoryFile("src/december3th/memory.txt");
            int resultPart1 = sumAllMultiplications(memoryLines);
            System.out.println(resultPart1);

            int resultPart2 = sumEnabledMultiplications(memoryLines);
            System.out.println(resultPart2);
        } catch (FileNotFoundException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static ArrayList<String> readMemoryFile(String pathname) throws FileNotFoundException {
        ArrayList<String> memoryLines = new ArrayList<>();
        try (Scanner reader = new Scanner(new File(pathname))) {
            while (reader.hasNextLine()) {
                memoryLines.add(reader.nextLine());
            }
        }
        return memoryLines;
    }

    private static int sumAllMultiplications(ArrayList<String> memoryLines) {
        String cleanedMemory = String.join("", memoryLines);
        return sumMultiplications(cleanedMemory, false);
    }

    private static int sumEnabledMultiplications(ArrayList<String> memoryLines) {
        String cleanedMemory = String.join("", memoryLines);
        return sumMultiplications(cleanedMemory, true);
    }

    private static int sumMultiplications(String memory, boolean checkEnabled) {
        int sum = 0;
        boolean enabled = true;
        Pattern pattern = Pattern.compile("(mul\\((\\d+),(\\d+)\\)|(?:do|don't)\\(\\))");
        Matcher matcher = pattern.matcher(memory);

        while (matcher.find()) {
            String found = matcher.group(1);
            if (found.startsWith("mul")) {
                if (!checkEnabled || enabled) {
                    int firstNumber = Integer.parseInt(matcher.group(2));
                    int secondNumber = Integer.parseInt(matcher.group(3));
                    sum += firstNumber * secondNumber;
                }
            } else if (checkEnabled) {
                enabled = found.equals("do()");
            }
        }

        return sum;
    }
}
