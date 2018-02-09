package com.wab.service;

import com.wab.controller.SystemController;
import com.wab.exception.UserAlreadyExistsException;
import com.wab.model.entity.AppUser;
import com.wab.model.entity.SimpleUserInfo;
import com.wab.service.impl.AppUserServiceImpl;
import com.wab.vo.APPUserVO;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class SystemUserService {
    private static Logger LOG = LoggerFactory.getLogger(SystemController.class);
    ObjectMapper objectMapper = new ObjectMapper();
    @Value("${im.wellcome.msg}")
    private String wellcome_msg;

    @Value("${redis.defult.time}")
    private long redis_defult_time;


    private Random random = new Random();
    @Autowired
    private AppUserServiceImpl appUserService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;// Warning: RedisTemplate can not use wildcard(*), but StringRedisTemplate can do it

    public String saveSystemUser(String client_id, APPUserVO userVO) throws UserAlreadyExistsException {
        PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        String username = client_id + "_" + userVO.getUsername();
        if (appUserService.getAppUserByUsername(username) != null) {
            throw new UserAlreadyExistsException();
        }
        AppUser user = new AppUser(client_id, username, passwordEncoder.encode(userVO.getPassword()), authorities);
        String nick = userVO.getNick();
        user.setNick(nick);
        user.setWelcome(wellcome_msg);
        user.setTel(userVO.getTel());
        if (userVO.getAvatar() != null) {
            user.setAvatar(userVO.getAvatar());
        }
        return appUserService.saveAppUser(user).getUsername();
    }

    public Collection<AppUser> getAllUsers() throws Exception {
        return appUserService.getAllusers();
    }

    public boolean isOnline(String username) {
        String[] user_str = username.split("_");
        if (user_str.length > 1) {
            return stringRedisTemplate.hasKey("online_users:" + user_str[0] + ":" + user_str[1]).booleanValue();
        } else {
            return stringRedisTemplate.hasKey("online_guest_users:" + username).booleanValue();
        }
    }

    public SimpleUserInfo getCustomerFromCache(String client_id, String uid) {
        String customerName = null;
        try {
            customerName = stringRedisTemplate.opsForValue().get("customer:" + client_id + ":" + uid);
        } catch (Exception e) {
        }
        if (!StringUtils.isEmpty(customerName) && isOnline(customerName)) {
            AppUser user = appUserService.getAppUserByUsername(customerName);
            if (user != null) {
                SimpleUserInfo simpleUserInfo = new SimpleUserInfo();
                BeanUtils.copyProperties(user, simpleUserInfo);
                return simpleUserInfo;
            }
        }
        return getRandomUserFromApplcations(client_id, uid);
    }



    public SimpleUserInfo getRandomUserFromApplcations(String app_id, String uid) {
        Set<String> keys = stringRedisTemplate.keys("online_users:" + app_id + ":*");
        if (keys != null && !keys.isEmpty()) {
            int size = keys.size();
            int index = random.nextInt(size);
            String randomKey = keys.toArray()[index].toString();
            String customerName = app_id + "_" + randomKey.split(":")[2];
            stringRedisTemplate.opsForValue().set("customer:" + app_id + ":" + uid, customerName, redis_defult_time, TimeUnit.SECONDS);
            AppUser user = appUserService.getAppUserByUsername(customerName);
            if (user != null) {
                SimpleUserInfo simpleUserInfo = new SimpleUserInfo();
                BeanUtils.copyProperties(user, simpleUserInfo);
                return simpleUserInfo;
            }
        }
        return null;
    }
}
