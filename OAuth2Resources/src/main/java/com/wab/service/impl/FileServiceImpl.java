package com.wab.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.wab.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {
    private static final Logger LOG = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Override
    public GridFSFile saveFile(MultipartFile file) throws IOException {
        DBObject dbObject = new BasicDBObject();
        String originalFilename = file.getOriginalFilename();
        String name = file.getName();
        String contentType = file.getContentType();
        dbObject.put("filename", originalFilename);
        dbObject.put("name", name);
        dbObject.put("contenttype", contentType);
        dbObject.put("time", System.currentTimeMillis());
        GridFSFile gridFSFile = gridFsTemplate.store(file.getInputStream(),originalFilename, contentType, dbObject);
        LOG.debug(gridFSFile.toString());
        return gridFSFile;
    }

    @Override
    public GridFSDBFile getFile(String fid) {
        GridFSDBFile fsdbFile = gridFsTemplate.findOne(Query.query(GridFsCriteria.where("_id").is(fid)));
        return fsdbFile;
    }
}
