package sales;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TotalSalesByProduct {

    public static class SalesMapper
            extends Mapper<Object, Text, Text, DoubleWritable> {

        private Text product = new Text();
        private DoubleWritable total = new DoubleWritable();

        public void map(Object key, Text value, Context context)
                throws IOException, InterruptedException {

            String line = value.toString();

            if (line.startsWith("product")) return; // header

            String[] parts = line.split(",");
            if (parts.length == 3) {
                String name = parts[0];
                double price = Double.parseDouble(parts[1]);
                int qty = Integer.parseInt(parts[2]);

                product.set(name);
                total.set(price * qty);

                context.write(product, total);
            }
        }
    }

    public static class SalesReducer
            extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

        private DoubleWritable result = new DoubleWritable();

        public void reduce(Text key, Iterable<DoubleWritable> values,
                           Context context) throws IOException, InterruptedException {

            double sum = 0.0;
            for (DoubleWritable v : values) sum += v.get();
            result.set(sum);
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Total Sales By Product");

        job.setJarByClass(TotalSalesByProduct.class);
        job.setMapperClass(SalesMapper.class);
        job.setReducerClass(SalesReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
