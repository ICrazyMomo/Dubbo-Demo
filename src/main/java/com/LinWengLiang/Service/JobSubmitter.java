package com.LinWengLiang.Service;

import com.LinWengLiang.Model.StaffBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * @author linwengliang
 * @Title: JobSubmitter
 * @Description: MapReduce任务主函数
 * @date 2019/12/242:09 PM
 */

public class JobSubmitter implements CommandLineRunner  {

    public static final Logger log = LoggerFactory.getLogger(JobSubmitter.class);

    @Override
    public void run(String... args) throws Exception {
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(JobSubmitter.class);
        // 2、封装参数： 本次job所要调用的Mapper实现类、Reducer实现类
        job.setMapperClass(HadoopMapper.class);
        job.setReducerClass(HadoopReduce.class);
        // 3、封装参数：本次job的Mapper实现类、Reducer实现类产生的结果数据的key、value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(StaffBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(StaffBean.class);

        // 4、封装参数：本次job要处理的输入数据集所在路径、最终结果的输出路径
        FileInputFormat.setInputPaths(job, new Path("D:\\code\\input"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\code\\output"));  // 注意：输出路径必须不存在

        // 5、封装参数：想要启动的reduce task的数量
//        job.setNumReduceTasks(1);

        // 6、提交job给yarn
        job.waitForCompletion(true);

//        System.exit(res ? 0 : -1);
    }
}
