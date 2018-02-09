package com.wab.mongo;

import com.wab.base.SpringBase;
import com.wab.model.dao.mongo.ChatLogRepository;
import com.wab.model.entity.ChatLog;
import com.wab.utils.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import java.util.List;

public class MongoTest extends SpringBase {
    @Autowired
    private ChatLogRepository chatLogRepository;

    private String from_id = "appclient_hcq";
    private String to_id = "123456";

    @Test
    public void Test() throws Exception {
        List<ChatLog> all = chatLogRepository.findAll();
        System.out.println("123213");
    }

    @Test
    public void QueryTest() throws Exception {
        long now = System.currentTimeMillis();
        String user_user_id = StringUtils.makeNewString(from_id, to_id);
//        chatLogRepository.countByUserToUerId(user_user_id, now);
        Sort sort = new Sort(new Sort.Order("time:-1"));
        PageRequest pageRequest = new PageRequest(1,10,sort);
        Page<ChatLog> chatLogs = chatLogRepository.queryAllByPage(user_user_id, now, pageRequest);
        Object[] objects = chatLogs.getContent().toArray();
    }

    @Test
    public void TestInsert() throws Exception {

        ChatLog chatLog = new ChatLog();
        chatLog.setClient_id("applient");
        chatLog.setClient_user_id("applient:123456");
        chatLog.setUser_user_id("applient_hcq:123456");
        chatLog.setTime(System.currentTimeMillis());

        chatLogRepository.insert(chatLog);
        System.out.println("123213");
    }

}
