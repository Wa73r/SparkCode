import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.*;
import org.junit.Before;
import org.junit.Test;
import com.holdenkarau.spark.testing.JavaDatasetSuiteBase;

public class TestSampleCode {
    Sample sample;
    SparkSession ss;
    String stockPriceInputDir;
    String sampledataSet;
    @Before
    public void setUp(){
        sample = new Sample();
        ss = SparkSession.builder().master("local").appName("sample").getOrCreate();
        stockPriceInputDir = "src/test/resources/input/stock_prices";
        sampledataSet = "src/test/resources/sampleDataset";
    }

    @Test
    public void TestCode(){
        Dataset<Row> csv = ss.read().option("header", "true").csv("/Users/kunal.pawar/Desktop/sampleDataset1.csv");
        csv.createOrReplaceTempView("tablename");
        Dataset<Row> sql = ss.sql("select Date,case when(Payments-PaymentsLag>0) then 1 when(Payments-PaymentsLag<=0) then -1 else null end as variance_flag from " +
                "(select Date,Payments,Lag(Payments,1) over (order by Date) as PaymentsLag from tablename)");
sql.show();
    }
    @Test
    public void TestCodeMovingAvg(){
        Dataset<Row> csv = ss.read().option("header", "true").csv(stockPriceInputDir);
        csv.createOrReplaceTempView("tablename");
        Dataset<Row> sql = ss.sql("select *," +
                " avg(stockPrice) over ( PARTITION BY stockId ORDER BY timeStamp ROWS BETWEEN 2 PRECEDING AND CURRENT ROW) AS movignAvg from tablename");
        sql.show();
    }

    @Test
    public void TestNTileFunction(){
        StructType structType = new StructType();

        StructField id = new StructField("id", DataTypes.IntegerType, false, Metadata.empty());
        StructField name = new StructField("name", DataTypes.StringType, false,Metadata.empty());
        StructField sex = new StructField("sex", DataTypes.StringType, false,Metadata.empty());
        StructField salary = new StructField("salary", DataTypes.IntegerType, false, Metadata.empty());

        structType.add(id);
        structType.add(name);
        structType.add(sex);
        structType.add(salary);
        StructType schema = new StructType(new StructField[]{id, name,
                sex, salary});
        Dataset<Row> csv = ss.read().option("header", "true").schema(schema).csv(sampledataSet);
        csv.printSchema();
        csv.createOrReplaceTempView("tablename");
        Dataset<Row> sql = ss.sql("select *, ntile(3) over ( PARTITION BY sex ORDER BY salary) AS tile from tablename");
        sql.show();
    }

    @Test
    public void wordCount(){
        RDD<String> stringRDD = ss.sparkContext().textFile("/Users/kunal.pawar/Documents/SFTP Link", 1);
//        sql.show();
    }
}
