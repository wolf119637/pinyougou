package com.pinyougou.manager.controller;

import com.pinyougou.entity.Result;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by
 * kim on 2019/5/26.
 */
@RestController
@RequestMapping("/login")
public class LoginController {



    @RequestMapping("name")
    public Map name(){
        //获取认证的用户名
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Map map = new HashMap<>();
        map.put("loginName",name);
        return map;
    }



}
