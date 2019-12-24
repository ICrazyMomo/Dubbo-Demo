package com.LinWengLiang.Service;

import org.apache.hadoop.conf.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author linwengliang
 * @Title: JobSubmitter
 * @Description: MapReduce任务主函数
 * @date 2019/12/242:09 PM
 */
@Component
public class JobSubmitter implements CommandLineRunner{

    public static final Logger log = LoggerFactory.getLogger(JobSubmitter.class);
    @Override
    public void run(String... args) throws Exception {

        // 在代码中设置JVM系统参数，用于给job对象来获取访问HDFS的用户身份
        System.setProperty("HADOOP_USER_NAME", "root");

        Configuration conf = new Configuration();
        // 1、设置job运行时要访问的默认文件系统
        // 2、设置job提交到哪去运行
        conf.set("mapreduce.framework.name", "yarn");
        conf.set("yarn.resourcemanager.hostname", "");
        // 3、如果要从windows系统上运行这个job提交客户端程序，则需要加这个跨平台提交的参数
//        conf.set("mapreduce.app-submission.cross-platform","true");

    }
}
