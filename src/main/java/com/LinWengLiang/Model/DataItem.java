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
public class DataItem implements Writable {
    private String produceName;
    private String supplierName;
    private String cityName;
    private String count;
    private String index;

    public void set(String produceName, String supplierName, String cityName, String count,String index) {
        this.produceName = produceName;
        this.supplierName = supplierName;
        this.cityName = cityName;
        this.count = count;
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

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(this.produceName);
        out.writeUTF(this.supplierName);
        out.writeUTF(this.cityName);
        out.writeUTF(this.count);
        out.writeUTF(this.index);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.produceName = in.readUTF();
        this.supplierName = in.readUTF();
        this.cityName = in.readUTF();
        this.count = in.readUTF();
        this.index = in.readUTF();
    }

    @Override
    public String toString() {
        return this.getCityName()+"/t"+this.getProduceName()+"/t"+this.getSupplierName()+"/t"+this.getCount();
    }

}
