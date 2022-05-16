import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestReadCSV {

    Sample sample;
    SparkSession ss;
    @Before
    public void setUp(){
        sample = new Sample();
        ss = SparkSession.builder().master("local").appName("sample").getOrCreate();
    }

    @Test
    public void TestReadCSVFile() throws IOException {
        String path = "/Users/kunal.pawar/Desktop/samplecsv1.csv";
        Dataset<Row> rowDataset = sample.readCSV(ss, path);
        long count = rowDataset.repartition(5).count();
        System.in.read();
        assertEquals(4,count);
        ss.stop();

    }
    @Test
    public void TestReadCSVFile1() throws IOException {
        String path = "/Users/kunal.pawar/Desktop/sampleDataset.csv";
        Dataset<Row> rowDataset = sample.readCSV(ss, path);
        rowDataset.createOrReplaceTempView("temptable");
        Dataset<Row> sql = ss.sql("select salary, max(salary) over () from temptable");
        sql.show();
        ss.stop();

    }
}
