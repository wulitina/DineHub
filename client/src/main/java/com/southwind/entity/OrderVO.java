package com.southwind.entity;

import lombok.Data;

import java.util.List;

@Data
public class OrderVO {
    private int Code;
    private String msg;
    private int Count;
    private List<Order> data;

}
