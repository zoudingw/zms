package com.zdw.zms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Author:zoudw
 * Since:JDK 7
 * Date:2019/3/20
 * Description:
 *
 * @Copyright:2018, zoudw@szinfinova.com All Rights Reserved
 */
@Controller
public class FileController {



    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        if(null == file){
            redirectAttributes.addAttribute("message","no file");
            return "redirect:/uploadStatus";
        }
        try {
            String UPLOAD_FILE_PATH = "/uploadFile";
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FILE_PATH+file.getOriginalFilename());
            Files.write(path,bytes);
            redirectAttributes.addFlashAttribute("message","success upload"+file.getOriginalFilename()+"");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }
}
