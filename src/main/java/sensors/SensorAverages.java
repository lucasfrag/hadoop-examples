package sensors;

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

public class SensorAverages {

    public static class SensorMapper
            extends Mapper<Object, Text, Text, DoubleWritable> {

        private Text sensorId = new Text();
        private DoubleWritable temp = new DoubleWritable();

        public void map(Object key, Text value, Context context)
                throws IOException, InterruptedException {

            String[] parts = value.toString().split(",");
            if (parts.length == 2) {
                sensorId.set(parts[0]);
                temp.set(Double.parseDouble(parts[1]));
                context.write(sensorId, temp);
            }
        }
    }

    public static class SensorReducer
            extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

        private DoubleWritable result = new DoubleWritable();

        public void reduce(Text key, Iterable<DoubleWritable> values,
                           Context context) throws IOException, InterruptedException {

            double sum = 0;
            int count = 0;

            for (DoubleWritable v : values) {
                sum += v.get();
                count++;
            }

            result.set(sum / count);
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Sensor Averages");

        job.setJarByClass(SensorAverages.class);
        job.setMapperClass(SensorMapper.class);
        job.setReducerClass(SensorReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
