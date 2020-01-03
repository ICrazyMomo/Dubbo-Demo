import com.LinWengLiang.Model.CityAndCountiem;
import com.LinWengLiang.Model.StaffBean;
import com.LinWengLiang.Service.ReadTxtService;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Qutto，
 * @Title: excelTest
 * @Description: TODO
 * @date 2019/12/2810:17
 */
public class excelTest {

    @Autowired
    private  ReadTxtService readTxtService ;

    @Test
    public void testExcel2() throws IOException {
        List<StaffBean> staffBeans = this.getList();

        //创建工作薄对象
        HSSFWorkbook workbook = new HSSFWorkbook();//这里也可以设置sheet的Name
        //创建工作表对象
        HSSFSheet sheet = workbook.createSheet();
        //创建工作表的行
        // 创建第一行的第一个单元格
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell((short) 0);
        cell0.setCellValue("集团到货抽检送样明细表");
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,29));

        HSSFRow row1 = sheet.createRow(1);
        HSSFCell cell = row1.createCell((short) 0);
        cell.setCellValue("序号");
        sheet.addMergedRegion(new CellRangeAddress(1,3,0,0));

        cell = row1.createCell((short) 1);
        cell.setCellValue("抽检产品");
        sheet.addMergedRegion(new CellRangeAddress(1,3,1,1));

        cell = row1.createCell((short) 2);
        cell.setCellValue("每个品类每个供应商样品抽样送检批次（次）");
        sheet.addMergedRegion(new CellRangeAddress(1,3,2,2));

        cell = row1.createCell((short) 3);
        cell.setCellValue("每个品类每个供应商每批次抽检样品组数（组）");
        sheet.addMergedRegion(new CellRangeAddress(1,3,3,3));

        cell = row1.createCell((short) 4);
        cell.setCellValue("供应商（标红供应商表示已达到该供应商集团计划抽检数量的1.5倍）");
        sheet.addMergedRegion(new CellRangeAddress(1,3,4,4));

        cell = row1.createCell((short) 5);
        cell.setCellValue("订单系统已下单应送样数量（组）");
        sheet.addMergedRegion(new CellRangeAddress(1,3,5,5));

        cell = row1.createCell((short) 6);
        cell.setCellValue("已送样数量（组）");
        sheet.addMergedRegion(new CellRangeAddress(1,3,6,6));

        cell = row1.createCell((short) 7);
        cell.setCellValue("已抽样数量（组）");
        sheet.addMergedRegion(new CellRangeAddress(1,3,7,7));

        cell = row1.createCell((short) 8);
        cell.setCellValue("剩余未抽样数（组）");
        sheet.addMergedRegion(new CellRangeAddress(1,3,8,8));

        cell = row1.createCell((short) 9);
        cell.setCellValue("按计划数1.5倍剩余未抽样数（组）");
        sheet.addMergedRegion(new CellRangeAddress(1,3, 9, 9));

        cell = row1.createCell((short) 10);
        cell.setCellValue("地区（“√”表示订单系统有采购订单的供应商）");
        sheet.addMergedRegion(new CellRangeAddress(1,1, 10, 27));

        cell = row1.createCell((short) 28);
        cell.setCellValue("备注");
        String[] headCity = new String[] { "","","","","","","","","","","福州", "福州", "厦门", "厦门", "宁德", "宁德",
                "莆田", "莆田" ,"泉州","泉州","漳州","漳州","龙岩","龙岩","三明","三明","南平","南平"};
        // 创建第二行
//        sheet.addMergedRegion(new CellRangeAddress(1, 1, 10, 11));
        HSSFRow row2 = sheet.createRow(2);
        for (int i = 10; i < 28; i++) {
            cell = row2.createCell(i);
            cell.setCellValue(headCity[i]);
        }
        //对应excel中的行和列，下表从0开始{"开始行,结束行,开始列,结束列"}
        String[] headCityNum = new String[] { "2,2,10,11", "2,2,12,13","2,2,14,15","2,2,16,17","2,2,18,19","2,2,20,21","2,2,22,23","2,2,24,25","2,2,26,27","2,2,28,29","2,2,30,31","2,2,32,33"};
        //动态合并单元格
        for (int i = 0; i < headCityNum.length; i++) {
            String[] temp = headCityNum[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            sheet.addMergedRegion(new CellRangeAddress(startrow, overrow,
                    startcol, overcol));
        }
        String[] headOrder = new String[] { "","","","","","","","","","","订单情况", "抽样情况","订单情况", "抽样情况","订单情况", "抽样情况","订单情况", "抽样情况","订单情况", "抽样情况","订单情况", "抽样情况","订单情况", "抽样情况","订单情况", "抽样情况","订单情况", "抽样情况" };
        HSSFRow row3 = sheet.createRow(3);
        for (int i = 0; i < 28; i++) {
            cell = row3.createCell(i);
            cell.setCellValue(headOrder[i]);
        }
        workbook.setSheetName(0,"集团到货抽检订单情况表（仅包含1-12月有采购订单的供应商）");
//        this.setExcellStyle(workbook,sheet);
       this.setCell(staffBeans,sheet);
        //文档输出
        FileOutputStream out = new FileOutputStream("D:\\code\\exportExcel/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() + ".xls");
        workbook.write(out);
        out.close();
    }

    private HSSFCell setCell(List<StaffBean> staffBeans,HSSFSheet sheet){
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell((short) 0);
        String outData[] = {"序号"};
        for (int i = 0; i < staffBeans.size(); i++){
            row = sheet.createRow(i + 4);
            for (int j = 0;j<outData.length;j++){
                StaffBean staffBean = staffBeans.get(i);
                cell = row.createCell(j);
//                if (outData[j].equals("序号")){
//                    cell.setCellValue(i+1);
//                }else if (outData[j].equals("供应商")){
//                    cell.setCellValue(staffBean.getSupplierName());
//                }else if (outData[j].equals("按计划数1.5倍剩余未抽样数")){
//                    cell.setCellValue(staffBean.getCount());
//                }else if (outData[j].equals("地区")){
//                    cell.setCellValue(staffBean.getCompanyName());
//                }
                if (outData[j].equals("地区")){
//                    cell.setCellValue(staffBean.getCompanyName());
                }
            }
        }
        return cell;
    }

    private void setExcellStyle(HSSFWorkbook workbook,HSSFSheet sheet){
        // 表头标题样式
        HSSFFont headfont = workbook.createFont();
        headfont.setFontName("宋体");
        headfont.setFontHeightInPoints((short) 22);// 字体大小
        HSSFCellStyle headstyle = workbook.createCellStyle();
        headstyle.setFont(headfont);
        headstyle.setAlignment(HorizontalAlignment.CENTER);// 左右居中
        headstyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
        headstyle.setLocked(true);
        // 表头时间样式
        HSSFFont datefont = workbook.createFont();
        datefont.setFontName("宋体");
        datefont.setFontHeightInPoints((short) 16);// 字体大小
        HSSFCellStyle datestyle = workbook.createCellStyle();
        datestyle.setFont(datefont);
        datestyle.setAlignment(HorizontalAlignment.CENTER);// 左右居中
        datestyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
        datestyle.setLocked(true);
        // 列名样式
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 16);// 字体大小
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN); //下边框
        style.setBorderLeft(BorderStyle.THIN);//左边框
        style.setBorderTop(BorderStyle.THIN);//上边框
        style.setBorderRight(BorderStyle.THIN);//右边框
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);// 左右居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
        style.setLocked(true);
        // 普通单元格样式（中文）
        HSSFFont font2 = workbook.createFont();
        font2.setFontName("宋体");
        font2.setFontHeightInPoints((short) 18);
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setBorderBottom(BorderStyle.THIN); //下边框
        style2.setBorderLeft(BorderStyle.THIN);//左边框
        style2.setBorderTop(BorderStyle.THIN);//上边框
        style2.setBorderRight(BorderStyle.THIN);//右边框
        style2.setFont(font2);
        style2.setAlignment(HorizontalAlignment.CENTER);// 左右居中
        style2.setWrapText(true); // 换行
        style2.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
        // 设置列宽  （第几列，宽度）
        sheet.setColumnWidth( 0, 1600);
        sheet.setColumnWidth( 1, 3600);
        sheet.setColumnWidth( 2, 2800);
        sheet.setColumnWidth( 3, 2800);
        sheet.setColumnWidth( 4, 2800);
        sheet.setColumnWidth( 5, 2800);
        sheet.setColumnWidth( 6, 4500);
        sheet.setColumnWidth( 7, 3600);
        sheet.setDefaultRowHeight((short)360);//设置行高
    }

    //创建文档摘要信息
    @Test
    public void testExcel3() throws IOException {
        //创建HSSFWorkbook工作薄对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建HSSFSheet对象
        HSSFSheet sheet = workbook.createSheet("sheet1");
        //创建行的单元格，从0开始
        HSSFRow row = sheet.createRow(0);
        //创建单元格,从0开始
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("a");
        //一下为简写
        row.createCell(1).setCellValue("aa");
        row.createCell(2).setCellValue("aaa");
        row.createCell(3).setCellValue("aaaa");

        //创建文档信息
        workbook.createInformationProperties();
        //获取DocumentSummaryInformation对象
        DocumentSummaryInformation documentSummaryInformation = workbook.getDocumentSummaryInformation();
        documentSummaryInformation.setCategory("类别:Excel文件");//类别
        documentSummaryInformation.setManager("管理者:王军");//管理者
        documentSummaryInformation.setCompany("公司:Action");//公司

        //文档输出
        FileOutputStream out = new FileOutputStream("D:\\code\\exportExcel/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() + ".xls");
        workbook.write(out);
        out.close();
    }

    @Test
    public void testExcel6() throws IOException {
        //创建Excel工作薄对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建Excel工作表对象
        HSSFSheet sheet = workbook.createSheet("wj");
        //创建行的单元格，从0开始
        HSSFRow row = sheet.createRow(0);
        //创建单元格
        HSSFCell cell = row.createCell(0);
        //设置值
        cell.setCellValue(new Date());
        //创建单元格样式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
        cell.setCellStyle(style);
        //设置保留2位小数--使用Excel内嵌的格式
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue(12.3456789);
        style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        cell1.setCellStyle(style);
        //设置货币格式--使用自定义的格式
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue(12345.6789);
        style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("￥#,##0"));
        cell2.setCellStyle(style);
        //设置百分比格式--使用自定义的格式
        HSSFCell cell3 = row.createCell(3);
        cell3.setCellValue(0.123456789);
        style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("0.00%"));
        cell3.setCellStyle(style);
        //设置中文大写格式--使用自定义的格式
        HSSFCell cell4 = row.createCell(4);
        cell4.setCellValue(12345);
        style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("[DbNum2][$-804]0"));
        cell4.setCellStyle(style);
        //设置科学计数法格式--使用自定义的格式
        HSSFCell cell5 = row.createCell(5);
        cell5.setCellValue(12345);
        style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("0.00E+00"));
        cell5.setCellStyle(style);

        //文档输出
        FileOutputStream out = new FileOutputStream("D:\\code\\exportExcel/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() + ".xls");
        workbook.write(out);
        out.close();
    }

    //合并单元格
    @Test
    public void testExcel7() throws IOException {
        //创建Excel工作薄对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建Excel工作表对象
        HSSFSheet sheet = workbook.createSheet("wj");
        //创建行的单元格，从0开始
        HSSFRow row = sheet.createRow(0);
        //创建单元格
        HSSFCell cell = row.createCell(0);
        //设置值
        cell.setCellValue(new Date());
        //合并列
        cell.setCellValue("合并列");
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 5);
        sheet.addMergedRegion(region);
        //合并行
        HSSFCell cell1 = row.createCell(6);
        cell1.setCellValue("合并行");
        region = new CellRangeAddress(0, 5, 6, 6);
        sheet.addMergedRegion(region);

        //文档输出
        FileOutputStream out = new FileOutputStream("D:\\code\\exportExcel/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() + ".xls");
        workbook.write(out);
        out.close();
    }

    private List<StaffBean> getList()throws IOException{
        String pathname = "D:\\code\\output\\part-r-00000.txt"; // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
        //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
        //不关闭文件会导致资源的泄露，读写文件都同理
        //Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271
        FileReader reader = new FileReader(pathname);
        BufferedReader br = new BufferedReader(reader);// 建立一个对象，它把文件内容转成计算机能读懂的语言

        String line;
        ArrayList<StaffBean> beanArrayList = new ArrayList<StaffBean>();
        //网友推荐更加简洁的写法
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
//        List<StaffBean> staffBeans = readTxtService.readText(pathname);
        return beanArrayList;
    }
}
