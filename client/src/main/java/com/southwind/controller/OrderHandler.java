package com.southwind.controller;

import com.southwind.entity.Menu;
import com.southwind.entity.Order;
import com.southwind.entity.OrderVO;
import com.southwind.entity.User;
import com.southwind.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderHandler {
    @Autowired
    private OrderFeign orderFeign;

    @GetMapping("/save/{mid}")
    public  String save(@PathVariable("mid") int mid, HttpSession session){
        User user = (User)session.getAttribute("user");
        Order order = new Order();
        order.setUser(user);
        Menu menu = new Menu();
        menu.setId(mid);
        order.setMenu(menu);
        orderFeign.save(order);
        return "index";
    }
    // todo 需要加注解@ResponseBody,否则@Controller会按照视图解析，请求接口会报错
    // template might not exist or might not be accessible by any of the configured Template Resolvers
    @GetMapping("/findAllByUID")
    @ResponseBody
    // layui框架自己会传过来page和limit
    public OrderVO findAllByUID(@RequestParam("page") int page,@RequestParam("limit") int limit, HttpSession session){
        User user = (User) session.getAttribute("user");
        int index=(page-1)*limit;
        return orderFeign.findAllByUID(index,limit,user.getId());
    }

    // layui会给我们传递page和limit需要自己去解析
    @GetMapping("/findAllUnreviewedOrder")
    @ResponseBody
    public OrderVO findAllUnreviewedOrder(@RequestParam("page") int page,@RequestParam("limit") int limit){
        int index=(page-1)*limit;
        return orderFeign.findAllUnreviewedOrder(index,limit);
    }

    //尽管这里是修改，但是是前端页面通过超链接方式传递过来，所以用get请求不是@PutMapping
    @GetMapping("/updateState/{id}")
    public String updateState(@PathVariable("id") long orderId){
         orderFeign.updateState(orderId);
         // todo 注意这里redirect:不能省略
         return "redirect:/menu/redirect/order_handler";
    }
}
