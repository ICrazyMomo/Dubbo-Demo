import com.LinWengLiang.Model.StaffBean;
import com.LinWengLiang.Service.ReadTxtService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Qutto，
 * @Title: readTxtTest
 * @Description: TODO
 * @date 2019/12/289:30
 */
public class readTxtTest {
    @Autowired
    private ReadTxtService readTxtService;

    @Test
    public  void readFile() throws IOException {
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
                String str  = line.substring(2);
                String[] strs = str.split(",");
                StaffBean staffBean = new StaffBean();
                staffBean.setProduceNanme(strs[0]);
                staffBean.setSupplierName(strs[1]);
                staffBean.setCompanyName(strs[2]);
                staffBean.setCount(Integer.parseInt(strs[3]));
                beanArrayList.add(staffBean);
            }
//        List<StaffBean> staffBeans = readTxtService.readText(pathname);
        System.out.println(beanArrayList.size());
    }
}
