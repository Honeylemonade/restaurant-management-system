package com.ruanjian.restaurantrear.controller;

import com.ruanjian.restaurantrear.entity.OrderDish;
import com.ruanjian.restaurantrear.entity.OrderDishRank;
import com.ruanjian.restaurantrear.mapper.OrderDishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * OrderDishController:
 *
 * @author XvYanPeng
 * @date 2019/6/25 18:38
 */
@CrossOrigin
@RestController
public class OrderDishController {
    @Autowired
    OrderDishMapper orderDishMapper;

    @PostMapping("/orderdish")
    public void insertOrderDish(@RequestBody OrderDish orderDish) {
        orderDishMapper.insertOrderDish(orderDish);
    }

    @GetMapping("/orderdish/{number}")
    public ArrayList<OrderDishRank> selectOrderDishNameByOrderNumber(@PathVariable("number") int number) {
        return orderDishMapper.selectOrderDishNameByOrderNumber(number);
    }

    @GetMapping("/orderdishrank")
    public ArrayList<OrderDishRank> selectOrderDishRank() {
        return orderDishMapper.selectOrderDishRank();
    }
}
