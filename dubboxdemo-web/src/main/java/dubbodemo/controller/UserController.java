package dubbodemo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import dubbodemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kim on 2019/5/22.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("/showName")
    @ResponseBody
    public String showName(){
        return userService.getName();
    }
}
