import java.util.Arrays;

    
public class MergeSortInversionCounter {
    public static void main(String[] args) throws Exception {
        int[] testArray = {54044 ,14108,79294,29649,25260, 60660, 2995, 53777,49689,9083};
        //int [] testArray={ 1,3,2};
        int inversionsCounted = sortAndCountInv(testArray);
        //System.out.printf("Inversions counted from exhaustive inversion counter %d\n",inversions);
        System.out.printf("Inversions counted from mergesort inversion counter:\t%d\n",inversionsCounted);
        System.out.println("sorted array:");
        for(int i : testArray){
            System.out.printf("%d   ", i);
        }
    }
    public static int sortAndCountInv(int []array){//input array
        int splitInv = 0;
        if (array.length==0 || array.length==1){
            return 0; // base case
        }
        else{
            int middle = array.length/2;
            int[] leftInv = Arrays.copyOfRange(array,0,middle); //x in pseduocode from book slides
            int[] rightInv = Arrays.copyOfRange(array,middle,array.length); // y in pseduocode from book slides
            
            // Sort the left and right halves recursively
            splitInv += sortAndCountInv(leftInv);
            splitInv += sortAndCountInv(rightInv);
            splitInv += mergeAndCountSplitInv(array, leftInv, rightInv);

        }
        return splitInv;
    }
    private static int mergeAndCountSplitInv(int[] array, int[] left, int[] right) {
        // Initialize the indices for the left, right, and original arrays
        int i = 0, j = 0, splitInv=0; //book initializes to 1. but this is java
        for (int k=0; k<array.length; k++) {
            if((i < left.length) && (j < right.length)){
                if (left[i] <= right[j]){
                array[k] = left[i++];
                }
                else{
                array[k] = right[j++];
                splitInv += (((array.length)/2) - i + 0);
                }
            }
            else if ((i<left.length)&& j==right.length) {
                array[k] = left[i++];
            }
            else if((i==left.length)&& (j<right.length)){
                array[k] = right[j++];
            }
            else{
                System.out.println("error");
            }
        
    }
    return splitInv;
    }
}
