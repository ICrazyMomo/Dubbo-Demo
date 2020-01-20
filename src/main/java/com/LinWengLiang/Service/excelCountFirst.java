package com.LinWengLiang.Service;

import com.LinWengLiang.Model.DataItem;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author Qutto，
 * @Title: excelCountFirst
 * @Description: TODO
 * @date 2020/1/422:02
 */
public class excelCountFirst {
    private static class excelCountFirstMaper extends Mapper<LongWritable, Text,Text,Text>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String strs[] = line.split("\t");
            context.write(new Text(strs[0]+"\t"+strs[1]+"\t"+strs[2]+"\t"+strs[3]),new Text("1"));
        }
    }

    private static class excelCountFirstReduce extends Reducer<Text,Text,Text,Text>{
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            int count = 0;
            Iterator<Text> iterator = values.iterator();
            while (iterator.hasNext()){
                Text value = iterator.next();
                count+=Integer.parseInt(value.toString());
            }
            context.write(key,new Text(String.valueOf(count)));
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration(); // 默认只加载core-default.xml core-site.xml

        Job job = Job.getInstance(conf);

        job.setJarByClass(excelCount.class);

        job.setMapperClass(excelCountFirstMaper.class);
        job.setReducerClass(excelCountFirstReduce.class);

        job.setNumReduceTasks(1);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
//        job.setPartitionerClass(IndexPartitioner.class);
//        job.setGroupingComparatorClass(IndexGroupingComparator.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

//        FileInputFormat.setInputPaths(job, new Path("/Users/linwengjing/Downloads/Java/HadoopLogs/excelInput"));
//        FileOutputFormat.setOutputPath(job, new Path("/Users/linwengjing/Downloads/Java/HadoopLogs/excelOutput"));
        FileInputFormat.setInputPaths(job, new Path("D:\\code\\HadoopLogs\\excelInputFir"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\code\\HadoopLogs\\excelOutputFir"));  // 注意：输出路径必须不存在

        job.waitForCompletion(true);
    }
}
