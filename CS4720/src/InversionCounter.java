package CS4720.src;

import java.util.Arrays;

public class InversionCounter {
    public static void main(String[] args) throws Exception {
        int[] testArray = {54044 ,14108,79294,29649,25260, 60660, 2995, 53777,49689,9083};
        int [] otherTestArray = Arrays.copyOf(testArray, testArray.length);
        int inversions = exhaustiveCount(testArray);
        int inversionsCounted = countInv(otherTestArray);
        System.out.printf("Inversions counted from exhaustive inversion counter %d\n",inversions);
        System.out.printf("Inversions counted from mergesort inversion counter %d\n",inversionsCounted);
        for(int i : otherTestArray){
            System.out.printf("%d   ", i);
        }
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
                System.out.printf("No Inversion\nleft: %d\nright: %d\n",left[i],right[j]);
                array[k++] = left[i++];

            } else {
                System.out.printf("inversion found\nleft: %d\nright: %d\n",left[i],right[j]);
                array[k++] = right[j++];
                inversionCount++;
            }
        }

        // Add any remaining elements from the left array
        
        while (i < left.length) {
            System.out.printf("inversion found\nleft: %d\nright: %d\n",left[i],right[j-1]);
            array[k++] = left[i++];
            inversionCount++;
        }

        // Add any remaining elements from the right array
        while (j < right.length) {
            System.out.printf("No Inversion\nleft: %d\nright: %d\n",left[i-1],right[j]);
            array[k++] = right[j++];
        }
        return inversionCount;
    }
}



