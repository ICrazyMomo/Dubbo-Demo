package com.LinWengLiang.Service;

import com.LinWengLiang.Model.DataItem;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author linwengliang
 * @Title: excelCount
 * @Description: TODO
 * @date 2020/1/32:27 PM
 */
public class excelCount {

    public static  class excelCountMap extends Mapper<LongWritable,Text,DataItem,Text>{
        DataItem item = new DataItem();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] fields = line.split("\t");
            item.set(fields[1],fields[2],fields[3].substring(0,2),fields[4],Integer.parseInt(fields[0]));
//            item.setTwo(fields[1],fields[3],Integer.parseInt(fields[0]));
            context.write(item,new Text(fields[3].substring(0,2)+fields[4]));
        }
    }

    public static class excelCountReduce extends Reducer<DataItem,Text,DataItem,Text>{
        @Override
        protected void reduce(DataItem key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
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

        job.setMapOutputKeyClass(DataItem.class);
        job.setMapOutputValueClass(Text.class);
        job.setPartitionerClass(IndexPartitioner.class);
        job.setGroupingComparatorClass(IndexGroupingComparator.class);

        job.setOutputKeyClass(DataItem.class);
        job.setOutputValueClass(Text.class);

//        FileInputFormat.setInputPaths(job, new Path("/Users/linwengjing/Downloads/Java/HadoopLogs/excelInput"));
//        FileOutputFormat.setOutputPath(job, new Path("/Users/linwengjing/Downloads/Java/HadoopLogs/excelOutput"));
        FileInputFormat.setInputPaths(job, new Path("D:\\code\\HadoopLogs\\excelInput"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\code\\HadoopLogs\\excelOutput"));  // 注意：输出路径必须不存在

        job.waitForCompletion(true);
    }
}
