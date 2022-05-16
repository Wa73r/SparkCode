import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestHackerRankPtm {

    @Test
    public void givenSetAndSelectionSize_whenCalculatedUsingSetRecursiveAlgorithm_thenExpectedCount() {

        HackerRankPtm generator = new HackerRankPtm();
int N = 5;
int R = 6;
        List<int[]> combinations = generator.generate(N, R);
        for (int[] combination : combinations) {
            System.out.println(Arrays.toString(combination));
        }
        System.out.printf("generated %d combinations of %d items from %d ", combinations.size(), R, N);

//        List<int[]> selection = generator.generate(N, R);
//        assertEquals(nCr, selection.size());
    }
}
