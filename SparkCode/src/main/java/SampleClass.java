import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SampleClass {

    public static void main(String[] args) {
        //Find pairs from array with sum 0. [1,0,5,6,-1,0,-1,-5]
        List<Integer> integers = Arrays.asList(1, 0, 5, 6, -1, 0, -1, -5);
        try{
//            for(int i = 0 ; i < integers.size(); i++){
//
//
//                for (int j =0 ; j<integers.size();j++ ){
//                    if(i!=j){
//                        int sumValue = integers.get(i) + integers.get(j);
//                        if (sumValue == 0) {
//                            System.out.println("Sum zero values are :: " + integers.get(i) + "," + integers.get(j));
//                        }
//                    }
//                }
//            }

            int arr[] = { 1, 5, 7, -1, 5};
            int n = arr.length;
            int sum = 6;
            System.out.print("Count of pairs is "
                    + getPairsCount(arr, n, sum));
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
    }
    static int getPairsCount(int arr[], int n, int k)
    {
        HashMap<Integer,Integer> m = new HashMap<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (m.containsKey(k - arr[i])) {
                count += m.get(k - arr[i]);
            }
            if(m.containsKey(arr[i])){
                m.put(arr[i], m.get(arr[i])+1);
            }
            else{
                m.put(arr[i], 1);
            }
        }
        return count;
    }
}
