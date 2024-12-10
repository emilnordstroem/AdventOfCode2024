package december2th;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int numberOfSafeReports = findSafeReports("src/december2th/report.txt");
        System.out.println(numberOfSafeReports);
    }

    private static int findSafeReports(String pathname) throws FileNotFoundException {
        Scanner input = new Scanner(new File(pathname));
        int numberOfSafeReports = 0;

        while(input.hasNextLine()){
            String line = input.nextLine();
            String[] numbers = line.split(" ");
            if(isReportSafe(numbers)){
                numberOfSafeReports++;
            }
        }
        return numberOfSafeReports;
    }

    private static boolean isReportSafe(String[] report){
        ArrayList<Integer> currentReport = StringArrayToIntegerArray(report);

        if(checkReportSafety(currentReport)){
            // If report is already safe...
            return true;
        }

        for (int index = 0; index < currentReport.size(); index++) {
            ArrayList<Integer> copiedCurrentReport = new ArrayList<>(currentReport);
            copiedCurrentReport.remove(index);
            if (checkReportSafety(copiedCurrentReport)) {
                return true;
            }
        }
        return false;
    }

    private static Boolean checkReportSafety(ArrayList<Integer> currentReport){
        boolean isDescending = false;
        boolean isAscending = false;

        for (int index = 1; index < currentReport.size(); index++) {
            int previous = currentReport.get(index - 1);
            int current = currentReport.get(index);
            // Check difference
            int difference = Math.abs(current - previous);
            if (difference == 0 || difference > 3) {
                return false;
            }
            // Check ascending or descending
            if (current > previous) {
                isAscending = true;
            } else if (current < previous) {
                isDescending = true;
            }
            // Does ascending and descending both occur
            if (isAscending && isDescending) {
                return false;
            }
        }
        return true;
    }

    private static ArrayList<Integer> StringArrayToIntegerArray(String[] stringArray){
        ArrayList<Integer> intergerArrayList = new ArrayList<>();
        for(String string : stringArray){
            intergerArrayList.add(Integer.parseInt(string));
        }
        return intergerArrayList;
    }
}
