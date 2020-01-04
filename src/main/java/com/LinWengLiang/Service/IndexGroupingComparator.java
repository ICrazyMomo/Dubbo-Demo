package com.LinWengLiang.Service;

import com.LinWengLiang.Model.DataItem;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;


/**
 * @author linwengliang
 * @Title: IndexGroupingComparator
 * @Description: TODO
 * @date 2020/1/43:33 PM
 */
public class IndexGroupingComparator extends WritableComparator{

    public IndexGroupingComparator() {
        super(DataItem.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {

        DataItem o1 = (DataItem)a;
        DataItem o2 = (DataItem)b;

        return o1.getIndex()-o2.getIndex();
    }
}
