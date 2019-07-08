<template>
    <div class="container">
        <!--没有订单时-->
        <div class="mt-5" v-show="!currentOrder.length">
            <div class="display-1 text-center">😭</div>
            <div class="display-3 text-center">您的生意</div>
            <div class="display-3 text-center">有些惨淡</div>
            <div class="display-3 text-center">暂无订单</div>
            <div class="display-3 text-center">再加把劲</div>
        </div>
        <!--有订单时-->
        <div v-show="currentOrder.length">
            <table class="table table-striped border text-center">
                <thead class="thead-light">
                <tr>
                    <th scope="col">序号</th>
                    <th scope="col">订单号</th>
                    <th scope="col">桌号</th>
                    <th scope="col">订单详情</th>
                    <th scope="col">总价(￥)</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody class="">
                <tr v-bind:key="currentOrderItem.order_number" v-for="(currentOrderItem,index) in currentOrder">
                    <th scope="row">{{index+1}}</th>
                    <td>{{currentOrderItem.order_number}}</td>
                    <td>{{currentOrderItem.table_number}}</td>
                    <td>
                        <div class="dropdown show">
                            <a class="btn btn-secondary dropdown-toggle btn-sm" href="#" role="button" id="dropdownMenuLink"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                订单明细
                            </a>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                <a class="dropdown-item" href="#" v-bind:key="dish.dish_name"
                                   v-for="dish in currentOrderItem.dishes">{{dish.dish_name}}*{{dish.amount}}</a>
                            </div>
                        </div>
                    </td>
                    <td>{{(currentOrderItem.price).toFixed(2)}}</td>
                    <td>
                        <button class="btn btn-secondary btn-sm" @click="finish(currentOrderItem)">完成订单</button>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="h3 text-center font-weight-light">
                🕗您的顾客正在耐心等待，请尽快完成订单
            </div>
        </div>
    </div>
</template>

<script>
    import $ from 'jquery'

    export default {
        name: "CurrentOrder",
        data: function () {
            return {
                currentOrder: [],

            }
        },
        mounted() {
            $(document).ready(function () {
                $(function () {
                    $('[data-toggle="1"]').tooltip()
                })
            })
            this.axios.get("http://localhost:8080/undoneorder").then(response => {
                this.currentOrder = response.data;
                for (let currentOrderItem of this.currentOrder) {
                    this.axios.get("http://localhost:8080/orderdish/" + currentOrderItem.order_number).then(respronse => {
                        currentOrderItem.dishes = respronse.data;
                        this.$forceUpdate();
                    })
                }
            });
        },
        methods: {
            finish(currentOrderItem) {
                //发送更改状态请求
                this.axios.put("http://localhost:8080/order/" + currentOrderItem.order_number).then(response => {
                    if (response == null) {
                        alert("failed")
                    }
                    this.axios.get("http://localhost:8080/undoneorder").then(response => {
                        this.currentOrder = response.data;
                        for (let currentOrderItem of this.currentOrder) {
                            this.axios.get("http://localhost:8080/orderdish/" + currentOrderItem.order_number).then(respronse => {
                                currentOrderItem.dishes = respronse.data;
                                this.$forceUpdate();
                            })
                        }
                    });
                })
                this.$forceUpdate();

            }
        }
    }
</script>

<style scoped>

</style>