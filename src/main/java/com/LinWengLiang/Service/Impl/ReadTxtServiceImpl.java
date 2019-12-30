package com.LinWengLiang.Service.Impl;

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
                String str  = line.substring(2);
                String[] strs = str.split(",");
                StaffBean staffBean = new StaffBean();
                staffBean.setProduceNanme(strs[0]);
                staffBean.setSupplierName(strs[1]);
                staffBean.setCompanyName(strs[2]);
                staffBean.setCount(Integer.parseInt(strs[3]));
                beanArrayList.add(staffBean);
            }

        return beanArrayList;
    }
}
