package com.southwind.controller;

import com.southwind.entity.Admin;
import com.southwind.entity.User;
import com.southwind.feign.AccountFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

@Controller
@RequestMapping("/account")
public class AccountHandler {
    // 用户登陆成功跳转到用户登录界面，返回视图
    // form表单传递参数，是传统的方式，所以不用写成rest形式
    @Autowired
    private AccountFeign accountFeign;
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("type") String type, HttpSession session){

        //内部调用feign，通过feign调用另外的服务提供者
        Object object = accountFeign.login(username,password,type);
        LinkedHashMap<String,Object> linkedHashMap = (LinkedHashMap) object;
        String result = null;
        String idStr = null;
        long id = 0L;
        // 如果查出来的对象是错误的,说明用户名或者密码是错误的,需要重新登录
        if (object==null){
           result = "login";
        }else{
            // 否则登录成功
            switch (type){
                case "admin":
                    Admin admin = new Admin();
                    idStr = linkedHashMap.get("id")+"";
                    id = Long.parseLong(idStr);
                    String adminName = (String) linkedHashMap.get("username");
                    admin.setId(id);
                    admin.setUsername(adminName);
                    session.setAttribute("admin",admin);
                    //后台管理页面为main.html
                    result = "main";
                    break;
                case "user":
                    User user = new User();
                    idStr = linkedHashMap.get("id")+"";
                    id = Long.parseLong(idStr);
                    String nickName = (String) linkedHashMap.get("nickname");
                    user.setId(id);
                    user.setNickname(nickName);
                    session.setAttribute("user",user);
                    // 跳转到index页面
                    // 想把session拿到不能用重定向方式,静态页面无论request还是session都需要转发方式
                    // jsp里面session是内置对象,所以能拿到,jsp底层是servlet,通过response...，就相当于经过了后台
                    // html页面没有人创建session,就相当于浏览器直接访问页面,没有经过后台,所以只能转发
                    result = "index";
                    break;
            }
        }

        return result;
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();;
        //这里用转发和重定向的方式都可以
        return "redirect:/login.html";
    }
}
