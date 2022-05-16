import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Sample {

    Dataset<Row> readCSV(SparkSession session,String path){
        Dataset<Row> load = session.read().format("CSV").option("header", "true").load(path);
        return load;
    }
}
