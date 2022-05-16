import org.apache.spark.sql.*;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class Dataframes {
    public static void main(String[] args) {

        SparkSession ss = SparkSession.builder().master("local[*]").appName("sample").getOrCreate();
        StructField pci = DataTypes.createStructField("pci", DataTypes.IntegerType, false);
        StructField childlongitude = DataTypes.createStructField("childlongitude", DataTypes.DoubleType, false);
        StructField childlatitude = DataTypes.createStructField("childlatitude", DataTypes.DoubleType, false);


        ArrayType arrayType = DataTypes.createArrayType(new StructType(new StructField[]{pci, childlatitude, childlongitude}));

        String json = "[{\"pci\":24,\"childlongitude\":139.327451,\"childlatitude\":35.432748},{\"pci\":23,\"childlongitude\":139.327461,\"childlatitude\":35.432749}]";

        Dataset<Row> rowDataset = ss.sql("select '" + json + "' as Response").select(functions.from_json(new Column("Response"), arrayType).as("json"))
                .select(functions.explode(new Column("json")).as("finalcol")).select(functions.col("finalcol.pci")
                        ,functions.col("finalcol.childlongitude").as("longitude"),functions.col("finalcol.childlatitude").as("latitude")).distinct();

        rowDataset.show();
        rowDataset.printSchema();
    }
}
