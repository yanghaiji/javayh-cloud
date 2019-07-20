package com.javayh.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * @author Dylan Yang
 * @Description: TODO
 * @Title: LogOut
 * @ProjectName javayh-cloud
 * @date 2019/7/20 16:55
 */
@Slf4j
public class LogOut {

    public static void logOut(CorrelationData correlationData, boolean isSendSuccess, String arg){
        log.info("confirm id : {}" ,correlationData.getId());
        if (isSendSuccess) {
            log.info("confirm isSendSuccess :{}" ,isSendSuccess);
        } else {
            log.info("confirm falid : {}" + arg);
        }
    }
}

