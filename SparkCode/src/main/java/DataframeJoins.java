import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.functions;
import java.util.ArrayList;
import java.util.List;

public class DataframeJoins {

    public void tryoutFunctions(SparkSession ss){
        List<Row> list = new ArrayList<>();
        list.add(RowFactory.create(1,"data1,data4"));
        list.add(RowFactory.create(2,"data2,data5"));
        list.add(RowFactory.create(4,"data3,data6"));

        StructType structType = DataTypes.createStructType(new StructField[]
                {DataTypes.createStructField("Id", DataTypes.IntegerType, true),
                        DataTypes.createStructField("someData", DataTypes.StringType, true)});
        Dataset<Row> dataFrame = ss.createDataFrame(list, structType);
        dataFrame.show();
        dataFrame.createOrReplaceTempView("Table1");
        List<Row> list1 = new ArrayList<>();
        list1.add(RowFactory.create(1,"md1"));
        list1.add(RowFactory.create(1,"md2"));
        list1.add(RowFactory.create(3,"md3"));
        StructType structType1 = DataTypes.createStructType(new StructField[]
                {DataTypes.createStructField("Id1", DataTypes.IntegerType, true),
                        DataTypes.createStructField("metaData", DataTypes.StringType, true)});
        Dataset<Row> dataFrame1 = ss.createDataFrame(list1, structType1);
        dataFrame1.show();
        dataFrame1.createOrReplaceTempView("Table2");

//        dataFrame.join(dataFrame1,dataFrame.col("Id").equalTo(dataFrame1.col("Id1")),"inner")
//                .select("Id","someData").where("someData <> 'data' and Id is not null").where(functions.col("Id").notEqual(functions.lit('1'))).show();

        dataFrame.select(functions.col("Id"),functions.explode(functions.split(functions.col("someData"),",")).as("someData"))
                .withColumn("newcol",functions.lit("tos"))
                .groupBy("Id").agg(functions.concat_ws("_",functions.collect_list("someData"))).show();

       dataFrame.select(functions.element_at(functions.split(functions.col("someData"),","),2)).show();
        //        ss.sql("select * from Table1 t1 left join Table2 t2 on t1.Id = t2.Id1").show();

    }
}
