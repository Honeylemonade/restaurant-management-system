package com.ruanjian.restaurantrear.entity;

/**
 * DishRank:
 *
 * @author XvYanPeng
 * @date 2019/6/25 19:00
 */
public class OrderDishRank {
    private String dish_name;
    private int amount;

    @Override
    public String toString() {
        return "DishRank{" +
                "dish_name='" + dish_name + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public OrderDishRank() {
    }

    public OrderDishRank(String dish_name, int amount) {
        this.dish_name = dish_name;
        this.amount = amount;
    }
}
