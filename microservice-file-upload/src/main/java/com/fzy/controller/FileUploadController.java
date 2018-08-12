package com.fzy.controller;

import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Fucai
 * @date 2018/8/10
 */
@Controller
public class FileUploadController {

  @ResponseBody
  @RequestMapping(value = "upload",method = RequestMethod.POST)
  public String uploadFile(@RequestParam(value = "file",required = true)MultipartFile file){
    try {
      byte[] bytes=file.getBytes();
      File fileToSave=new File("/Users/fuzhongyu/Desktop/logs/"+file.getOriginalFilename());
      FileCopyUtils.copy(bytes,fileToSave);
      return fileToSave.getAbsolutePath();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "";

  }

}
