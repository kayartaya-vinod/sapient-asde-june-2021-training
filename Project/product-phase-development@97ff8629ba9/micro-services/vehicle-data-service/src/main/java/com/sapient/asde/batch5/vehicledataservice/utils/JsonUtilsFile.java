package com.sapient.asde.batch5.vehicledataservice.utils;

import org.springframework.web.multipart.MultipartFile;

public class JsonUtilsFile {
    

    public static boolean isJsonFile(MultipartFile file) {
        String extension;
        try{
       extension = file.getOriginalFilename().split("\\.")[1];
    }
    catch(Exception e)
    {
        return false;
    }

        if (!extension.equals("json")) {
            return false;
        }

        return true;
    }
}
