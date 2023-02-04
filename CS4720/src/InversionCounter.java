package CS4720.src;

import java.util.Arrays;

public class InversionCounter {
    public static void main(String[] args) throws Exception {
        int[] testArray = {3 ,1,2,4,19, 20, 15, 7};
        int [] otherTestArray = Arrays.copyOf(testArray, testArray.length);
        int inversions = exhaustiveCount(testArray);
        int inversionsCounted = countInv(otherTestArray);
        System.out.println(inversions);
        System.out.println(inversionsCounted);

    }

    public static int exhaustiveCount(int []array){//input array
        int numInv = 0;
        for (int i = 0; i < array.length-1;i++){
            for(int j = i+1; j < array.length;j++){
                if (array[i]>array[j]){
                    numInv++;
                }
            }
        }
        return numInv;//output count of inversions
    }
    public static int countInv(int []array){//input array
        int inversionCount = 0;
        if (array.length==0 || array.length==1){
            return 0; // not in book pseudocode but it's silly to go through nlogn length algorithm if array is too small for inversions
        }
        else{
            int middle = array.length/2;
            int[] leftInv = Arrays.copyOfRange(array,0,middle); //x in pseduocode from book
            int[] rightInv = Arrays.copyOfRange(array,middle,array.length); // y in pseduocode from book
            
            // Sort the left and right halves recursively
            inversionCount += countInv(leftInv);
            inversionCount += countInv(rightInv);
            inversionCount += mergeAndCount(array, leftInv, rightInv);

        }
        return inversionCount;
    }
    private static int mergeAndCount(int[] array, int[] left, int[] right) {
        // Initialize the indices for the left, right, and original arrays
        int i = 0, j = 0, k = 0; //book initializes to 1. but this is java
        int inversionCount = 0;
        // While there are elements in both the left and right arrays, pick the smaller
        // element and add it to the original array
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) { //book psedocode uses b for left and c for right here
                array[k++] = left[i++];
                /*book uses only less than, not less than or equal to. this takes into the assumption that every
                 * element in array is different. if we have two of the same element it would count an inversion if the elements 
                 * were the same. so i made it less than or equal to
                 */
            } else {
                array[k++] = right[j++];
                inversionCount++;
            }
        }

        // Add any remaining elements from the left array
        while (i < left.length) {
            array[k++] = left[i++];
            //inversionCount++;
        }

        // Add any remaining elements from the right array
        while (j < right.length) {
            array[k++] = right[j++];
        }
        return inversionCount;
    }
}



