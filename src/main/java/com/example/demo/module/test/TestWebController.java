package com.example.demo.module.test;

import com.example.demo.config.redis.RedisRepository;
import com.example.demo.module.user.entity.User;
import com.example.demo.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestWebController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index.html")
    public String index(Model model, User user) {
        model.addAttribute("user", user);

        Integer i = userService.insert(user);
        if (i > 0) {
            model.addAttribute("msg", "Success");
        } else {
            model.addAttribute("msg", "Fail");
        }
        return "index";
    }


    @RequestMapping("/userList.html")
    public String userList(Model model, Integer page) {
        List<User> users = userService.selectAllUserList(page);
        model.addAttribute("users", users);
        return "user/userlist";
    }

    @RequestMapping("/updateuser.html")
    public String updatauser(Model model, Long userId) {
        User user = userService.selectByPrimaryKey(userId);
        model.addAttribute("user", user);
        return "user/updateuser";
    }

    @Autowired
    RedisRepository redisRepo;

    @RequestMapping("/test")
    @ResponseBody
    public Map testRedis() {
        Map map = new HashMap();
        redisRepo.add("key", "hello~",5000L);
        map.put("key", redisRepo.get("key"));
        return map;
    }


}
