package com.LinWengLiang.Service;

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
public class HadoopReduce extends Reducer<Text, StaffBean,Text,StaffBean>{

    @Override
    protected void reduce(Text key, Iterable<StaffBean> values, Context context) throws IOException, InterruptedException {

        int count = 0;
        String companyName = null;
        String produceNane =null;
        String supplierName =null;

        for (StaffBean staffBean :values){
            count += staffBean.getCount();
             companyName = staffBean.getCompanyName();
             produceNane = staffBean.getProduceNanme();
             supplierName = staffBean.getSupplierName();
        }

        context.write(new Text(String.valueOf(count)),new StaffBean(companyName,supplierName,produceNane,count));
    }
}
