package com.wab.controller;

import com.wab.exception.UserAlreadyExistsException;
import com.wab.model.entity.AppBaseClientDetaile;
import com.wab.model.entity.AppUser;
import com.wab.model.service.inf.AppClientDetailService;
import com.wab.model.service.inf.AppUserService;
import com.wab.service.SystemUserService;
import com.wab.utils.RestResultUtils;
import com.wab.utils.VaildUtils;
import com.wab.vo.APPUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/system")
public class SystemController {
    Logger log = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private AppUserService appUserService;
    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private AppClientDetailService appClientDetailService;


    @RequestMapping(
            path = "/users/{uid}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Object readUser(@PathVariable("uid") String uid) {
        AppUser user = appUserService.getAppUserByUsername(uid);
        return RestResultUtils.getOKInstance("查询成功！",user);
    }

    @RequestMapping(
            path = "/users",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Object getAllUsers() throws Exception {
        return RestResultUtils.getOKInstance("获取成功", systemUserService.getAllUsers());

    }

    @RequestMapping(
            path = "/{clientId}/users",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Object addUser(@PathVariable("clientId") String clientID, @RequestBody @Valid APPUserVO appUserVO, BindingResult vaildResult) throws UserAlreadyExistsException {
        if (vaildResult.hasErrors()) {
            VaildUtils.parseExction(vaildResult);
        }
        String userID = systemUserService.saveSystemUser(clientID, appUserVO);
        return RestResultUtils.getOKInstance("添加成功！",userID);
    }

    @RequestMapping(
            path = "/apps/{cid}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Object getClientInfomations(@PathVariable("cid") String cid) {
        AppBaseClientDetaile appBaseClientDetaile = appClientDetailService.getBaseClientDetaileByID(cid);
        if (appBaseClientDetaile == null) {
            return RestResultUtils.getErrorInstance(cid + " Not Found");
        } else {
            return RestResultUtils.getOKInstance("获取成功！",appBaseClientDetaile);
        }
    }
}
