package com.wab.controller;


import com.alibaba.fastjson.JSONObject;
import com.wab.dto.RestResult;
import com.wab.dto.UserDto;
import com.wab.entry.LeaveMessage;
import com.wab.model.constant.RestConstant;
import com.wab.model.entity.AppUser;
import com.wab.model.entity.ChatLog;
import com.wab.model.service.inf.AppUserService;
import com.wab.service.ChatLogSerivce;
import com.wab.utils.*;
import com.wab.vo.*;
import com.wab.vo.user.RsetPassWord;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ChatLogSerivce chatLogSerivce;

    @Autowired
    private AppUserService appUserService;


    @Value("${url.security.token.check}")
    private String URL_SECURITY;


    @RequestMapping(
            path = "/user",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Object readUser(@RequestBody @Valid UserVO userVO, BindingResult vaildResult) throws IOException {
        if (vaildResult.hasErrors()) {
            VaildUtils.parseExction(vaildResult);
        }
        String token = userVO.getToken();
        String s = HttpUtils.getUser(URL_SECURITY, token);
        JSONObject jsonObject = JSONObject.parseObject(s);
        String username = jsonObject.getString("user_name");
        AppUser user = appUserService.getAppUserByUsername(username);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return new RestResult(RestConstant.SUCCESS, userDto);
    }


    /**
     * 修改密码
     *
     * @param rsetPassWord
     * @param vaildResult
     * @return
     */
    @RequestMapping(
            path = "/rsetPassword",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Object rsetPassword(@RequestBody @Valid RsetPassWord rsetPassWord, BindingResult vaildResult) throws IOException {
        if (vaildResult.hasErrors()) {
            VaildUtils.parseExction(vaildResult);
        }
        String username = TokenUtils.getUserName(URL_SECURITY, rsetPassWord.getToken());
        AppUser user = appUserService.getAppUserByUsername(username);
        if (!PasswordUtils.verification(rsetPassWord.getOldPassWord(), user.getPassword())) {
            return new RestResult(RestConstant.OLD_PASSWORD_ERROR);
        }
        user.setPassword(PasswordUtils.encode(rsetPassWord.getNewPassWord()));
        appUserService.updateUserPassword(user);
        return new RestResult(RestConstant.SUCCESS);
    }

    /**
     * 修改用户个人信息
     *
     * @param userDetails
     * @param vaildResult
     * @return
     */
    @RequestMapping(
            path = "/editUserDetails",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Object editUserDetails(@RequestBody @Valid UserDetails userDetails, BindingResult vaildResult) throws Exception {
        if (vaildResult.hasErrors()) {
            VaildUtils.parseExction(vaildResult);
        }
        String token = userDetails.getToken();
        String userName = TokenUtils.getUserName(URL_SECURITY, token);
        userDetails.setUsername(userName);
        int i = appUserService.updateAppUser(userDetails);
        return new RestResult(RestConstant.SUCCESS);
    }


    @RequestMapping(
            value = "/getChatLog",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Object getChatLog(@RequestBody @Valid ChatLogPageVO chatLogPageVO, BindingResult vaildResult) {
        if (vaildResult.hasErrors()) {
            VaildUtils.parseExction(vaildResult);
        }
        Page<ChatLog> chatLogs = chatLogSerivce.pageByUserToUerId(chatLogPageVO);
        return new RestResult(RestConstant.SUCCESS, parseMessage(chatLogs));
    }

    @RequestMapping(
            value = "/getCompanyLog",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Object getCompanyLog(@RequestBody @Valid CompanyVO companyVO, BindingResult vaildResult) {
        if (vaildResult.hasErrors()) {
            VaildUtils.parseExction(vaildResult);
        }
        Page<LeaveMessage> companyLogs = chatLogSerivce.pageMessageByCompany(companyVO);
        return new RestResult(RestConstant.SUCCESS, parseMessageBase(companyLogs));
    }

    @RequestMapping(
            value = "/addCompanyLog",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Object addCompanyLog(@RequestBody @Valid LeaveMessageVO leaveMessageVO, BindingResult vaildResult) {
        leaveMessageVO.setContent(MessageUtils.deleteAllHTMLTag(leaveMessageVO.getContent()));
        if (vaildResult.hasErrors()) {
            VaildUtils.parseExction(vaildResult);
        }
        LeaveMessage leaveMessage = chatLogSerivce.makeCompanyLog(leaveMessageVO);
        Map<String, Object> map = new HashMap<>();
        if (leaveMessage != null) {
            return RestResultUtils.getOKInstance("添加成功!");
        } else {
            return RestResultUtils.getErrorInstance("添加失败!");
        }
    }


    private Map<String, Object> parseMessage(Page<ChatLog> chatLogs) {
        List<ChatLog> data = chatLogs.getContent();
        int size = data.size();
        ChatLog[] content = new ChatLog[size];
        for (int i = 0; i < size; i++) {
            content[size - 1 - i] = data.get(i);
        }
        return parseMessageBase(chatLogs, Arrays.asList(content));
    }

    private Map<String, Object> parseMessageBase(Page message, List content) {
        Map<String, Object> result = new HashMap<>();
        result.put("pageSize", message.getSize());
        result.put("pageNow", message.getNumber() + 1);
        result.put("totalPage", message.getTotalPages());
        result.put("count", message.getTotalElements());
        result.put("content", content);
        return result;
    }

    private Map<String, Object> parseMessageBase(Page message) {
        return parseMessageBase(message, message.getContent());
    }


}
