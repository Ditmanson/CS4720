package CS4720.src;

import java.util.Arrays;

public class InversionCounter {
    public static void main(String[] args) throws Exception {
        int[] testArray = {3 ,1,2,4, 7};
        int inversions = exhaustiveCount(testArray);
        System.out.println(inversions);

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
    public static int CountInv(int []array){//input array
        if (array.length==0 || array.length==1){
            return 0;
        }
        else{
            int middle = array.length/2;
            int[] leftInv = Arrays.copyOfRange(array,0,middle);
            int[] rightInv = Arrays.copyOfRange(array,middle,array.length);
            
        }
        return 0;
    }
}


