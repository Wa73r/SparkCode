import org.apache.spark.sql.SparkSession;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class TestPracticeDataFrame {

    PracticeDataFrame sample;
    SparkSession ss;
    String path = "src/test/resources/input/stock_prices";
    @Before
    public void setUp(){
        sample = new PracticeDataFrame();
//        ss = SparkSession.builder().master("local").appName("sample").getOrCreate();
//        ss.sparkContext().setLogLevel("INFO");
    }

    @Test
    public void testtryoutFunctions(){
        sample.tryoutFunctions(ss,path);
    }

    @Test
    public void testJavaCode()  {
        sample.testJavaCode();
    }
    @Test
    public void testMinMaxSum()  {
        sample.minMaxSum();
    }

    @Test
    public void testchangeHour()    {
        sample.changeHour();
    }

    @Test
    public void testLonelyInteger()    {
        List<Integer> integers = sample.lonelyInteger(Arrays.asList(1, 2, 3, 4, 5, 3, 2, 1));
        System.out.println(integers);
    }
    @Test
    public void testDiagonalDifference()    {
        Integer integers = sample.diagonalDifference(Arrays.asList(Arrays.asList(1,2,3),
                Arrays.asList(1,2,3),Arrays.asList(1,2,3)));
        System.out.println(integers);
    }
    @Test
    public void testcountingSort1() {
sample.countingSort1(Arrays.stream("63 25 73 1 98 73 56 84 86 57 16 83 8 25 81 56 9 53 98 67 99 12 83 89 80 91 39 86 76 85 74 39 25 90 59 10 94 32 44 3 89 30 27 79 46 96 27 32 18 21 92 69 81 40 40 34 68 78 24 87 42 69 23 41 78 22 6 90 99 89 50 30 20 1 43 3 70 95 33 46 44 9 69 48 33 60 65 16 82 67 61 32 21 79 75 75 13 87 70 33".split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
    }
    @Test
    public void testcaesarCipher() {
        String abcdef = sample.caesarCipher("abcdefz", 2);
        System.out.println(abcdef);
    }

    @Test
    public void testpalindromeIndex() {
        String samplestr = "hgygsvlfcwnswtuhmyaljkqlqjjqlqkjlaymhutwsnwcwflvsgygh";
        int i = sample.palindromeIndex(samplestr);
        System.out.println(i);
    }

    public static void main(String[] args) {
        Character c = 'a';
        int numericValue = Character.getNumericValue(c);
        System.out.println(numericValue+ ", "+ (int)'a');
//        HashSet<Integer> ();
    }
}
