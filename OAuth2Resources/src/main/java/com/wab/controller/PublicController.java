package com.wab.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wab.dto.RestResult;
import com.wab.model.constant.RestConstant;
import com.wab.model.entity.AppUser;
import com.wab.model.entity.SimpleUserInfo;
import com.wab.model.service.inf.AppUserService;
import com.wab.service.SystemUserService;
import com.wab.utils.HttpUtils;
import com.wab.utils.IpUtil;
import com.wab.utils.VaildUtils;
import com.wab.vo.user.UserBrowserTagVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wanganbang
 * <p>
 * PublicController Creatd on 2018/1/8
 */
@RestController
@RequestMapping("/public")
public class PublicController {
    private static Logger LOG = LoggerFactory.getLogger(PublicController.class);

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private AppUserService appUserService;

    @Value("${im.wellcome.msg}")
    private String wellcome_msg;

    @Value("${url.user.browser.tag.uid}")
    private String user_browser_by_uid_url;

    @Value("${url.user.profiles.tag.uid}")
    private String user_profiles_by_uid_url;
    /**
     * 获取随机在线客服
     *
     * @param client_id
     * @param uid
     * @param request
     * @return
     */
    @RequestMapping(
            path = "/customer/{clientId}/{uid}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Object getRandomOnlineUser(@PathVariable("clientId") String client_id, @PathVariable("uid") String uid, HttpServletRequest request) {

        SimpleUserInfo simpleUserInfo = systemUserService.getCustomerFromCache(client_id, uid);
        parseIpAndAddress(simpleUserInfo, request);
        return new RestResult(RestConstant.SUCCESS, simpleUserInfo);
    }


    @RequestMapping(
            path = "/getUserBrowserTag",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Object getUserBrowserTag(@RequestBody @Valid UserBrowserTagVO userBrowserTagVO, BindingResult vaildResult) throws IOException {

        if (vaildResult.hasErrors()) {
            VaildUtils.parseExction(vaildResult);
        }
        String profiles = HttpUtils.httpGet(user_profiles_by_uid_url.replace("UUID", userBrowserTagVO.getuId()));
        String browser = HttpUtils.httpGet(user_browser_by_uid_url.replace("UUID", userBrowserTagVO.getuId()));
        JSONObject userProfiles = JSONObject.parseObject(profiles);
        JSONObject userBrowser = JSONObject.parseObject(browser);
        JSONObject result = userProfiles.getJSONObject("data");
        JSONArray data = userBrowser.getJSONArray("data");
        result.put("browser", data);
        return new RestResult(RestConstant.SUCCESS, result);
    }


    private void parseIpAndAddress(SimpleUserInfo simpleUserInfo, HttpServletRequest request) {
        try {
            String ipAddr = IpUtil.getIpAddr(request);
            simpleUserInfo.setIp(ipAddr);
            String[] address = IpUtil.getAddress(ipAddr);
            simpleUserInfo.setAddress(address);
        } catch (Exception e) {
            LOG.error("识别地址出错");
        }
    }


    @RequestMapping(
            value = "/wellcome/msg/{customer}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Map<String, Object>> getWellcomeMessage(@PathVariable("customer") String customer) throws UnsupportedEncodingException {
        AppUser appUserByUsername = appUserService.getAppUserByUsername(customer);
        String welcome = appUserByUsername.getWelcome();
        welcome = welcome == null ? wellcome_msg : welcome;
        String nick = appUserByUsername.getNick();
        String message = new String(welcome.getBytes("ISO-8859-1"), "UTF-8").replace("{nick}", nick);
        return ResponseEntity.ok(makeWelcome(message));
    }

    @RequestMapping(
            value = "/wellcome/msg",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Map<String, Object>> getWellcomeMessage() throws UnsupportedEncodingException {
        return ResponseEntity.ok(makeWelcome(new String(wellcome_msg.getBytes("ISO-8859-1"), "UTF-8")));
    }

    public Map<String, Object> makeWelcome(String message) throws UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", message);
        map.put("time", System.currentTimeMillis() + "");
        map.put("type", "welcome");
        return map;
    }
}
