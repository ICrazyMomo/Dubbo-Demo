package com.LinWengLiang.Service;


import org.apache.hadoop.mapreduce.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author linwengliang
 * @Title: HadoopMapper
 * @Description: TODO
 * @date 2019/12/2311:32 AM
 */
@Component
public class HadoopMapper extends Mapper<Text, VALUEIN, KEYOUT, VALUEOUT> {
}
