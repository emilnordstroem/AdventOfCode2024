package december4th;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static final int gridSize = 140;
    private static final String target = "XMAS";

    public static void main(String[] args) throws FileNotFoundException {
        char[][] grid = readFile("src/december4th/input.txt");
        char[][] sampleGrid = {
                {'X', 'M', 'X', 'S', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'A', 'X', 'X', 'M', 'S', 'M', 'S', 'X'},
                {'X', 'M', 'X', 'S', 'X', 'M', 'A', 'A', 'X', 'X'},
                {'X', 'X', 'A', 'X', 'A', 'S', 'M', 'S', 'M', 'X'},
                {'X', 'M', 'X', 'S', 'X', 'M', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'S', 'X', 'S', 'X', 'S', 'X', 'S', 'X', 'S', 'X'},
                {'X', 'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X', 'X'},
                {'M', 'X', 'M', 'X', 'M', 'X', 'M', 'X', 'M', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
        };

        System.out.println(totalOccurrenceOfXmas(grid));
        System.out.println(totalOccurrencesOfX(grid));
    }

    private static char[][] readFile(String pathname) throws FileNotFoundException {
        char[][] grid = new char[gridSize][gridSize];
        Scanner scanner = new Scanner(new File(pathname));

        for (int index = 0; index < gridSize && scanner.hasNextLine(); index++) {
            grid[index] = scanner.nextLine().toCharArray();
        }

        return grid;
    }

    private static int totalOccurrenceOfXmas(char[][] grid) {
        int result = 0;
        for (int row = 0; row < gridSize; row++) {
            for (int column = 0; column < gridSize; column++) {
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
        for (int index = 0; index < target.length(); index++) {
            int currentRow = row + index * xDirection;
            int currentColumn = col + index * yDirection;
            if (currentRow < 0 || currentRow >= gridSize || currentColumn < 0 || currentColumn >= gridSize
                    || grid[currentRow][currentColumn] != target.charAt(index)) {
                return false;
            }
        }
        return true;
    }

    private static int totalOccurrencesOfX(char[][] grid){
        int result = 0;

        for(int indexRow = 1; indexRow < gridSize - 1; indexRow++){
            for(int indexColumn = 1; indexColumn < gridSize - 1; indexColumn++){
                if(occurringX(grid, indexRow, indexColumn)){
                    result++;
                }
            }
        }

        return result;
    }

    private static boolean occurringX(char[][] grid, int row, int column) {
        if (row <= 0 || row >= gridSize - 1 || column <= 0 || column >= gridSize - 1) {
            return false;
        }

        return  (grid[row - 1][column - 1] == 'M' && grid[row + 1][column + 1] == 'S' && // Top left to bottom right
                grid[row - 1][column + 1] == 'M' && grid[row + 1][column - 1] == 'S' && // Top right to bottom left
                grid[row][column] == 'A') // Middle
                ||
                (grid[row + 1][column + 1] == 'M' && grid[row - 1][column - 1] == 'S' && // Bottom right to top left
                        grid[row + 1][column - 1] == 'M' && grid[row - 1][column + 1] == 'S' && // Bottom left to top right
                        grid[row][column] == 'A') // Middle
                ||
                (grid[row - 1][column - 1] == 'M' && grid[row + 1][column + 1] == 'M' && // Top left and bottom right are M
                        grid[row - 1][column + 1] == 'S' && grid[row + 1][column - 1] == 'S' && // Top right and bottom left are S
                        grid[row][column] == 'A') // Middle
                ||
                (grid[row + 1][column + 1] == 'S' && grid[row - 1][column - 1] == 'S' && // Bottom right and top left are S
                        grid[row + 1][column - 1] == 'M' && grid[row - 1][column + 1] == 'M' && // Bottom left and top right are M
                        grid[row][column] == 'A') // Middle
                ||
                (grid[row - 1][column - 1] == 'M' && grid[row + 1][column + 1] == 'S' && // Top left to bottom right
                        grid[row - 1][column + 1] == 'S' && grid[row + 1][column - 1] == 'M' && // Top right to bottom left
                        grid[row][column] == 'A') // Middle
                ||
                (grid[row + 1][column + 1] == 'M' && grid[row - 1][column - 1] == 'S' && // Bottom right to top left
                        grid[row + 1][column - 1] == 'S' && grid[row - 1][column + 1] == 'M' && // Bottom left to top right
                        grid[row][column] == 'A'); // Middle

    }
}