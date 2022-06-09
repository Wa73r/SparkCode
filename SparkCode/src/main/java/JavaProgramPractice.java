import java.util.*;
import java.util.stream.Collectors;

public class JavaProgramPractice {

    public Integer subArraySum(int arr[], int n, int sum){
//        int arr[] = { 10, 2, -2, -20, 10 };
//        int sum = -10;
//        int n = arr.length;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int sumdata = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            sumdata += arr[i];

            int remsum = sumdata - sum;
            if(map.containsKey(remsum)){
                count+=map.get(remsum);
            }
            map.put(sumdata,map.getOrDefault(sumdata,0)+1);
        }

        return count;

    }
    class Solution {
        public int solution(int[] A) {
            List<Integer> collect = Arrays.stream(A).filter(p -> p > 0).sorted().distinct().boxed().collect(Collectors.toList());
            Integer previousValue = null;
            if( collect == null || collect.isEmpty())
                return 1;
            else {
                for(Integer i : collect){
                    if(previousValue != null ){
                        if(i - previousValue != 1 )
                             return previousValue+1;
                    }
                    else
                        previousValue = i;
                }
            }
            return previousValue+1;
        }
    }
}
