package com.LinWengLiang.Model;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.List;

/**
 * @author Qutto，
 * @Title: StaffBean
 * @Description: TODO
 * @date 2019/12/2720:55
 */
public class StaffBean implements Writable {

    //产品名称
    private String produceNanme;
    //供应商名称
    private String supplierName;

    private List<CityAndCountiem> items;

    public StaffBean(){}

    public List<CityAndCountiem> getItems() {
        return items;
    }

    public void setItems(List<CityAndCountiem> items) {
        this.items = items;
    }

    public String getProduceNanme() {
        return produceNanme;
    }

    public void setProduceNanme(String produceNanme) {
        this.produceNanme = produceNanme;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }


    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(this.supplierName);
        out.writeUTF(this.produceNanme);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.supplierName = in.readUTF();
        this.produceNanme = in.readUTF();
    }

//    @Override
//    public String toString() {
//        return this.produceNanme + ","+ this.supplierName + "," + this.companyName+","+ this.count;
//    }
}
