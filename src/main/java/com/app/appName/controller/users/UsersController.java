package com.app.appName.controller.users;

import com.app.appName.config.ErrorCode;
import com.app.appName.dto.ResultDto;
import com.app.appName.entity.UsersEntity;
import com.app.appName.service.UsersService;
import com.app.appName.util.DateUtil;
import com.app.appName.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/users")
public class UsersController {

    @Resource
    private UsersService usersService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto register(String nickName, String pwd, String mobile, String email, String des){
        ResultDto re = new ResultDto();
        UsersEntity usersEntity = null;
        Map<String, Object> map = new HashMap<String, Object>();

        try{
            map.put("mobile", mobile);
            List<UsersEntity> usersEntityList = usersService.queryListByHql("from UsersEntity a where a.mobile = :mobile", map);
            if(usersEntityList == null || usersEntityList.size() <= 0){
                map.clear();
                map.put("email", email);
                usersEntityList = usersService.queryListByHql("from UsersEntity a where a.email=:email", map);

                if(usersEntityList == null || usersEntityList.size() <= 0){
                    usersEntity = new UsersEntity();

                    Timestamp timestamp = DateUtil.getNowTimestamp();

                    usersEntity.setType((short) 1);
                    usersEntity.setPwd(SecurityUtils.MD5to32(pwd));
                    usersEntity.setNickName(nickName);
                    usersEntity.setMobile(mobile);
                    usersEntity.setEmail(email);
                    usersEntity.setDes(des);
                    usersEntity.setCreateTime(timestamp);
                    usersService.save(usersEntity);
                    re.setStatus(ResultDto.RESULT_STATUS_SUCCESS);
                }else{
                    re.setCode(ErrorCode.EMAIL_REPEAT_CODE);
                    re.setMsg(ErrorCode.EMAIL_REPEAT_MSG);
                }

            }else{
                re.setCode(ErrorCode.MOBILE_REPEAT_CODE);
                re.setMsg(ErrorCode.MOBILE_REPEAT_MSG);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return re;
    }



















}
