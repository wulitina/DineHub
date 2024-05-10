package com.southwind.controller;

import com.southwind.entity.User;
import com.southwind.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserHandler {
//    @Value("${server.port}")
//    private String port;
//    @GetMapping("/index")
//    public String index(){
//        return this.port;
//    }
    //注入我们之前写的各种repository中的UserRepository的数据库语句，
    //注入mapper创建的动态代理对象，把mapper声明的代理扫描到ioc中，加一个MapperScan注解
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/findAll/{index}/{limit}")
    public List<User> findAll(@PathVariable("index") int index,@PathVariable("limit") int limit){
        return  userRepository.findAll(index,limit);
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") long id){
        return userRepository.findById(id);
    }
    @GetMapping("/count")
    public int count(){
        return userRepository.count();
    }
    // 微服务rest方式进行沟通，数据封装成json,RequestBody把json格式的数据转化成user对象，
    // 加进去存入则为空
    @PostMapping("/save")
    public void save(@RequestBody User user){
      userRepository.save(user);
    }

    @PutMapping("/update")
    public void update(@RequestBody User user){
        userRepository.update(user);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
        userRepository.deleteById(id);

    }
}
