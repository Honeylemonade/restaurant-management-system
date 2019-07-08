package com.ruanjian.restaurantrear.controller;

import com.ruanjian.restaurantrear.entity.Order;
import com.ruanjian.restaurantrear.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * OrderController:
 *
 * @author XvYanPeng
 * @date 2019/6/25 17:14
 */
@CrossOrigin
@RestController
public class OrderController {
    @Autowired
    OrderMapper orderMapper;

    @GetMapping("/undoneorder")
    public ArrayList<Order> selectUndoneOrder() {
        return orderMapper.selectUndoneOrder();
    }

    @PutMapping("/order/{number}")
    public void changeOrderStatusByNumber(@PathVariable("number") int number) {
        orderMapper.changeOrderStatusByNumber(number);
    }

    @GetMapping("/doneorder")
    public ArrayList<Order> selectDoneOrder() {
        return orderMapper.selectDoneOrder();
    }

    @GetMapping("/order/{tableNumber}/{time}")
    public Order selecetOrderByTableNumberAndTime(@PathVariable("tableNumber") int tableNumber, @PathVariable("time") int time) {
        return orderMapper.selecetOrderByTableNumberAndTime(tableNumber, time);
    }

    @PostMapping("/order")
    public void insertOrder(@RequestBody Order order) {
        order.setStatus(0);
        orderMapper.insertOrder(order);
    }
}
