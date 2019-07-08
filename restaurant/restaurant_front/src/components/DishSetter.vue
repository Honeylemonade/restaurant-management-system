<template>
    <div class="container">
        <table class="table table-striped border text-center">
            <thead class="thead-light">
            <tr>
                <th scope="col">序号</th>
                <th scope="col">菜品名称</th>
                <th scope="col">折扣 <span class="text-muted small">(10为原价)</span></th>
                <th scope="col">单价</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <tr v-bind:key="dishItem.dish_number" v-for="(dishItem,index) in dish">
                <th scope="row">{{index+1}}</th>
                <td><input type="text" class="form-control form-control-sm" v-model="dishItem.dish_name"
                           v-on:blur="change(dishItem)"></td>
                <td><input type="text" class="form-control form-control-sm" v-model="dishItem.discount"
                           v-on:blur="check(dishItem),change(dishItem)"></td>
                <td><input type="text" class="form-control form-control-sm" v-model="dishItem.price"
                           v-on:blur="check(dishItem),change(dishItem)"></td>
                <td>
                    <button class="btn btn-danger" v-on:click="deleteDish(dishItem)">删除</button>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="row">
            <div class="col">
                <div class="input-group input-group-sm">
                    <div class="input-group-prepend">
                        <span class="input-group-text">菜品名称</span>
                    </div>
                    <input type="text" class="form-control" v-model="newDish.dish_name">
                </div>
            </div>
            <div class="col">
                <div class="input-group input-group-sm">
                    <div class="input-group-prepend">
                        <span class="input-group-text">折扣</span>
                    </div>
                    <input type="text" class="form-control"
                           v-on:blur="check(this)" v-model.number="newDish.discount">
                </div>
            </div>
            <div class="col">
                <div class="input-group input-group-sm">
                    <div class="input-group-prepend">
                        <span class="input-group-text">单价</span>
                    </div>
                    <input type="text" class="form-control"
                           v-on:blur="check(this)" v-model.number="newDish.price">
                </div>
            </div>
            <div class="col-2 text-center">
                <button class="btn btn-success" @click="add">添加</button>
            </div>

        </div>
    </div>
</template>

<script>
    export default {
        name: "DishSetter",
        data: function () {
            return {
                dish: [],
                newDish: {
                    dish_name: '',
                    discount: 10,
                    price: 10
                },
            }
        },
        mounted() {
            this.axios.get("http://localhost:8080/dish").then(response => {
                this.dish = response.data;
            })
        },
        methods: {
            change(dishItem) {
                if(!dishItem.dish_name){
                    alert("不能为空");
                    this.axios.get("http://localhost:8080/dish").then(response => {
                        this.dish = response.data;
                    })
                }else {
                    var dish = {
                        "dish_number": dishItem.dish_number,
                        "dish_name": dishItem.dish_name,
                        "discount": dishItem.discount,
                        "price": dishItem.price
                    }
                    this.axios.put("http://localhost:8080/dish", dish);
                }
            },
            deleteDish(dishItem) {
                this.axios.delete("http://localhost:8080/dish/" + dishItem.dish_number).then(response => {
                    if (response == null) {
                        alert("failed");
                    }
                    this.axios.get("http://localhost:8080/dish").then(response => {
                        this.dish = response.data;
                    })
                })
            },
            add() {
                if(!this.newDish.dish_name){
                    alert("菜品名称不能为空")
                }else {
                    this.axios.post("http://localhost:8080/dish", this.newDish).then(response => {
                        if (response) {
                            this.axios.get("http://localhost:8080/dish").then(response => {
                                this.dish = response.data;
                            })
                        }
                    }).catch(error=>{
                        if (error){
                            alert("此菜品已存在")
                        }
                    })
                }
            },
            check(e) {
                //处理将添加的菜品
                if (!e) {
                    this.newDish.discount = Math.round(this.newDish.discount);
                    if (this.newDish.discount < 1) {
                        this.newDish.discount = 1;
                    }
                    if (this.newDish.discount > 10 || Number.isNaN(this.newDish.discount)) {
                        this.newDish.discount = 10;
                    }
                    this.newDish.price = Math.round(this.newDish.price);
                    if (this.newDish.price < 1 || Number.isNaN(this.newDish.price)) {
                        this.newDish.price = 1;
                    }
                    if (this.newDish.price > 9999) {
                        this.newDish.price = 9999;
                    }

                } else {
                    //处理已存在菜品
                    e.discount = Math.round(e.discount);
                    if (e.discount < 1) {
                        e.discount = 1;
                    }
                    if (e.discount > 10 || Number.isNaN(e.discount)) {
                        e.discount = 10;
                    }
                    e.price = Math.round(e.price);
                    if (e.price < 1 || Number.isNaN(e.price)) {
                        e.price = 1;
                    }
                    if (e.price > 9999) {
                        e.price = 9999;
                    }

                }
            }
        },

    }
</script>

<style scoped>

</style>