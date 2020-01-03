package com.LinWengLiang.Model;

import org.apache.hadoop.io.Writable;

import javax.xml.transform.Result;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author linwengliang
 * @Title: ExportItem
 * @Description: TODO
 * @date 2020/1/32:30 PM
 */
public class ExportItem implements Writable {

    private String produceName;
    private String supplierName;
    private String cityName;
    private Integer count;
    private String result;

    public void  set(String produceName, String supplierName, String cityName, Integer count) {
        this.produceName = produceName;
        this.supplierName = supplierName;
        this.cityName = cityName;
        this.count = count;
        this.result = cityName +","+count;
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

    public int getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(this.produceName);
        out.writeUTF(this.supplierName);
        out.writeUTF(this.cityName);
        out.write(this.count);
    }

    @Override
    public void readFields(DataInput in) throws IOException {

       this.produceName=in.readUTF();
       this.supplierName = in.readUTF();
       this.cityName = in.readUTF();
       this.count = in.readInt();
    }

    @Override
    public String toString() {
        return this.produceName+"/t"+this.supplierName+"/t"+this.cityName+this.count.toString();
    }
}
