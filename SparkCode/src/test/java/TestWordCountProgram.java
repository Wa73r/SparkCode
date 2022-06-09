import org.apache.spark.sql.SparkSession;
import org.junit.Before;
import org.junit.Test;

public class TestWordCountProgram {

    WordCountProgram sample;
    SparkSession ss;
    @Before
    public void setUp(){
        sample = new WordCountProgram();
        ss = SparkSession.builder().master("local").appName("sample").getOrCreate();
    }

    @Test
    public void testWordCountDataFrame(){
        sample.WordCountDataFrame(ss,"src/test/resources/sampleDataset/WordCount.txt");
    }

    @Test
    public void testWordCountRDD()  {
        sample.WordCountRDD(ss, "src/test/resources/sampleDataset/WordCount.txt");
    }
}
