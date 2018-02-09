package com.wab.service;


import com.wab.OAuth2ResourceServer;
import com.wab.model.entity.AppUser;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OAuth2ResourceServer.class)
@WebAppConfiguration
public class SystemUserServiceTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    String client_id = "appclient";
    String uid = "123456";

    @org.junit.Test
    public void getCustomerFromCache() throws Exception {
        stringRedisTemplate.opsForHash().get("customer:" + client_id + ":" + uid, "info");
        AppUser appUser = new AppUser();
        appUser.setUsername("hcq");
        appUser.setNick("hcq");
        appUser.setClientId("test");

        stringRedisTemplate.opsForHash().put("wefe", appUser, appUser);


    }

}