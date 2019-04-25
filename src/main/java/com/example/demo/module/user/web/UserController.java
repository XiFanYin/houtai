package com.example.demo.module.user.web;

import com.example.demo.handler.ErrorMessage;
import com.example.demo.handler.LogicException;
import com.example.demo.module.user.entity.User;
import com.example.demo.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController//RestController是@ResponseBody和@Controller的合并
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;
    @RequestMapping("/selectbyid")
    private Map<String, Object> selectbyid(Long userId) {
        if (userId<=0){
            throw  LogicException.le(ErrorMessage.MOBILE_ALREADY_REGISTER);
        }
        HashMap map = new HashMap();

        User user = userService.selectByPrimaryKey(userId);
        map.put("user", user);
        return map;
    }

}
