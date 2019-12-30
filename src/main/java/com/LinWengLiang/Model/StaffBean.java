package com.LinWengLiang.Model;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author Qutto，
 * @Title: StaffBean
 * @Description: TODO
 * @date 2019/12/2720:55
 */
public class StaffBean implements Writable {

    //公司名称
    private String companyName;
    //产品名称
    private String produceNanme;
    //供应商名称
    private String supplierName;
    //产品数量
    private int count;

    public StaffBean(){}

    public StaffBean(String companyName, String supplierName, String produceNanme,int count){
        this.companyName = companyName;
        this.supplierName =supplierName;
        this.produceNanme = produceNanme;
        this.count =count;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(this.companyName);
        out.writeUTF(this.supplierName);
        out.writeUTF(this.produceNanme);
        out.writeInt(this.count);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.companyName = in.readUTF();
        this.supplierName = in.readUTF();
        this.produceNanme = in.readUTF();
        this.count = in.readInt();
    }

    @Override
    public String toString() {
        return this.produceNanme + ","+ this.supplierName + "," + this.companyName+","+ this.count;
    }
}
