package com.cienet.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.cienet.bean.FileInfo;
import com.cienet.bean.FileInfo.FileType;
import com.cienet.bean.FileInfo.OrderType;
import com.cienet.service.FileService;
import com.cienet.util.Constants;
import com.cienet.util.FreemarkerUtils;

/**
 * Service - 文件
 * 
 */
@Service
public class FileServiceImpl implements FileService, ServletContextAware {

    /** servletContext */
    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public List<FileInfo> browser(String path, FileType fileType,
            OrderType orderType) {
        if (path != null) {
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (!path.endsWith("/")) {
                path += "/";
            }
        } else {
            path = "/";
        }
        String uploadPath;
        if (fileType == FileType.flash) {
            uploadPath = Constants.FLASH_UPLOAD_PATH;
        } else if (fileType == FileType.media) {
            uploadPath = Constants.MEDIA_UPLOAD_PATH;
        } else if (fileType == FileType.file) {
            uploadPath = Constants.FILE_UPLOAD_PATH;
        } else {
            uploadPath = Constants.IMAGE_UPLOAD_PATH;
        }

        List<FileInfo> fileInfos = new ArrayList<FileInfo>();

        File directory = new File(servletContext.getRealPath(uploadPath));
        if (directory.exists() && directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                FileInfo fileInfo = new FileInfo();
                fileInfo.setName(file.getName());
                fileInfo.setUrl("/exam_server" + uploadPath + file.getName());
                fileInfo.setIsDirectory(file.isDirectory());
                fileInfo.setSize(file.length());
                fileInfo.setLastModified(new Date(file.lastModified()));
                fileInfos.add(fileInfo);
            }
        }

        if (orderType == OrderType.size) {
            Collections.sort(fileInfos, new SizeComparator());
        } else if (orderType == OrderType.type) {
            Collections.sort(fileInfos, new TypeComparator());
        } else {
            Collections.sort(fileInfos, new NameComparator());
        }
        return fileInfos;
    }

    private class NameComparator implements Comparator<FileInfo> {
        public int compare(FileInfo fileInfos1, FileInfo fileInfos2) {
            return new CompareToBuilder()
                    .append(!fileInfos1.getIsDirectory(),
                            !fileInfos2.getIsDirectory())
                    .append(fileInfos1.getName(), fileInfos2.getName())
                    .toComparison();
        }
    }

    private class SizeComparator implements Comparator<FileInfo> {
        public int compare(FileInfo fileInfos1, FileInfo fileInfos2) {
            return new CompareToBuilder()
                    .append(!fileInfos1.getIsDirectory(),
                            !fileInfos2.getIsDirectory())
                    .append(fileInfos1.getSize(), fileInfos2.getSize())
                    .toComparison();
        }
    }

    private class TypeComparator implements Comparator<FileInfo> {
        public int compare(FileInfo fileInfos1, FileInfo fileInfos2) {
            return new CompareToBuilder()
                    .append(!fileInfos1.getIsDirectory(),
                            !fileInfos2.getIsDirectory())
                    .append(FilenameUtils.getExtension(fileInfos1.getName()),
                            FilenameUtils.getExtension(fileInfos2.getName()))
                    .toComparison();
        }
    }

    @Override
    public boolean isValid(FileType fileType, MultipartFile multipartFile) {
        if (multipartFile == null) {
            return false;
        }

        if (multipartFile.getSize() > 10 * 1024L * 1024L) {
            return false;
        }
        String[] uploadExtensions;
        if (fileType == FileType.flash) {
            uploadExtensions = Constants.FLASH_UPLOAD_EXTENSIONS;
        } else if (fileType == FileType.media) {
            uploadExtensions = Constants.MEDIA_UPLOAD_EXTENSIONS;
        } else if (fileType == FileType.file) {
            uploadExtensions = Constants.FILE_UPLOAD_EXTENSIONS;
        } else {
            uploadExtensions = Constants.IMAGE_UPLOAD_EXTENSIONS;
        }
        if (ArrayUtils.isNotEmpty(uploadExtensions)) {
            return FilenameUtils.isExtension(
                    multipartFile.getOriginalFilename(), uploadExtensions);
        }
        return false;
    }

    @Override
    public String upload(FileType fileType, MultipartFile multipartFile) {
        if (multipartFile == null) {
            return null;
        }
        String uploadPath;
        if (fileType == FileType.flash) {
            uploadPath = Constants.FLASH_UPLOAD_PATH;
        } else if (fileType == FileType.media) {
            uploadPath = Constants.MEDIA_UPLOAD_PATH;
        } else if (fileType == FileType.file) {
            uploadPath = Constants.FILE_UPLOAD_PATH;
        } else {
            uploadPath = Constants.IMAGE_UPLOAD_PATH;
        }
        try {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("uuid", UUID.randomUUID().toString());
            String path = FreemarkerUtils.process(uploadPath, model);
            String destPath = path + multipartFile.getOriginalFilename();

            File tempFile = new File(System.getProperty("java.io.tmpdir")
                    + "/upload_" + UUID.randomUUID() + ".tmp");
            if (!tempFile.getParentFile().exists()) {
                tempFile.getParentFile().mkdirs();
            }
            multipartFile.transferTo(tempFile);
            try {
                File destFile = new File(servletContext.getRealPath(destPath));
                try {
                    FileUtils.moveFile(tempFile, destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } finally {
                FileUtils.deleteQuietly(tempFile);
            }
            return "/exam_server" + destPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}