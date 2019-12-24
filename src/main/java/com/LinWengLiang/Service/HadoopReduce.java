package com.LinWengLiang.Service;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author linwengliang
 * @Title: HadoopReduce
 * @Description: reduce实现类
 * @date 2019/12/242:01 PM
 */
public class HadoopReduce extends Reducer{
    @Override
    protected void reduce(Object key, Iterable values, Context context) throws IOException, InterruptedException {

        int count = 0;

        Iterator<IntWritable> iterator = values.iterator();

        while (iterator.hasNext()){
            IntWritable value = iterator.next();
            count += value.get();
        }

        context.write(key,new IntWritable(count));

    }

}
