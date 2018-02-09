//package com.wab.mysql;
//
//import com.wab.base.SpringBase;
//import com.wab.model.dao.mysql.LeaveMessageDao;
//import com.wab.model.entity.msql.LeaveMessagePO;
//import org.json.JSONObject;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Date;
//
///**
// * @author hcq
// * @create 2018-01-29 上午 10:19
// **/
//
//
//public class MysqlTest extends SpringBase {
//
//    @Autowired
//    private LeaveMessageDao leaveMessageDao;
//    @Test
//    public void insertTest() {
//        for (int i = 0; i < 100; i++) {
//            LeaveMessagePO po = new LeaveMessagePO();
//            po.setFromJid(String.valueOf(i));
//            po.setCompany("测试");
//            po.setCreateTime(new Date(System.currentTimeMillis()));
//            po.setCreateUer("haha");
//            LeaveMessagePO save = leaveMessageDao.save(po);
//            System.out.println(new JSONObject(save).toString());
//            System.out.println(234);
//        }
//
//    }
//
//
////    @Test
////    public void query2232() {
////        new Date("2018-01-29 16:45:41");
////        leaveMessageDao.findByCompanyAndAndCreateTimeBetween("appclient"，);
////    }
//    @Test
//    public void query() {
////        List<LeaveMessagePO> ceshi = leaveMessageDao.findByCompanyAndYn("测试", BasePO.DEFAULT_YN);
////        System.out.println(new JSONArray(ceshi).toString());
//    }
//    @Test
//    public void query1() {
////        CompanyVO companyVO = new CompanyVO();
////        companyVO.setCompanyName("appclient");
////        companyVO.setCustomerName("appclient_hcq");
////
////        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "c_time"));
////        PageRequest pageRequest = new PageRequest(1 , 10, sort);
//
////        Page<LeaveMessagePO> leaveMessage = leaveMessageDao.getLeaveMessage("appclient_hcq","appclient",  pageRequest);
////        System.out.println(new JSONObject(leaveMessage).toString());
//
//
//    }
//
//    public static void main(String[] args) {
//        System.out.println("select o.id,o.from_nick,o.from_jid,o.to_company,o.title,o.content,o.c_time,o.c_user,o.yn,b.is_read from oa_leave_message o" +
//                " LEFT JOIN oa_leave_message_read b on o.id = b.message_id  and b.customer_name = ?0" +
//                " where o.yn = 1 and o.to_company = ?1 \n-- #pageable\n ");
//    }
//
//}
