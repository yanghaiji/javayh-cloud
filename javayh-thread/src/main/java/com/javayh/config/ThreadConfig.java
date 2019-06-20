package com.javayh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Dylan Yang
 * @Description: ThreadConfig
 * @Title: ThreadConfig
 * @ProjectName javayh-oauth2
 * @date 2019/6/20 11:41
 */
@Configuration
public class ThreadConfig {

    /**
     *newFixedThreadPool
     创建一个指定工作线程数量的线程池。每当提交一个任务就创建一个工作线程，如果工作线程数量达到线程池初始的最大数，则将提交的任务存入到池队列中。

     newCachedThreadPool
     创建一个可缓存的线程池。这种类型的线程池特点是：
     1).工作线程的创建数量几乎没有限制(其实也有限制的,数目为Interger. MAX_VALUE), 这样可灵活的往线程池中添加线程。
     2).如果长时间没有往线程池中提交任务，即如果工作线程空闲了指定的时间(默认为1分钟)，则该工作线程将自动终止。终止后，如果你又提交了新的任务，则线程池重新创建一个工作线程。

     newSingleThreadExecutor
     创建一个单线程化的Executor，即只创建唯一的工作者线程来执行任务，如果这个线程异常结束，会有另一个取代它，保证顺序执行(我觉得这点是它的特色)。单工作线程最大的特点是可保证顺序地执行各个任务，并且在任意给定的时间不会有多个线程是活动的 。

     newScheduleThreadPool
     创建一个定长的线程池，而且支持定时的以及周期性的任务执行，类似于Timer。
     * @return
     */
    @Bean
    public ExecutorService getExecutorTools(){
        // 获取计算机有几个核
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(processors * 5);
        return  executorService;
    }

}

