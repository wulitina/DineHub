package com.southwind.controller;

import com.southwind.entity.Order;
import com.southwind.entity.OrderVO;
import com.southwind.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping ("/order")
public class OrderHandler {
//    @Value("${server.port}")
//    private String port;
//
//    @GetMapping("/index")
//    public String index(){
//        return "order的端口 "+ this.port;
//    }
    @Autowired
    private OrderRepository orderRepository;
    @PostMapping("/save")
    private void save(@RequestBody Order order){
        order.setDate(new Date());
        orderRepository.save(order);
    }
    @GetMapping("/findAllByUID/{index}/{limit}/{uid}")
    public OrderVO findAllByUID(@PathVariable("index") int index, @PathVariable("limit") int limit,@PathVariable("uid") long uid){
        OrderVO orderVO = new OrderVO();
        orderVO.setMsg("");
        orderVO.setCount(orderRepository.countByUID(uid));
        orderVO.setData(orderRepository.findAllByUID(index, limit, uid));
        return orderVO;
    }
    @GetMapping("/countByUID/{uid}")
    public int countByUID(@PathVariable("uid") int uid){
        return orderRepository.countByUID(uid);
    }

    @GetMapping("/findAllUnreviewedOrder/{index}/{limit}")
    public OrderVO findAllUnreviewedOrder(@PathVariable("index") int index, @PathVariable("limit") int limit){
        // 这里返回所有状态为0的订单未审核订单
        OrderVO orderVO = new OrderVO();
        orderVO.setMsg("");
        orderVO.setCount(orderRepository.unreviewedOrderCount());
        orderVO.setData(orderRepository.findAllUnreviewedOrder(index,limit));
        return orderVO;
    }
    @GetMapping("/updateState/{id}")
    public void updateState(@PathVariable("id") long id){
        orderRepository.updateState(id);
    }
}
