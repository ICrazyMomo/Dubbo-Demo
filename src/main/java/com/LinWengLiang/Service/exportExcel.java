package com.LinWengLiang.Service;

import com.LinWengLiang.Model.StaffBean;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Qutto，
 * @Title: exportExcel
 * @Description: 到处excel文件
 * @date 2019/12/2810:13
 */
@Component
public class exportExcel implements CommandLineRunner {

    @Autowired
    private ReadTxtService readTxtService;

    @Override
    public void run(String... strings) throws Exception {

        List<StaffBean> staffBeans = readTxtService.readText("D:\\code\\output\\part-r-00000.txt");
        // 创建excel
        HSSFWorkbook wk = new HSSFWorkbook();
        // 创建一张工作表
        HSSFSheet sheet = wk.createSheet();
        // 创建第一行
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("序号");
        cell = row.createCell((short) 1);
        cell.setCellValue("抽检产品");
        cell = row.createCell((short) 2);
        cell.setCellValue("每个品类每个供应商样品抽样送检批次（次）");
        cell = row.createCell((short) 3);
        cell.setCellValue("每个品类每个供应商每批次抽检样品组数（组）");
        cell = row.createCell((short) 4);
        cell.setCellValue("供应商（标红供应商表示已达到该供应商集团计划抽检数量的1.5倍）");
        cell = row.createCell((short) 5);
        cell.setCellValue("订单系统已下单应送样数量（组）");
        cell = row.createCell((short) 6);
        cell.setCellValue("已送样数量（组）");
        cell = row.createCell((short) 7);
        cell.setCellValue("已抽样数量（组）");
        cell = row.createCell((short) 8);
        cell.setCellValue("剩余未抽样数（组）");
        cell = row.createCell((short) 9);
        cell.setCellValue("按计划数1.5倍剩余未抽样数（组）");
        cell = row.createCell((short) 10);
        cell.setCellValue("地区（“√”表示订单系统有采购订单的供应商）");
        cell = row.createCell((short) 11);
        cell.setCellValue("备注");

        // 创建第二行
        HSSFRow row2 = sheet.createRow(2);
//        for (int i = 0; i < 8; i++) {
//            cell = row.createCell(i);
//            cell.setCellValue(head0[i]);
//            cell.setCellStyle(style);
//        }



        wk.setSheetName(0,"集团到货抽检订单情况表（仅包含1-12月有采购订单的供应商）");
    }
}
