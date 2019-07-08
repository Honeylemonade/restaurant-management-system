package com.ruanjian.restaurantrear.controller;

import com.ruanjian.restaurantrear.entity.Dish;
import com.ruanjian.restaurantrear.mapper.DishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * TestController:
 *
 * @author XvYanPeng
 * @date 2019/6/25 7:34
 */
@CrossOrigin
@RestController
public class DishController {
    @Autowired
    DishMapper dishMapper;

    @GetMapping("/dish")
    public ArrayList<Dish> selectAllDishes() {
        return dishMapper.selectAllDishes();
    }

    @GetMapping("/dish/{number}")
    public Dish selectDishByNumber(@PathVariable("number") int number) {
        return dishMapper.selectDishByNumber(number);
    }

    @DeleteMapping("/dish/{number}")
    public void deleteDishByNumber(@PathVariable("number") int number) {
        dishMapper.deleteDishByNumber(number);
    }

    @PostMapping("/dish")
    public void insertDish(@RequestBody Dish dish) {
        dishMapper.insertDish(dish);
    }

    @PutMapping("/dish")
    public void updateDishByNumber(@RequestBody Dish dish) {
        dishMapper.updateDishByNumber(dish);
    }
}
