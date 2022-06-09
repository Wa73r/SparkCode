import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.*;
import scala.Tuple2;
import java.util.Arrays;

public class WordCountProgram {

    public void WordCountDataFrame(SparkSession session, String path) {

        Dataset<Row> text = session.read().text(path);
        Dataset<Row> rowDataset = text.withColumn("value", explode(split(column("value"), " ")));
        Dataset<Row> value = rowDataset.groupBy(upper(column("value"))).count();
        value.show();
    }

    public void WordCountRDD(SparkSession session, String path) {

        JavaSparkContext javaSparkContext = new JavaSparkContext(session.sparkContext());
        JavaRDD<String> textFile = javaSparkContext.textFile(path, 1);
        JavaPairRDD<String, Integer> counts = textFile
                .flatMap(s -> Arrays.asList(s.split(" ")).iterator())
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey((a, b) -> a + b);
        counts.rdd();
        counts.foreach(data -> {
            System.out.println("model=" + data._1() + " label=" + data._2());
        });
    }
}
