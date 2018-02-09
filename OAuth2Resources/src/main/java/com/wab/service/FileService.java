package com.wab.service;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    GridFSFile saveFile(MultipartFile file) throws IOException;
    GridFSDBFile getFile(String fid);
}
