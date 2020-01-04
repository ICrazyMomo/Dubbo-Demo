package com.LinWengLiang.Service;

import com.LinWengLiang.Model.DataItem;
import com.LinWengLiang.Model.StaffBean;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
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
public class HadoopReduce extends Reducer<DataItem, NullWritable,DataItem,NullWritable>{

    @Override
    protected void reduce(DataItem key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        for (NullWritable v : values) {
            context.write(key, v);
        }
    }
}
