package com.javayh.file;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Dylan Yang
 * @Description: FileUpload
 * @Title: FileUpload
 * @ProjectName javayh-oauth2
 * @date 2019/6/1 15:55
 */
public class FileUpload {

    public static List<String> uploadFilesCover(MultipartHttpServletRequest request,String filePath){
        List<MultipartFile> files = request.getFiles("file");
        Set<String> listName = new HashSet<>();
        File file = new File(filePath);
        // 获得该文件夹内的所有文件
        File[] array = file.listFiles();
        for(int i=0;i<array.length;i++){
            if(array[i].isFile()){
                String name = array[i].getName();
                listName.add(name);
            }
        }
        List<String> listSize = new ArrayList<>();
        for (MultipartFile multipartFile : files){
            String originalFilename = multipartFile.getOriginalFilename();
            boolean contains = listName.contains(originalFilename);
            if(contains){
                listSize.add(originalFilename);
            }
        }
        return listSize;
    }



    public static String uploadFilesUitl(MultipartHttpServletRequest request, String filePath){
        List<MultipartFile> files = request.getFiles("file");
        if (files.size()>0){
            for (int i=0;i<files.size();i++){
                String originalFilename = files.get(i).getOriginalFilename();
                String path = filePath + originalFilename;
                File dest = new File(path);
                // 检测是否存在目录
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();// 新建文件夹
                }
                try {
                    files.get(i).transferTo(dest);// 文件写入
                } catch (IOException e) {
                    e.printStackTrace();
                    return "失败"+e.getMessage().length();
                }
            }
            return "上传成功";
        }
        return "上传失败!";
    }
}

