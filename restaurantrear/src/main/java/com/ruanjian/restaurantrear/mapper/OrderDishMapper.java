package com.ruanjian.restaurantrear.mapper;

import com.ruanjian.restaurantrear.entity.OrderDish;
import com.ruanjian.restaurantrear.entity.OrderDishRank;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * OrderDishMapper:
 *
 * @author XvYanPeng
 * @date 2019/6/25 18:37
 */
@Component
@Mapper
public interface OrderDishMapper {
    void insertOrderDish(OrderDish orderDish);

    ArrayList<OrderDishRank> selectOrderDishNameByOrderNumber(int number);

    ArrayList<OrderDishRank> selectOrderDishRank();

}
