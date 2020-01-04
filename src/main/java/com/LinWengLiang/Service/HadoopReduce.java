package com.LinWengLiang.Service;

import com.LinWengLiang.Model.DataItem;
import com.LinWengLiang.Model.StaffBean;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author linwengliang
 * @Title: HadoopReduce
 * @Description: reduce实现类
 * @date 2019/12/242:01 PM
 */
public class HadoopReduce extends Reducer<Text, Text,Text,Text>{

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        int count = 0;
        for (Text a :values){
            count += Integer.parseInt(a.toString());
        }
        context.write(key,new Text(String.valueOf(count)));
    }
}
