package io.sbelkin.reimaginedfortnight.learning;

/**
 * Created by sbelkin on 02/01/2017.
 */
import org.apache.spark.api.java.*;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            throw new IllegalArgumentException("Need one arguments to work in this application");
        }
        String logFile = args[0];    // File on system
        SparkConf conf = new SparkConf().setAppName("BasicInitApplication");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> logData = sc.textFile(logFile).cache();

        long numAs = logData.filter((Function<String, Boolean>) s -> s.contains("a")).count();

        long numBs = logData.filter((Function<String, Boolean>) s -> s.contains("b")).count();

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);

        sc.stop();
    }
}
