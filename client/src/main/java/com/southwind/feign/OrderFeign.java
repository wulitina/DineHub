package com.southwind.feign;

import com.southwind.entity.Order;
import com.southwind.entity.OrderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "order")
public interface OrderFeign {
    @PostMapping("/order/save")
    public void save(@RequestBody Order order);
    @GetMapping("/order/findAllByUID/{index}/{limit}/{uid}")
    public OrderVO findAllByUID(@PathVariable("index") int index,@PathVariable("limit") int limit,@PathVariable("uid") long uid );

    @GetMapping("/order/findAllUnreviewedOrder/{index}/{limit}")
    public OrderVO findAllUnreviewedOrder(@PathVariable("index") int index,@PathVariable("limit") int limit );

    @GetMapping("/order/updateState/{id}")
    public void updateState(@PathVariable("id") long orderId);
}
