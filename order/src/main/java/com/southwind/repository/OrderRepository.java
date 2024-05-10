package com.southwind.repository;

import com.southwind.entity.Order;

import java.util.List;

public interface OrderRepository {
    public void save(Order order);
    public List<Order> findAllByUID(int index,int limit,long uid);
    //  查询当前用户订单信息&查询全部用户订单信息
    public int countByUID (long uid);

    public List<Order> findAllUnreviewedOrder(int index,int limit);

    public void updateState(long orderId);

    public int unreviewedOrderCount();


}
