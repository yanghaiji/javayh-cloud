package com.javayh.file;

import com.javayh.exception.BaseException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Dylan Yang
 * @Description: 文件下载 NIO
 * @Title: FileDownLoad
 * @ProjectName javayh-oauth2
 * @date 2019/6/9 19:05
 */
@Slf4j
public class FileDownLoad {

    public static byte[] toByteArray(String fileName) {
        File file = new File(fileName);
        if (!file.exists()){
            throw  new BaseException("File Inexistence");
        }
        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(file);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int read = channel.read(byteBuffer);
            while (read > 0){
                //do something
            }
            return byteBuffer.array();
        }catch (IOException e){
            e.printStackTrace();
            throw new BaseException("File DownLoad Error");
        }finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

