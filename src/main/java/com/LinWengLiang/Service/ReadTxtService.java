package com.LinWengLiang.Service;

import com.LinWengLiang.Model.StaffBean;

import java.io.IOException;
import java.util.List;

/**
 * @author Qutto，
 * @Title: readTxtService
 * @Description: 读取excel
 * @date 2019/12/2810:06
 */
public interface ReadTxtService {
    public List<StaffBean> readText(String pathname) throws IOException;
}
