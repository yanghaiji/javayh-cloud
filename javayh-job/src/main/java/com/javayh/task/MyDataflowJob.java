package com.javayh.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dylan Yang
 * @Description: Dataflow类型用于处理数据流，需实现DataflowJob接口。
 *               该接口提供2个方法可供覆盖，分别用于抓取(fetchData)和处理(processData)数据。
 * @Title: MyDataflowJob
 * @ProjectName javayh-oauth2
 * @date 2019/7/3 10:22
 */
public class MyDataflowJob implements DataflowJob {
    /**
     * 流式处理
     *
     * 可通过DataflowJobConfiguration配置是否流式处理。
     *
     * 流式处理数据只有fetchData方法的返回值为null或集合长度为空时，作业才停止抓取，
     * 否则作业将一直运行下去； 非流式处理数据则只会在每次作业执行过程中执行一次fetchData方法
     * 和processData方法，随即完成本次作业。
     *
     * 如果采用流式作业处理方式，建议processData处理数据后更新其状态，
     * 避免fetchData再次抓取到，从而使得作业永不停止。 流式数据处理参照TbSchedule设计，
     * 适用于不间歇的数据处理。
     */
    @Override
    public List fetchData(ShardingContext context) {
        switch (context.getShardingItem()) {
            case 0:
                List data1 = new ArrayList();;// get data from database by sharding item 0
                return data1;
            case 1:
                List data2 = new ArrayList();// get data from database by sharding item 1
                return data2;
            case 2:
                List data3 = new ArrayList();; // get data from database by sharding item 2
                return data3;
            // case n: ...
        }
        return null;
    }

    @Override
    public void processData(ShardingContext shardingContext, List data) {
        // process data
        // ...
    }

}





