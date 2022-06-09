import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.apache.spark.sql.functions.*;
public class PracticeDataFrame {

    public void tryoutFunctions(SparkSession ss, String path){
//        Dataset<Row> csv = ss.read().option("header", "true").csv(path);
        List<Row> list=new ArrayList<Row>();
        list.add(RowFactory.create("one"));
        list.add(RowFactory.create("two"));
        list.add(RowFactory.create("three"));
        list.add(RowFactory.create("four"));


        Dataset<Row> column1 = ss.createDataFrame(list, DataTypes.createStructType(Arrays.asList(DataTypes.createStructField("column1", DataTypes.StringType, true))));
        column1.show();
    }

    public void testJavaCode(){
        List<Integer> arr = Arrays.asList(-4, 3, -9, 0, 4, 1);
        Map<String,Long> map = arr.stream().map(p -> {
            if(p>0){
                return "positive";
            } else if(p<0){
                return "negative";
            } else{
                return "zero";
            }
        }).collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        Collections.sort(arr);
        Long collect1 = map.values().stream().collect(Collectors.summingLong(Long::longValue));

        if(map.containsKey("positive"))
            System.out.println(String.format("%.6f",map.get("positive")/Double.valueOf(collect1)));
        else
            System.out.println(String.format("%.6f",0.0));
        if(map.containsKey("negative"))
            System.out.println(String.format("%.6f",map.get("negative")/Double.valueOf(collect1)));
        else
            System.out.println(String.format("%.6f",0.0));
        if(map.containsKey("zero"))
            System.out.println(String.format("%.6f",map.get("zero")/Double.valueOf(collect1)));
        else
            System.out.println(String.format("%.6f",0.0));
    }

    public void minMaxSum(){
        List<Integer> integers = Arrays.asList(1, 2, 9, 5, 2);
//

        //
        integers.sort(Comparator.naturalOrder());
        Long collect = integers.stream().map(p->Long.valueOf(p))
                .collect(Collectors.toList()).subList(0, 4).stream().collect(Collectors.summingLong(Long::longValue));

        Long collect1 = integers.subList(1, 5).stream().map(p->Long.valueOf(p)).collect(Collectors.toList()).stream()
                .collect(Collectors.summingLong(Long::longValue));
        System.out.println(collect+","+collect1);
    }

    public void changeHour()    {
        String str = "12:45:54PM";
        if(str.contains("AM")){
            str = str.replaceAll("AM","");
            String[] split = str.split(":");
            split[0]= String.valueOf(Integer.valueOf(split[0])==12?"00":split[0]);
            str = Arrays.stream(split).collect(Collectors.joining(":"));
        }

        else{
            str = str.replaceAll("PM","");
            String[] split = str.split(":");
            split[0]= String.valueOf(Integer.valueOf(split[0])==12?"12":Integer.valueOf(split[0])+12);
            str = Arrays.stream(split).collect(Collectors.joining(":"));
        }
        System.out.println(str);
    }

    public List<Integer> lonelyInteger(List<Integer> a)    {
        Map<Integer, Long> collect = a.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        List<Integer> collect1 = collect.entrySet().stream().filter(p -> p.getValue() % 2 == 1l).map(p -> p.getKey()).collect(Collectors.toList());
        return collect1;
    }

    public Integer diagonalDifference(List<List<Integer>> a)    {
//        11 12 13 14
//        21 22 23 24
//        31 32 33 34
//        41 42 43 44
        Integer lToR = 0;
        Integer rToL = 0;
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.size(); j++) {
                if(i==j) {
                    lToR+=a.get(i).get(j);
                }
                if((a.size()-1)-j==i) {
                    rToL+=a.get(i).get(j);
                }
            }
        }
       return Math.abs(lToR-rToL);
    }

    public List<Integer> countingSort1(List<Integer> arr) {

        Map<Integer, Long> collect = arr.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        List<Integer> range = IntStream.rangeClosed(0, 99)
                .boxed().collect(Collectors.toList());
        List<Integer> collect1 = range.stream().map(p -> {
            if(collect.containsKey(p)){
                Long l = collect.get(p);
                return Integer.valueOf(l.toString());}
                else return 0;
        }).collect(Collectors.toList());
        return collect1;
    }

    public static int flippingMatrix(List<List<Integer>> matrix) {
        // Write your code here

        return 0;
    }

    public static void flipRow(int r,List<List<Integer>> matrix){
        Collections.reverse(matrix.get(r));
    }
    public static void flipColumn(int c,List<List<Integer>> matrix){
        // int size = matrix.size();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i< matrix.size();i++){
            list.add(matrix.get(c).get(i));
        }
        Collections.reverse(list);
        for(int i = 0; i< matrix.size();i++){
            Integer integer = matrix.get(c).get(i);
//            matrix.get(c).set(i,)
        }
    }
    public int towerBreakers(int n, int m) {
        // Write your code here
        return (m != 1 && n%2 == 1)? 1 : 2;
    }

    public static String caesarCipher(String s, int k) {
        // Write your code here
        char c = s.charAt(1);
        s.chars().boxed().map(p->(char)(int)p).forEach(System.out::println);

        k = k%26;
        char[] chars = s.toCharArray();
        for(int i = 0; i< s.length(); i++){
            chars[i] =(char)(((int)chars[i]>='a'&&(int)chars[i]<='z')?((int)chars[i]+k >'z')?(char)'a'+((int)chars[i]+(k-1)-'z'):(int)chars[i]+k:
                    ((int)chars[i]>='A'&&(int)chars[i]<='Z')?((int)chars[i]+k >'Z')?(char)'A'+((int)chars[i]+(k-1)-'Z'):(int)chars[i]+k:chars[i]);
        }
        return String.valueOf(chars);
    }
    public int palindromeIndex(String s) {
//        System.out.print(s);
        if(isPalindrome(s)){
            return -1;
        }
        char[] chars = s.toCharArray();
        int length = chars.length;
        for(int i = 0 ;i<length; i++){
            if(chars[i] != chars[(length-i)-1]){
                if(chars[i+1] == chars[(length-i)-1]){
                    return i;
                }
                else{
                    return (length-i)-1;
                }
            }
        }
        return -1;
    }
    public Boolean isPalindrome(String s){
        StringBuilder sb = new StringBuilder(s);
        return s.equals(sb.reverse().toString());
    }

    public static long repeatedString(String s, long n) {
        // Write your code here
        int slength = s.length();
        if (slength>n){

//                    for (sle)
        }
        return 1l;
    }
//hgygsvlfcwnswtuhmyaljkqlqjjqlqkjlaymhutwsnwcwflvsgygh
}
