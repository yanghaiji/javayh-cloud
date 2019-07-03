package com.javayh.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.mchange.v2.beans.swing.TestBean;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dylan Yang
 * @Description: 自定义SimpleJob
 * @Title: MyElasticJob
 * @ProjectName javayh-oauth2
 * @date 2019/7/3 10:17
 */
@Slf4j
public class MyElasticJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        switch (shardingContext.getShardingItem()) {
            case 0:
                // do something by sharding item 0
                break;
            case 1:
                // do something by sharding item 1
                break;
            case 2:
                // do something by sharding item 2
                break;
            // case n: ...
        }
    }
}

