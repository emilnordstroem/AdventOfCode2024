package december1th;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Integer> firstInputArrayList = new ArrayList<>();
    private static ArrayList<Integer> secondInputArrayList = new ArrayList<>();
    private static ArrayList<Integer> differenceArrayList = new ArrayList<>();
    private static ArrayList<Integer> occurrenceArrayList = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        //===============================================================
        // Part one
        firstInputArrayList = readFileObject("src/december1th/firstInputList.txt");
        insertionSortAlgorithm(firstInputArrayList);

        secondInputArrayList = readFileObject("src/december1th/secondInputList.txt");
        insertionSortAlgorithm(secondInputArrayList);

        differenceArrayList = comparisonDifference(firstInputArrayList, secondInputArrayList);
        System.out.println(differenceArrayList);

        System.out.println(total(differenceArrayList));

        //===============================================================
        // Part two
        occurrenceArrayList = similarityBetweenElements(firstInputArrayList, secondInputArrayList);
        System.out.println(total(occurrenceArrayList));
    }

    private static ArrayList<Integer> readFileObject(String pathname) throws FileNotFoundException {
        Scanner input = new Scanner(new File(pathname));
        ArrayList<Integer> fileContentsArrayList = new ArrayList<>();

        while(input.hasNext()){
            int number = Integer.parseInt(input.nextLine());
            fileContentsArrayList.add(number);
        }

        return fileContentsArrayList;
    }

    private static void insertionSortAlgorithm(ArrayList<Integer> arrayList){
        for(int currentIndex = 1; currentIndex < arrayList.size(); currentIndex++){
            int keyElement = arrayList.get(currentIndex);
            int previousIndex = currentIndex - 1;

            while (previousIndex >= 0 && arrayList.get(previousIndex) > keyElement){
                arrayList.set(previousIndex + 1, arrayList.get(previousIndex));
                previousIndex--;
            }

            arrayList.set(previousIndex + 1, keyElement);
        }
    }

    private static ArrayList<Integer> comparisonDifference(ArrayList<Integer> firstArrayList, ArrayList<Integer> secondArrayList){
        ArrayList<Integer> differenceArrayList = new ArrayList<>();

        int length = Math.min(firstArrayList.size(), secondArrayList.size()); // Lowest value of array.size()

        for (int index = 0; index < length; index++) {
            int elementFirstArrayList = firstArrayList.get(index);
            int elementSecondArrayList = secondArrayList.get(index);

            int difference = Math.abs(elementFirstArrayList - elementSecondArrayList); // absolut positive
            differenceArrayList.add(difference);
        }

        return differenceArrayList;
    }

    private static int total(ArrayList<Integer> arrayList){
        int result = 0;
        for(Integer number : arrayList){
            result += number;
        }
        return result;
    }

    private static ArrayList<Integer> similarityBetweenElements(ArrayList<Integer> firstArrayList, ArrayList<Integer> secondArrayList){
        ArrayList<Integer> resultArrayList = new ArrayList<>();

        for(Integer number : firstArrayList){
            int currentNumber = number;
            int occurrence = 0;
            for(Integer candidateNumber : secondArrayList){
                if(candidateNumber == currentNumber){
                    occurrence++;
                }
            }
            resultArrayList.add(currentNumber * occurrence);
        }

        return resultArrayList;
    }
}
