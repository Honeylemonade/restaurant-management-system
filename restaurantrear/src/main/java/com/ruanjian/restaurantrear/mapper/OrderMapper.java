package com.ruanjian.restaurantrear.mapper;

import com.ruanjian.restaurantrear.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * OrderMapper:
 *
 * @author XvYanPeng
 * @date 2019/6/25 17:04
 */
@Component
@Mapper
public interface OrderMapper {
    ArrayList<Order> selectUndoneOrder();

    void changeOrderStatusByNumber(int number);

    ArrayList<Order> selectDoneOrder();

    Order selecetOrderByTableNumberAndTime(int tableNumber, int time);

    void insertOrder(Order order);
}
