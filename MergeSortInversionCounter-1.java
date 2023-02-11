import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Travis Ditmanson
 * CS 4720 001
 * Exercise 3.8
 * Program performs a merge sort and counts the number inversions in an array of
 * numbers recieved from a text file
 */
public class MergeSortInversionCounter {
    public static void main(String[] args) throws IOException {
        File smallTest = new File("problem3.5test.txt");
        File largeTest = new File("problem3.5.txt");// text file must be saved in the same directory as the java file

        Scanner inputTest = new Scanner(smallTest);
        Scanner input = new Scanner(largeTest);

        Queue<Integer> smallNumberedQueue = new LinkedList<>();
        Queue<Integer> numberedQueue = new LinkedList<>();// queue used to read the text file to account for any size
                                                          // text file

        smallNumberedQueue = fileToQueue(inputTest);
        numberedQueue = fileToQueue(input);

        int [] numberSmallTest =new int[smallNumberedQueue.size()];
        int[] numbers = new int[numberedQueue.size()];// create the array of appropriate size

        for(int i=0; i< numberSmallTest.length; i++){
            numberSmallTest[i] = smallNumberedQueue.remove();//remove each item from the queue into the array in keeping the same order
        }

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = numberedQueue.remove();
        }


        long inversionCountedOnTest = sortAndCountInv(numberSmallTest);
        long inversionsCounted = sortAndCountInv(numbers);
        // this is the main part of the problem. Long used instead of int because
        // program counted too many inversions to save in an int
        System.out.printf("\ninversions counted on small test :%d", inversionCountedOnTest);
        System.out.printf("\ninversions counted on large test :%d", inversionsCounted);
        /**
         * uncomment to check if the array is sorted
         * for(int i=0; i< numbers.length; i++){
         * System.out.printf("%d\t", numbers[i]);
         * }
         */
    }

    // this method was used to fill a queue from the input. thought it might be
    // useful to save this for future assignments
    public static Queue<Integer> fileToQueue(Scanner input) {
        Queue<Integer> integersRead = new LinkedList<>();
        while (input.hasNextInt()) {
            integersRead.offer(input.nextInt());
        }
        return integersRead;
    }

    public static long sortAndCountInv(int[] array) {// input array
        long splitInv = 0;
        if (array.length == 0 || array.length == 1) {
            return 0; // base case
        } else {
            int middle = array.length / 2;
            int[] leftInv = Arrays.copyOfRange(array, 0, middle); // x in pseduocode from book slides
            int[] rightInv = Arrays.copyOfRange(array, middle, array.length); // y in pseduocode from book slides

            // Sort the left and right halves recursively
            splitInv += sortAndCountInv(leftInv);
            splitInv += sortAndCountInv(rightInv);
            splitInv += mergeAndCountSplitInv(array, leftInv, rightInv);

        }
        return splitInv;
    }

    private static int mergeAndCountSplitInv(int[] array, int[] left, int[] right) {
        // Initialize the indices for the left, right, and original arrays
        int i = 0, j = 0, splitInv = 0; // book initializes to 1. but this is java
        for (int k = 0; k < array.length; k++) {
            if ((i < left.length) && (j < right.length)) {
                if (left[i] <= right[j]) {
                    array[k] = left[i++];
                } else {
                    array[k] = right[j++];
                    splitInv += (((array.length) / 2) - i + 0);
                }
            }
            // psedo code doesn't account for array out of bounds
            else if ((i < left.length) && (j == right.length)) {
                array[k] = left[i++];// this is for if the right side of array has run through it's loop
            } else if ((i == left.length) && (j < right.length)) {
                array[k] = right[j++];// this is for if the left side of the array has run through it's loop
            } else {
                System.out.println("error"); // should never run, if it does there's a problem
            }

        }
        return splitInv;
    }
}
