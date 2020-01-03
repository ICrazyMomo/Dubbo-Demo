import com.LinWengLiang.Model.StaffBean;
import com.LinWengLiang.Service.HadoopMapper;
import com.LinWengLiang.Service.HadoopReduce;
import com.LinWengLiang.Service.JobSubmitter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author linwengliang
 * @Title: mapReduceTest
 * @Description: TODO
 * @date 2020/1/31:47 PM
 */
public class mapReduceTest {
    public static void main(String[] args) throws Exception{
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(JobSubmitter.class);
        // 2、封装参数： 本次job所要调用的Mapper实现类、Reducer实现类
        job.setMapperClass(HadoopMapper.class);
        job.setReducerClass(HadoopReduce.class);
        // 3、封装参数：本次job的Mapper实现类、Reducer实现类产生的结果数据的key、value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(StaffBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(StaffBean.class);

        // 4、封装参数：本次job要处理的输入数据集所在路径、最终结果的输出路径
//        FileInputFormat.setInputPaths(job, new Path("D:\\code\\input"));
//        FileOutputFormat.setOutputPath(job,new Path("D:\\code\\output"));  // 注意：输出路径必须不存在
        FileInputFormat.setInputPaths(job, new Path("/Users/linwengjing/Downloads/Java/HadoopLogs/excelInput"));
        FileOutputFormat.setOutputPath(job,new Path("/Users/linwengjing/Downloads/Java/HadoopLogs/excelOuput"));  // 注意：输出路径必须不存在

        // 5、封装参数：想要启动的reduce task的数量
//        job.setNumReduceTasks(1);

        // 6、提交job给yarn
        job.waitForCompletion(true);

//        System.exit(res ? 0 : -1);
    }
}
