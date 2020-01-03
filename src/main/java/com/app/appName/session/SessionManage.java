package com.app.appName.session;

import com.app.appName.entity.UsersEntity;

import javax.servlet.http.HttpSession;

public class SessionManage {

    public static final String SESSION_USER = "SESSION_USER";

    public static UsersEntity getUserEntity(HttpSession session){
        if(session == null){
            return null;
        }

        UsersEntity user = (UsersEntity) session.getAttribute(SESSION_USER);

        if(user == null){
            return null;
        }

        return user;
    }

    public static void saveUserEntity(HttpSession session,UsersEntity mUserInfo){
        if(session != null && mUserInfo != null){
            session.setAttribute(SESSION_USER, mUserInfo);
        }
    }
}
