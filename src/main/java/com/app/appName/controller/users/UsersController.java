package com.app.appName.controller.users;

import com.app.appName.entity.UsersEntity;
import com.app.appName.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/users")
public class UsersController {

    @Resource
    private UsersService usersService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public void register(HttpSession session, String username){
        UsersEntity usersEntity = new UsersEntity();
        try{
            usersEntity.setName("name");
            usersEntity.setUserName("userName");
            usersEntity.setTel("15957158850");
            usersEntity.setPwd("hufengxi");

            usersService.save(usersEntity);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }



















}
