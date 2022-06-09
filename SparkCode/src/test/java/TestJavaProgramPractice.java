import org.junit.Before;
import org.junit.Test;

public class TestJavaProgramPractice {
    JavaProgramPractice jp;

    @Before
    public void setUp() {
        jp = new JavaProgramPractice();
    }

    @Test
    public void testsubArraySum() {

        int arr[] = {10,-20, 2, -2, -20, 10,-20};
        int sum = -10;
        int n = arr.length;
        Integer integer = jp.subArraySum(arr, n, sum);
        System.out.println(integer);
    }

}
