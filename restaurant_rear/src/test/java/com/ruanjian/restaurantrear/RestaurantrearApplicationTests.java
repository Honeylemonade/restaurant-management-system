package com.ruanjian.restaurantrear;

import com.ruanjian.restaurantrear.mapper.DishMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantrearApplicationTests {
@Autowired
    DishMapper dishMapper;

    @Test
    public void contextLoads() {
        System.out.println(dishMapper.selectAllDishes());
    }

}
