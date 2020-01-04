package com.LinWengLiang.Service;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author linwengliang
 * @Title: excelCount
 * @Description: TODO
 * @date 2020/1/32:27 PM
 */
public class excelCount {

    public static  class excelCountMap extends Mapper<LongWritable,Text,Text,Text>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] fields = line.split("\t");
            context.write(new Text(fields[0]+","+fields[1]+","),new Text(fields[2].substring(0,2)+fields[3]));
        }
    }

    public static class excelCountReduce extends Reducer<Text,Text,Text,Text>{
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            StringBuilder sb = new StringBuilder();
            for (Text value:values) {
                if (values.iterator().hasNext()){
					sb.append(value.toString()).append("\t");
				}else {
					sb.append(value.toString());
				}
            }
            context.write(key,new Text(sb.toString()));
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration(); // 默认只加载core-default.xml core-site.xml

        Job job = Job.getInstance(conf);

        job.setJarByClass(excelCount.class);

        job.setMapperClass(excelCountMap.class);
        job.setReducerClass(excelCountReduce.class);

        job.setNumReduceTasks(1);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);


        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

//        FileInputFormat.setInputPaths(job, new Path("/Users/linwengjing/Downloads/Java/HadoopLogs/excelInput"));
//        FileOutputFormat.setOutputPath(job, new Path("/Users/linwengjing/Downloads/Java/HadoopLogs/excelOutput"));
        FileInputFormat.setInputPaths(job, new Path("D:\\code\\HadoopLogs\\excelInput"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\code\\HadoopLogs\\excelOutput"));  // 注意：输出路径必须不存在

        job.waitForCompletion(true);
    }
}
