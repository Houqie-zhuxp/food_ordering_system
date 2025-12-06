package com.rabbiter.ordering.controller;

import cn.hutool.core.io.FileUtil;
import com.rabbiter.ordering.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/files")
public class FileController {
    @Value("${server.port}")
    private String port;
    // 表示本地磁盘文件的存储路径  D:\知识星球\项目训练营\项目\在线点餐系统\脚手架\canteen\files\
    private static final String filePath = System.getProperty("user.dir") + "/files/";

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        if (!FileUtil.exist(filePath)) {
            FileUtil.mkdir(filePath);
        }
        // 文件的原始名称
        String originalFilename = file.getOriginalFilename();
        String realFilePath = filePath + originalFilename;
        if (FileUtil.exist(realFilePath)) {  // 同名文件已存在   123.jpg =>  123_123123123124141.jpg
            originalFilename = FileUtil.mainName(originalFilename) + "_" + System.currentTimeMillis()
                    + "." + FileUtil.extName(originalFilename);
            realFilePath = filePath + originalFilename;
        }
        File localFile = new File(realFilePath);
        file.transferTo(localFile);
        String url = "http://localhost:" + port + "/files/download/" + originalFilename;
        return Result.success(url);
    }

    /**
     * 文件下载
     */
    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, String.valueOf(StandardCharsets.UTF_8)));
        String realFilePath = filePath + fileName;
        byte[] bytes = FileUtil.readBytes(realFilePath);
        ServletOutputStream os = response.getOutputStream();
        os.write(bytes);
        os.flush();
        os.close();
    }

}
