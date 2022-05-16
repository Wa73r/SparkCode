import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GetDecimalNumber {

    public static void main(String[] args) {

        List<Integer> integers = Arrays.asList(1, 1, 0, 1);
        int j = 0;
        Double finalSum = 0.0;
        Collections.reverse(integers);
        for (Integer inte : integers) {
            double pow = Math.pow(2, j);
            finalSum = finalSum+integers.get(j)*pow;
            j++;
        }
        System.out.println(finalSum);
    }
}
