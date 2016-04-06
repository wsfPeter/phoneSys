package com.cienet.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cienet.bean.FileInfo;
import com.cienet.bean.FileInfo.FileType;
import com.cienet.bean.FileInfo.OrderType;
import com.cienet.service.FileService;
import com.cienet.util.JsonUtil;

/**
 * Controller - 文件处理
 */
@Controller
@RequestMapping(value = "/file")
public class FileController extends BaseController {

    @Resource
    private FileService fileService;

    /**
     * 浏览
     */
    @RequestMapping(value = "/browser", method = RequestMethod.GET)
    public @ResponseBody List<FileInfo> browser(String path, FileType fileType,
            OrderType orderType) {
        return fileService.browser(path, fileType, orderType);
    }

    /**
     * 上传
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
    public void upload(FileType fileType, MultipartFile file,
            HttpServletResponse response) {
        Map<String, Object> data = new HashMap<String, Object>();
        if (!fileService.isValid(fileType, file)) {
            data.put("message", "上传文件格式或大小不正确");
        } else {
            String url = fileService.upload(fileType, file);
            if (url == null) {
                data.put("message", "上传文件出现错误");
            } else {
                data.put("message", SUCCESS);
                data.put("url", url);
            }
        }
        try {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtil.writeValue(response.getWriter(), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}