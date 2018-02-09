package com.wab.service;


import com.wab.dao.mysql.LeaveMessageDao;
import com.wab.entry.LeaveMessage;
import com.wab.model.dao.mongo.ChatLogRepository;
import com.wab.model.entity.ChatLog;
import com.wab.utils.StringUtils;
import com.wab.vo.ChatLogPageVO;
import com.wab.vo.CompanyVO;
import com.wab.vo.LeaveMessageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ChatLogSerivce {

    @Autowired
    private ChatLogRepository chatLogRepository;

    @Autowired
    private LeaveMessageDao leaveMessageDao;

    /**
     * 聊天记录查询
     *
     * @param chatLogPageVO
     * @return
     */
    public Page<ChatLog> pageByUserToUerId(ChatLogPageVO chatLogPageVO) {
        String user_user_id = StringUtils.makeNewString(chatLogPageVO.getFrom_uid(), chatLogPageVO.getTo_uid());
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "time"));
        PageRequest pageRequest = new PageRequest(chatLogPageVO.getPageNow() - 1, chatLogPageVO.getPageSize(), sort);
        return chatLogRepository.queryAllByPage(user_user_id, chatLogPageVO.getTime(), pageRequest);
    }



    /**
     * 用户添加留言
     * @param companyVO
     * @return
     */
    public Page<LeaveMessage> pageMessageByCompany(CompanyVO companyVO) {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "createTime"));
        PageRequest pageRequest = new PageRequest(companyVO.getPageNow() - 1, companyVO.getPageSize(), sort);
        return leaveMessageDao.findAllByCompany(companyVO.getCompanyName(),pageRequest);
    }


    public LeaveMessage makeCompanyLog(LeaveMessageVO leaveMessageVO) {
        LeaveMessage leaveMessage = new LeaveMessage();
        BeanUtils.copyProperties(leaveMessageVO, leaveMessage);
        if (leaveMessage.getCreateTime() == null) {
            leaveMessage.setCreateTime(new Date());
        }
        LeaveMessage save = leaveMessageDao.save(leaveMessage);
        return save;
    }





}
