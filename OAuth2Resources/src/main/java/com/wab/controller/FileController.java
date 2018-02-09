package com.wab.controller;

import com.wab.service.FileService;
import main.java.com.UpYun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/files")
public class FileController {
    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private FileService fileService;
    @Value("${im.files.cdn.upyun.bucket}")
    private String bucket;
    @Value("${im.files.cdn.upyun.username}")
    private String username;
    @Value("${im.files.cdn.upyun.password}")
    private String passwd;

//    @RequestMapping(
//            value = "/upload",
//            method = RequestMethod.POST
//    )
//    public ResponseEntity<Map<String, Object>> handleFileUpload(HttpServletRequest request) throws IOException {
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        List<String> fids = new ArrayList<>();
//        Map<String, Object> map = new HashMap<>();
//        MultipartFile file = null;
//        for (int i = 0; i < files.size(); ++i) {
//            file = files.get(i);
//            if (!file.isEmpty()) {
//                GridFSFile gridFSFile = fileService.saveFile(file);
//                fids.add(gridFSFile.getId().toString());
//            } else {
//                LOG.error("上传文件为空");
//            }
//        }
//        map.put("msg", "上传文件成功");
//        map.put("files", fids);
//        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//    }

    @RequestMapping(
            value = "/cdn/upload",
            method = RequestMethod.POST
    )
    public ResponseEntity<Map<String, Object>> handleCDNFileUpload(HttpServletRequest request) throws IOException {
        UpYun upYun = new UpYun(bucket, username, passwd );
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        List<String> fids = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        MultipartFile file = null;
        String filePathPrefix = "/im/" + sdf.format(new Date()) + "/";
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                upYun.setTimeout(60);
                upYun.setApiDomain(UpYun.ED_AUTO);
                String originalFileName = file.getOriginalFilename();
                String filePath = filePathPrefix + System.currentTimeMillis() + originalFileName.hashCode() + originalFileName.substring(originalFileName.lastIndexOf(".")-1);
                boolean isok = upYun.writeFile(filePath, file.getBytes(), true);
                if (isok) {
                    fids.add(filePath);
                }else {
                    map.put("msg", "上传文件失败");
                    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                LOG.error("上传文件为空");
            }
        }
        map.put("msg", "上传文件成功");
        map.put("files", fids);
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }
//    public Map<String, Object> handleCDNFileUpload(HttpServletRequest request) throws IOException {
//        UpYun upYun = new UpYun(bucket, username, passwd );
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        List<String> fids = new ArrayList<>();
//        Map<String, Object> map = new HashMap<>();
//        MultipartFile file = null;
//        String filePathPrefix = "/im/" + sdf.format(new Date()) + "/";
//        for (int i = 0; i < files.size(); ++i) {
//            file = files.get(i);
//            if (!file.isEmpty()) {
//                upYun.setTimeout(60);
//                upYun.setApiDomain(UpYun.ED_AUTO);
//                String originalFileName = file.getOriginalFilename();
//                String filePath = filePathPrefix + System.currentTimeMillis() + originalFileName.hashCode() + originalFileName.substring(originalFileName.lastIndexOf(".")-1);
//                boolean isok = upYun.writeFile(filePath, file.getBytes(), true);
//                if (isok) {
//                    fids.add(filePath);
//                }else {
//                    map.put("msg", "上传文件失败");
//                    return RESTResponse.getErrorResponse(DyrsHttpStatus.ERROR,"上传失败！");
//                }
//            } else {
//                LOG.error("上传文件为空");
//            }
//        }
//        map.put("msg", "上传文件成功");
//        map.put("files", fids);
//        return RESTResponse.getResponse(DyrsHttpStatus.OK,map,"上传成功！");
//    }

//    @RequestMapping(
//            value = "/{id}",
//            method = RequestMethod.GET
//    )
//    public ResponseEntity fileDownload(@PathVariable("id") String fid, HttpServletResponse response) {
//        Map<String, Object> map = new HashMap<>();
//        if (StringUtils.isEmpty(fid)) {
//            map.put("error", "file id must be not empty or null");
//            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
//        } else {
//            GridFSDBFile fsFile = fileService.getFile(fid);
//            if (fsFile != null) {
//                try {
//                    String fileName = fsFile.getFilename();
//                    HttpHeaders headers = new HttpHeaders();
//                    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//                    headers.add("Pragma", "no-cache");
//                    headers.add("Expires", "0");
//                    headers.add("charset", "utf-8");
//
//                    //设置下载文件名
//                    fileName = URLEncoder.encode(fileName, "UTF-8");
//                    headers.add("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
//                    headers.setContentType(MediaType.parseMediaType("application/force-download"));
//                    response.setContentType("application/force-download");// 设置强制下载不打开
//                    Resource resource = new InputStreamResource(fsFile.getInputStream());
//
//                    return new ResponseEntity(resource, headers, HttpStatus.OK);
//                } catch (Exception e) {
//                    LOG.error(e.getMessage());
//                    map.put("error", e.getMessage());
//                    return new ResponseEntity(map, HttpStatus.INTERNAL_SERVER_ERROR);
//                }
//            } else {
//                LOG.error("file not fund {}", fid);
//                map.put("error", "file not fund " + fid);
//                return new ResponseEntity(map, HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//
//        }
//    }
}
