package com.wab.model.dao.mongo;

import com.wab.model.entity.ChatLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ChatLogRepository extends MongoRepository<ChatLog, String> {

    @Query(value = "{user_user_id : ?0,time:{$lt:?1}}")
    Page<ChatLog> queryAllByPage(String user_user_id, long timeStamp, Pageable pageable);

//    Page<ChatLog> findByUser_user_idAndTimeBefore(String user_user_id, Long time, Pageable pageable);
//    Page<ChatLog> findByUser_user_idAndTimeBefore(String user_user_id, Long time, Pageable pageable);
}
