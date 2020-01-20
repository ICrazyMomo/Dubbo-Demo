package com.LinWengLiang.Model;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author Qutto，
 * @Title: DataItem
 * @Description: TODO
 * @date 2020/1/413:52
 */
public class DataItem implements Writable, WritableComparable<DataItem> {
    private String produceName;
    private String supplierName;
    private String cityName;
    private String count;
    private int index;

    public void set(String produceName, String supplierName, String cityName, String count,int index) {
        this.produceName = produceName;
        this.supplierName = supplierName;
        this.cityName = cityName;
        this.count = count;
        this.index = index;
    }

    public void setTwo(String produceName, String supplierName, int index) {
        this.produceName = produceName;
        this.supplierName = supplierName;
        this.index = index;
    }

    public String getProduceName() {
        return produceName;
    }

    public void setProduceName(String produceName) {
        this.produceName = produceName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(this.produceName);
        out.writeUTF(this.supplierName);
        out.writeUTF(this.cityName);
        out.writeUTF(this.count);
        out.writeInt(this.index);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.produceName = in.readUTF();
        this.supplierName = in.readUTF();
        this.cityName = in.readUTF();
        this.count = in.readUTF();
        this.index = in.readInt();
    }

    @Override
    public String toString() {
//        return this.index+"\t"+ this.produceName+"\t"+this.supplierName+"\t"+this.cityName+"\t"+this.count;
        return this.index+"\t"+ this.produceName+"\t"+this.supplierName;
    }

    @Override
    public int compareTo(DataItem o) {
        return this.getIndex()-o.getIndex()==0?this.supplierName.compareTo(o.getSupplierName()):this.getIndex()-o.getIndex();
    }
}
