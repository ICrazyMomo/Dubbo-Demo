package com.LinWengLiang.Service.Impl;

import com.LinWengLiang.Model.CityAndCountiem;
import com.LinWengLiang.Model.StaffBean;
import com.LinWengLiang.Service.ReadTxtService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Qutto，
 * @Title: readTxtServiceImpl
 * @Description: TODO
 * @date 2019/12/2810:06
 */

@Service
public class ReadTxtServiceImpl implements ReadTxtService {


    @Override
    public List<StaffBean> readText(String pathname) throws IOException{
        ArrayList<StaffBean> beanArrayList = new ArrayList<StaffBean>();
        FileReader reader = new FileReader(pathname);
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        String line;
        while ((line = br.readLine()) != null) {
            // 一次读入一行数据
            String[] strs = line.split(",");
            String[]citys = strs[2].substring(1).split("\t");
            StaffBean staffBean = new StaffBean();
            staffBean.setProduceNanme(strs[0]);
            staffBean.setSupplierName(strs[1]);
            ArrayList<CityAndCountiem> countiems = new ArrayList<CityAndCountiem>();
            for (String str :citys){
                CityAndCountiem cityAndCountiem = new CityAndCountiem();
                cityAndCountiem.set(str.substring(0,2),str.substring(2));
                countiems.add(cityAndCountiem);
            }
            staffBean.setItems(countiems);
            beanArrayList.add(staffBean);
        }

        return beanArrayList;
    }
}
