package com.wab.dao.mysql;

import com.wab.entry.LeaveMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hcq
 * @create 2018-02-06 下午 7:46
 **/
@Repository
public interface LeaveMessageDao extends JpaRepository<LeaveMessage, Long> {
    Page<LeaveMessage> findAllByCompany(String companyName, Pageable pageable);
}
