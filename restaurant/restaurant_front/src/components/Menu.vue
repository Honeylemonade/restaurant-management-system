<template>
    <div class="container">
        <!--全部菜品-->
        <div>
            <table class="table table-striped border text-center">
                <thead class="thead-light">
                <tr>
                    <th scope="col">序号</th>
                    <th scope="col">菜品名称</th>
                    <th scope="col">单价(￥)</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-bind:key="menuItem.dish_number" v-for="(menuItem,index) in menu">
                    <th scope="row">{{index+1}}</th>
                    <td>
                        <span class="badge badge-warning" v-show="menuItem.discount!=10">
                            现{{menuItem.discount}}折
                        </span>
                        {{menuItem.dish_name}}
                    </td>
                    <td>{{(menuItem.price*menuItem.discount*0.1).toFixed(2)}}</td>
                    <td>
                        <button class="btn btn-dark btn-sm" v-on:click="add(menuItem)">添加</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <!--我的订单-->
        <hr>
        <div>
            <h3 class="font-weight-light">📃我的订单</h3>
            <div v-bind:key="myOrderItem.dish_number" v-for="myOrderItem in myOrder">
                <div class="row my-1">
                    <li class="col-9">
                        <span>{{myOrderItem.dish_name}}</span>
                    </li>
                    <div class="col">
                        <button class="btn btn-dark btn-sm" v-on:click="minus(myOrderItem)">➖</button>
                        <span class="mx-2 ">×<ins class="h5 font-weight-light"> {{myOrderItem.amount}} </ins>份</span>
                        <button class="btn btn-dark btn-sm" v-on:click="plus(myOrderItem)">➕</button>
                    </div>
                </div>
            </div>
            <hr>
            <div class="row justify-content-between my-3">
                <div class="col-4">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text">桌号</label>
                        </div>
                        <select class="custom-select" v-model="tableNumber">
                            <option value="0">请选择桌号</option>
                            <option value="1">1号桌</option>
                            <option value="2">2号桌</option>
                            <option value="3">3号桌</option>
                            <option value="4">4号桌</option>
                            <option value="5">5号桌</option>
                        </select>
                    </div>
                </div>
                <div class="col-4 text-center">
                    <div class="font-weight-light h4">总价格:{{(totalPrice).toFixed(2)}}元</div>
                </div>
            </div>
            <div class="row text-right justify-content-center">
                <button class=" col-4 btn btn-success" @click="submitOrder()">提交订单</button>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Menu",
        data: function () {
            return {
                menu: [],
                myOrder: [],
                tableNumber: 0,
            }
        },
        mounted() {
            this.axios.get("http://localhost:8080/dish").then(response => {
                this.menu = response.data;
            })
        },
        computed: {
            totalPrice: function () {
                var total = 0;
                for (var number in this.myOrder) {
                    total += this.myOrder[number].price * this.myOrder[number].amount * 0.1 * this.myOrder[number].discount;
                }
                return total;
            }
        },
        methods: {
            add(menuItem) {
                var flag = 0;
                for (var number in this.myOrder) {
                    if (this.myOrder[number].dish_number == menuItem.dish_number) {
                        this.myOrder[number].amount++;
                        flag++;
                    }
                }
                if (flag == 0) {
                    menuItem.amount = 1;
                    this.myOrder.push(menuItem);
                }
                //触发计算属性
                this.myOrder.push(1);
                this.myOrder.pop(1);
                this.$forceUpdate();
            },
            plus(myOrderItem) {
                myOrderItem.amount++;
                //触发计算属性
                this.myOrder.push(1);
                this.myOrder.pop(1);
                this.$forceUpdate();
            },
            minus(myOrderItem) {
                if (myOrderItem.amount == 1) {
                    this.myOrder.pop(myOrderItem);
                } else {
                    myOrderItem.amount--;
                    //触发计算属性
                    this.myOrder.push(1);
                    this.myOrder.pop(1);
                    this.$forceUpdate();
                }
            },
            submitOrder() {
                if (this.tableNumber == 0 || this.myOrder.length == 0) {
                    alert("请将信息填写完整");
                } else {
                    var time = Math.round(new Date() / 1000);
                    var currentOrder;
                    //插入订单
                    var newOrder = {
                        "table_number": this.tableNumber,
                        "time": time,
                        "price": this.totalPrice
                    }
                    this.axios.post("http://localhost:8080/order", newOrder).then(response => {
                        //获取订单号
                        if (!response) {
                            alert(response);
                        }
                        var url = "http://localhost:8080/order/" + this.tableNumber + "/" + time;
                        this.axios.get(url).then(response => {
                            currentOrder = response.data;
                            //插入订单菜品关系
                            for (let myDish of this.myOrder) {
                                var itme = {
                                    "order_number": currentOrder.order_number,
                                    "dish_number": myDish.dish_number,
                                    "amount": myDish.amount
                                }
                                this.axios.post("http://localhost:8080/orderdish", itme).then(response => {
                                    if (response !== null && myDish === this.myOrder[this.myOrder.length - 1]) {
                                        alert("订单提交成功,请耐心等待");
                                        this.$router.push({path: "/"})
                                    }

                                })
                            }
                        })
                    });

                }


            },
        }
    }
</script>

<style scoped>

</style>