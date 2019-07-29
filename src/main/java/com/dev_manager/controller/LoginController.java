package com.dev_manager.controller;

import com.alibaba.fastjson.JSON;
import com.dev_manager.entities.BaseResponse;
import com.dev_manager.entities.User;
import com.dev_manager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller("com.dev_manager.controller.LoginController")
@RequestMapping(value = "/index", produces = "application/json;charset=UTF-8")
public class LoginController {
    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger("LoginController");

    @RequestMapping(value = "/login")
    public ModelAndView getLogin() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/postLogin")
    @ResponseBody
    public BaseResponse postLogin(@RequestParam("json") String jsonString) {
        User user = JSON.parseObject(jsonString, User.class);
        logger.info("user = " + user.toString());
        boolean isUser = userService.checkUser(user.getAccount(),user.getPassword());
        BaseResponse baseResponse = new BaseResponse();
        if(isUser){
            baseResponse.setStatusCode(0);
            baseResponse.setData(null);
            baseResponse.setErrMsg("登录成功");
        }else {
            baseResponse.setStatusCode(-1);
            baseResponse.setData(null);
            baseResponse.setErrMsg("账号或者密码错误");
        }

        return baseResponse;
    }


}
