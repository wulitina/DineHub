package com.southwind.controller;

import com.southwind.entity.User;
import com.southwind.entity.UserVO;
import com.southwind.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserHandler {
    // 通过userFeign完成服务调用,同时也会开启负载均衡
    @Autowired
    private UserFeign userFeign;
    //加了ResponseBody直接返回值,如果不加会返回视图,
    // layui框架传page和limit,需要做一个简单的转换
    // findAll不能返回集合,要返回msg,data等属性,要做一个封装
    // layui不是rest方式,是传统的问号传递参数
    @ResponseBody
    @GetMapping("/findAll")
    public UserVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        UserVO userVO = new UserVO();
        userVO.setCode(0);
        userVO.setMsg("");
        userVO.setCount(userFeign.count());
        userVO.setData(userFeign.findAll(index,limit));
        return userVO;
    }
    // 这里很复杂,(1)访问 /user/redirect的时候,把location传进去
    // (2) 后台映射到html
    // (3) 从script里的table render的url里面发一个异步请求
    // (4) 回到当前的getmapping等对应的原始方法,比如findAll,把数据加载出来
    @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable("location") String location){
        return location;
    }



    @GetMapping("/count")
    public int count(){
        return userFeign.count();
    }
    //RequestBody 需要在前端传入json数据的时候加入,把json转化成java对象
    //但是现在layui框架下前端只是普通的问号传参的形式,所以不需要这个注解
    //添加完成,返回视图
    @PostMapping("/save")
    public String save(User user){
        user.setRegisterdate(new Date());
        userFeign.save(user);
        return "redirect:/menu/redirect/user_manage";
    }

    // 这里的注解不应该是@DeleteMapping("/deleteById/{id}"),而是get
    // 因为通过layui框架的window.location.href发送的请求以超链接形式,
    // 全部是get请求
    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id){
        userFeign.deleteById(id);
        // 删除完之后重新请求 /redirect/{location},回到 "redirect:/user/redirect/xx.html";
        return "redirect:/menu/redirect/user_manage";
    }
}
