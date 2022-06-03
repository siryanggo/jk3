package com.yang.jk.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @auther yhjStart
 * @create 2022-04-03 21:57
 */
@Component
public class Uploads {
    @Autowired
    private  Yproperties yproperties;
    public  String uploadFile(MultipartFile file,String dir) throws IOException {
        if (file==null) return null;
        String lastName = FilenameUtils.getExtension(file.getOriginalFilename());
        String uuidStr = UUID.randomUUID().toString().replace("-", "");
        String newfilename =  uuidStr+"."+lastName;
        String filePaths =  dir+newfilename;
        String path =  yproperties.getUpload().getBasicPath()+dir+newfilename;
        /**
         * 创建目标文件对象
         */
        File file1 = new File(path);
        file1.deleteOnExit();
        File parentFile = file1.getParentFile();
      if (!parentFile.exists()) {
          parentFile.mkdirs();
      }
      file.transferTo(file1);
        return filePaths;
    }
}
