import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringSubstring {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(9,9,9,2, 3, 4, 4, 5, 5);

        Set<Integer>set = new HashSet<>();
        Set<Integer> list = new HashSet<>();
        for(Integer i : integers){
            if(!set.add(i) )
                list.add(i);
        }

        list.stream().sorted().collect(Collectors.toList());
        System.out.println(list);
        Queue<Integer> queue = new LinkedList<>();
        queue.poll();
    }
}
