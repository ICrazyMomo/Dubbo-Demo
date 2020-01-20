import com.LinWengLiang.Model.CityAndCountiem;
import com.LinWengLiang.Model.StaffBean;
import com.LinWengLiang.Service.ReadTxtService;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
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

        this.setExcellStyle(workbook,sheet,row1,0,"序号",500);

        this.setExcellStyle(workbook,sheet,row1,1,"抽检产品",3000);

        this.setExcellStyle(workbook,sheet,row1,2,"每个品类每个供应商样品抽样送检批次（次）",2000);

        this.setExcellStyle(workbook,sheet,row1,3,"每个品类每个供应商每批次抽检样品组数（组）",2000);

        this.setExcellStyle(workbook,sheet,row1,4,"供应商（标红供应商表示已达到该供应商集团计划抽检数量的1.5倍）",2000);

        this.setExcellStyle(workbook,sheet,row1,5,"订单系统已下单应送样数量（组）",2000);

        this.setExcellStyle(workbook,sheet,row1,6,"已送样数量（组）",2000);

        this.setExcellStyle(workbook,sheet,row1,7,"已抽样数量（组）",2000);

        this.setExcellStyle(workbook,sheet,row1,8,"剩余未抽样数（组）",2000);

        this.setExcellStyle(workbook,sheet,row1,9,"按计划数1.5倍剩余未抽样数（组）",2000);

        cell = row1.createCell((short) 10);
        cell.setCellValue("地区（“√”表示订单系统有采购订单的供应商）");
        CellStyle cellStyle = workbook.createCellStyle(); // 创建单元格样式
        cellStyle.setAlignment(HorizontalAlignment.CENTER);  // 设置单元格水平方向对其方式
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 设置单元格垂直方向对其方式
        cell.setCellStyle(cellStyle); // 设置单元格样式
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
            CellStyle cityCellStyle = workbook.createCellStyle(); // 创建单元格样式
            cityCellStyle.setAlignment(HorizontalAlignment.CENTER);  // 设置单元格水平方向对其方式
            cityCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 设置单元格垂直方向对其方式
            cell.setCellStyle(cityCellStyle); // 设置单元格样式
            sheet.setColumnWidth( i, 1200);
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
        for (int i = 10; i < 28; i++) {
            cell = row3.createCell(i);
            cell.setCellValue(headOrder[i]);
            CellStyle cs = workbook.createCellStyle(); // 创建单元格样式
            cs.setAlignment(HorizontalAlignment.CENTER);  // 设置单元格水平方向对其方式
            cs.setVerticalAlignment(VerticalAlignment.CENTER); // 设置单元格垂直方向对其方式
            cs.setWrapText(true);
            cell.setCellStyle(cs); // 设置单元格样式
            sheet.setColumnWidth( i,900);
//            sheet.setDefaultRowHeight((short)10000);//设置行高
        }
        workbook.setSheetName(0,"集团到货抽检订单情况表（仅包含1-12月有采购订单的供应商）");
//        this.setExcellStyle(workbook,sheet);
        this.setCell(staffBeans,sheet,workbook);
        //文档输出
        FileOutputStream out = new FileOutputStream("D:\\code\\exportExcel/" + "分公司抽检情况-"+new SimpleDateFormat("MM").format(new Date()).toString() + ".xls");
        workbook.write(out);
        out.close();
    }

    //设置单元格
    private HSSFCell setCell(List<StaffBean> staffBeans,HSSFSheet sheet,HSSFWorkbook wb){
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell((short) 0);
        String outData[] = {"序号","抽检产品","每个品类每个供应商样品抽样送检批次（次）","每个品类每个供应商每批次抽检样品组数（组）","供应商（标红供应商表示已达到该供应商集团计划抽检数量的1.5倍）","订单系统已下单应送样数量（组）","已送样数量（组）","已抽样数量（组）","剩余未抽样数（组）","按计划数1.5倍剩余未抽样数（组）","福州订单情况","福州抽样情况","厦门订单情况","厦门抽样情况","宁德订单情况","宁德抽样情况","莆田订单情况","莆田抽样情况","泉州订单情况","泉州抽样情况","漳州订单情况","漳州抽样情况","龙岩订单情况","龙岩抽样情况","三明订单情况","三明抽样情况","南平订单情况","南平抽样情况","备注"};
        //上一个产品名称
        String tmpProduceName = "";
        //上一次合并的结束行
        int startIndex = 4;
        int endIndex = 4;
        int num =1;
        for (int i = 0; i < staffBeans.size(); i++){
            StaffBean staffBean = staffBeans.get(i);
            row = sheet.createRow(i + 4);
            if (i==0){//第一条数据，就是第5行开始
                tmpProduceName = staffBean.getProduceNanme();
                //第二条数据开始之后
            }else if (i>0 & StringUtils.isNotBlank(staffBean.getProduceNanme())){
                //判断新的一行的产品名称跟上一行是否相等(存在有不相同的行)
//                startIndex=endIndex+1;
                if (!tmpProduceName.equals(staffBean.getProduceNanme())){
                    tmpProduceName = staffBean.getProduceNanme();
                    num += 1;
                    if (startIndex!=endIndex){
                        this.mergerCell(sheet,startIndex,endIndex);
                    }
                    startIndex=endIndex+1;
                }
                //如果全是相同的行(判断最后一行和第一行是否相同，如果相同就全部合并)
                else{
                    if (i==staffBeans.size()-1){
//                        startIndex=endIndex+1;
                        if (tmpProduceName.equals(staffBean.getProduceNanme()) &  StringUtils.isNotBlank(tmpProduceName)){
                            this.mergerCell(sheet,startIndex,endIndex);
                        }
                    }
                }
                endIndex = endIndex+1;
            }
            for (int j = 0;j<outData.length;j++){
                cell = row.createCell(j);
                if (outData[j].equals("序号")){
                    cell.setCellValue(num);
                    this.setDataCellStyle(wb,sheet,cell,j,1000);
                }
                else if(outData[j].equals("抽检产品")){
                    cell.setCellValue(staffBean.getProduceNanme());
                    this.setDataCellStyle(wb,sheet,cell,j,3500);
                }
                else if(outData[j].equals("每个品类每个供应商样品抽样送检批次（次）")){

                }
                else if(outData[j].equals("每个品类每个供应商每批次抽检样品组数（组）")){

                }
                else if(outData[j].equals("供应商（标红供应商表示已达到该供应商集团计划抽检数量的1.5倍）")){
                    cell.setCellValue(staffBean.getSupplierName());
                }
                else if(outData[j].equals("订单系统已下单应送样数量（组）")){

                }
                else if(outData[j].equals("已送样数量（组）")){

                }
                else if(outData[j].equals("已抽样数量（组）")){

                }
                else if(outData[j].equals("剩余未抽样数（组）")){

                }
                else if(outData[j].equals("按计划数1.5倍剩余未抽样数（组）")){

                }
                if(outData[j].equals("福州订单情况")){

                }
                else if(outData[j].equals("福州抽样情况")){
                    this.setCityCell(staffBean.getItems(),"福州",cell);
                    this.setDataCellStyle(wb,sheet,cell,j,900);
                }
                else if(outData[j].equals("厦门订单情况") ){

                }
                else if(outData[j].equals("厦门抽样情况")){
                    this.setCityCell(staffBean.getItems(),"厦门",cell);
                    this.setDataCellStyle(wb,sheet,cell,j,900);
                }
                else if(outData[j].equals("宁德订单情况")){

                }
                else if(outData[j].equals("宁德抽样情况")){
                    this.setCityCell(staffBean.getItems(),"宁德",cell);
                    this.setDataCellStyle(wb,sheet,cell,j,900);
                }
                else if(outData[j].equals("莆田订单情况")){

                }
                else if(outData[j].equals("莆田抽样情况")){
                    this.setCityCell(staffBean.getItems(),"莆田",cell);
                    this.setDataCellStyle(wb,sheet,cell,j,900);
                }
                else if(outData[j].equals("泉州订单情况")){

                }
                else if(outData[j].equals("泉州抽样情况")){
                    this.setCityCell(staffBean.getItems(),"泉州",cell);
                    this.setDataCellStyle(wb,sheet,cell,j,900);
                }
                else if(outData[j].equals("漳州订单情况")){

                }
                else if(outData[j].equals("漳州抽样情况")){
                    this.setCityCell(staffBean.getItems(),"漳州",cell);
                    this.setDataCellStyle(wb,sheet,cell,j,900);
                }
                else if(outData[j].equals("龙岩订单情况")){

                }
                else if(outData[j].equals("龙岩抽样情况")){
                    this.setCityCell(staffBean.getItems(),"龙岩",cell);
                    this.setDataCellStyle(wb,sheet,cell,j,900);
                }
                else if(outData[j].equals("三明订单情况")){

                }
                else if(outData[j].equals("三明抽样情况")){
                    this.setCityCell(staffBean.getItems(),"三明",cell);
                    this.setDataCellStyle(wb,sheet,cell,j,900);
                }
                else if(outData[j].equals("南平订单情况")){

                }
                else if(outData[j].equals("南平抽样情况")){
                    this.setCityCell(staffBean.getItems(),"南平",cell);
                    this.setDataCellStyle(wb,sheet,cell,j,900);
                }

            }
        }
        return cell;
    }

    //合并单元格
    private void mergerCell(HSSFSheet sheet,int startIdex,int endIndex){
        CellRangeAddress region1 = new CellRangeAddress(startIdex,endIndex, 0, 0);
        sheet.addMergedRegion(region1);
        CellRangeAddress region2 = new CellRangeAddress(startIdex, endIndex, 1, 1);
        sheet.addMergedRegion(region2);
        CellRangeAddress region3 = new CellRangeAddress(startIdex, endIndex, 2, 2);
        sheet.addMergedRegion(region3);
    }

    //设置地市抽样情况
    private void setCityCell(List<CityAndCountiem> items,String cityName, HSSFCell cell){
        for (CityAndCountiem item:items) {
            if (item.getCityName().equals(cityName)){
                cell.setCellValue(Integer.parseInt(item.getCount()));
            }
        }
    }

    //创建表头
    private void setExcellStyle(HSSFWorkbook wb,HSSFSheet sheet, HSSFRow row,int column,String title,int width) {
        HSSFCell cell = row.createCell((short)column);
        cell.setCellValue(title);
        sheet.addMergedRegion(new CellRangeAddress(1,3,column,column));
        CellStyle cellStyle = wb.createCellStyle(); // 创建单元格样式
        cellStyle.setAlignment(HorizontalAlignment.CENTER);  // 设置单元格水平方向对其方式
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 设置单元格垂直方向对其方式
        cellStyle.setWrapText(true);//自动换行
        cell.setCellStyle(cellStyle); // 设置单元格样式
        sheet.setColumnWidth(column,width);
        sheet.setDefaultRowHeight((short)3000);//设置行高
    }

    //设置单元格样式
    private void setDataCellStyle(HSSFWorkbook wb,HSSFSheet sheet, HSSFCell cell,int index, int width){
        CellStyle cs = wb.createCellStyle(); // 创建单元格样式
        cs.setAlignment(HorizontalAlignment.CENTER);  // 设置单元格水平方向对其方式
        cs.setVerticalAlignment(VerticalAlignment.CENTER); // 设置单元格垂直方向对其方式
        cs.setWrapText(true);
        cell.setCellStyle(cs); // 设置单元格样式
        sheet.setColumnWidth(index,width);
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
        region = new CellRangeAddress(5, 6, 6, 6);
        sheet.addMergedRegion(region);
        HSSFCell cell11 = row.createCell(6);
        cell11.setCellValue("合并行");
        region = new CellRangeAddress(5, 8, 6, 6);
        sheet.addMergedRegion(region);


        //文档输出
        FileOutputStream out = new FileOutputStream("D:\\code\\exportExcel/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() + ".xls");
        workbook.write(out);
        out.close();
    }

    private List<StaffBean> getList()throws IOException{
        String pathname = "D:\\code\\HadoopLogs\\excelOutput/part-r-00000"; // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
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
            String[] strs = line.split("\t");
            List<String>citys = new ArrayList<String>();
            for (int i =0;i<strs.length;i++){
                if (i>2){
                    citys.add(strs[i]);
                }
            }
            StaffBean staffBean = new StaffBean();
            staffBean.setProduceNanme(strs[1]);
            staffBean.setSupplierName(strs[2]);
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
