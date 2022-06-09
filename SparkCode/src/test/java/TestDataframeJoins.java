import org.apache.spark.sql.SparkSession;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestDataframeJoins {

    DataframeJoins sample;
    SparkSession ss;
    @Before
    public void setUp(){
        sample = new DataframeJoins();
        ss = SparkSession.builder().master("local").appName("sample").getOrCreate();
        ss.sparkContext().setLogLevel("INFO");
    }

    @Test
    public void testtryoutFunctions(){
        sample.tryoutFunctions(ss);
    }

}
