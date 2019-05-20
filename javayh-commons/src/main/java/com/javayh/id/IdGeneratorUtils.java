package com.javayh.id;

import com.javayh.exception.BaseException;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Dylan Yang
 * @Description: IdGeneratorUtils
 * @Title: IdGeneratorUtils
 * @ProjectName javayh-oauth2
 * @date 2019/5/20 19:57
 */
@Component
public class IdGeneratorUtils {

    private long workerId;   //用ip地址最后几个字节标示
    private long datacenterId = 0L; //可配置在properties中,启动时加载,此处默认先写成0
    private long sequence = 0L;
    private long workerIdBits = 8L; //节点ID长度
    //private long datacenterIdBits = 2L; //数据中心ID长度,可根据时间情况设定位数
    private long sequenceBits = 12L; //序列号12位
    private long workerIdShift = sequenceBits; //机器节点左移12位
    private long datacenterIdShift = sequenceBits + workerIdBits; //数据中心节点左移17位
    private long sequenceMask = -1L ^ (-1L << sequenceBits); //4095
    private long lastTimestamp = -1L;

    public IdGeneratorUtils() {
        workerId = 0x000000FF & getLastIP();//255
    }

    /**
     * 调用该方法，获取序列ID
     * @return
     */
    public synchronized String nextId() {
        long timestamp = currentTime();
        if (timestamp < lastTimestamp) {
            throw new BaseException(String.format("时钟向后移动。拒绝在%d毫秒内生成id", lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = nextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        long suffix = (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
        // 格式化日期  线程安全
        String datePrefix = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");
        return datePrefix + suffix + generateCellPhoneValNum();
    }

    /**
     * 比较当前时间戳和下一个时间戳，如果下一个时间戳等于或小于当前时间戳，则循环获取下个时间戳
     * 该方法主要是避免同一时间获取同一时间戳
     * @param lastTimestamp
     * @return
     */
    protected long nextMillis(long lastTimestamp) {
        long timestamp = currentTime();
        while (timestamp <= lastTimestamp) {
            timestamp = currentTime();
        }
        return timestamp;
    }

    /**
     * 获取系统当前时间戳
     * @return
     */
    protected long currentTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前本地IP
     * @return
     */
    private byte getLastIP(){
        byte lastip = 0;
        try{
            InetAddress ip = InetAddress.getLocalHost();
            byte[] ipByte = ip.getAddress();
            lastip = ipByte[ipByte.length - 1];
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return lastip;
    }

    /**
     * 获取随机数
     * @return
     */
    public static String generateCellPhoneValNum() {
        String[] beforeShuffle = new String[]{"1", "2", "3", "4", "5", "6",
                "7", "8", "9", "0"};
        List<String> list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            buffer.append(list.get(i));
        }
        String afterShuffle = buffer.toString();
        String result = afterShuffle.substring(3, 9);
        return result;
    }
}

