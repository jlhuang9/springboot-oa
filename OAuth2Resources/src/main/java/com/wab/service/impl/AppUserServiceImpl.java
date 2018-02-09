package com.wab.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.WriteResult;
import com.wab.model.dao.mongo.AppUserRepository;
import com.wab.model.entity.AppUser;
import com.wab.model.service.inf.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Set;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${redis.defult.time}")
    private long redis_defult_time;

    @Override
    public AppUser saveAppUser(AppUser user) {
        return appUserRepository.save(user);
    }

    @Override
    public AppUser getAppUserByUsername(String username) {
        String redisUserInfo = makeUserInfo(username);
        String userinfo = stringRedisTemplate.opsForValue().get(redisUserInfo);
        AppUser appUser = JSONObject.parseObject(userinfo, AppUser.class);
        if (appUser == null) {
            appUser = appUserRepository.findOne(username);
            stringRedisTemplate.opsForValue().set(redisUserInfo, JSONObject.toJSONString(appUser));
        }
        return appUser;
    }

    @Override
    public Collection<AppUser> getAllusers() {
        return appUserRepository.findAll(new Sort(new Sort.Order(Sort.Direction.DESC, "creationDate")));
    }

    @Override
    public int updateUserPassword(AppUser user) {
        Query query = getQueryName(user.getUsername());
        Update update = new Update();
        update.set("password", user.getPassword());
        WriteResult writeResult = mongoTemplate.updateFirst(query, update, AppUser.class);
        return writeResult.getN();
    }

    @Override
    public int updateAppUser(AppUser user) {
        return updateBasic(user);
    }

    private Query getQueryName(String username) {
        return new Query(Criteria.where("username").is(username));
    }

    private String makeUserInfo(String username) {
        String[] split = username.split("_");
        return "users:info:" + split[0] + ":" + split[1];
    }

    private int updateBasic(AppUser user) {
        String nick = user.getNick();
        String username = user.getUsername();
        Set<String> avatar = user.getAvatar();
        Query query = getQueryName(username);
        String welcome = user.getWelcome();
        String tel = user.getTel();
        Update update = new Update();
//        if (!StringUtils.isEmpty(nick)) {
        update.set("nick", nick);
//        }
//        if (!StringUtils.isEmpty(avatar)) {
//            update.set("avatar", avatar);
//        }
//        if (!StringUtils.isEmpty(welcome)) {
        update.set("welcome", welcome);
//        }
//        if (!StringUtils.isEmpty(tel)) {
        update.set("tel", tel);
//        }
        stringRedisTemplate.opsForValue().set(makeUserInfo(username), JSONObject.toJSONString(user));
        return mongoTemplate.updateFirst(query, update, AppUser.class).getN();
    }


}
