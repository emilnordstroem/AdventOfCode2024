package december4th;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static final int GRID_SIZE = 140;
    private static final String TARGET = "XMAS";

    public static void main(String[] args) throws FileNotFoundException {
        char[][] grid = readFile("src/december4th/input.txt");
        System.out.println(totalOccurrenceOfXmas(grid));
    }

    private static char[][] readFile(String pathname) throws FileNotFoundException {
        char[][] grid = new char[GRID_SIZE][GRID_SIZE];
        Scanner scanner = new Scanner(new File(pathname));

        for (int index = 0; index < GRID_SIZE && scanner.hasNextLine(); index++) {
            grid[index] = scanner.nextLine().toCharArray();
        }

        return grid;
    }

    private static int totalOccurrenceOfXmas(char[][] grid) {
        int result = 0;
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                result += occurrenceOfTarget(grid, row, column);
            }
        }
        return result;
    }

    private static int occurrenceOfTarget(char[][] grid, int row, int col) {
        int result = 0;
        int[] xDirection = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] yDirection = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int direction = 0; direction < 8; direction++) {
            if (occurrenceAtDirection(grid, row, col, xDirection[direction], yDirection[direction])) {
                result++;
            }
        }
        return result;
    }

    private static boolean occurrenceAtDirection(char[][] grid, int row, int col, int xDirection, int yDirection) {
        for (int index = 0; index < TARGET.length(); index++) {
            int currentRow = row + index * xDirection;
            int currentColumn = col + index * yDirection;
            if (currentRow < 0 || currentRow >= GRID_SIZE || currentColumn < 0 || currentColumn >= GRID_SIZE
                    || grid[currentRow][currentColumn] != TARGET.charAt(index)) {
                return false;
            }
        }
        return true;
    }
}