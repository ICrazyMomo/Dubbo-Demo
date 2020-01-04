package com.LinWengLiang.Service;

import com.LinWengLiang.Model.DataItem;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author linwengliang
 * @Title: IndexPartitioner
 * @Description: TODO
 * @date 2020/1/43:29 PM
 */
public class IndexPartitioner extends Partitioner<DataItem,NullWritable>{
    @Override
    public int getPartition(DataItem dataItem, NullWritable nullWritable, int i) {
        return (dataItem.getIndex() & Integer.MAX_VALUE) % i;
    }
}
