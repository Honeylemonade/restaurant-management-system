package com.ruanjian.restaurantrear.mapper;

import com.ruanjian.restaurantrear.entity.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * DishMapper:
 *
 * @author XvYanPeng
 * @date 2019/6/25 7:43
 */
@Component
@Mapper
public interface DishMapper {

    ArrayList<Dish> selectAllDishes();

    Dish selectDishByNumber(int number);

    void deleteDishByNumber(int number);

    void insertDish(Dish dish);

    void updateDishByNumber(Dish dish);
}
