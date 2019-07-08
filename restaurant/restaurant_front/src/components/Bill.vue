<template>
    <div class="container">
        <table class="table table-striped border text-center">
            <thead class="thead-light">
            <tr>
                <th scope="col">序号</th>
                <th scope="col">订单号</th>
                <th scope="col">桌号</th>
                <th scope="col">下单时间</th>
                <th scope="col">总价(￥)</th>
            </tr>
            </thead>
            <tbody>
            <tr v-bind:key="billItem.order_number" v-for="(billItem,index) in bill">
                <th scope="row">{{index+1}}</th>
                <td>{{billItem.order_number}}</td>
                <td>{{billItem.table_number}}</td>
                <td>{{getLocalTime(billItem.time)}}</td>
                <td>{{(billItem.price).toFixed(2)}}</td>
            </tr>
            </tbody>
        </table>
        <!--合计-->
        <div class="h3 font-weight-light text-right my-4">💴共收入：{{(income).toFixed(2)}}元</div>
        <hr>
        <!--菜品排名-->

        <table class="table table-striped border text-center">
            <thead class="thead-light">
            <tr>
                <th scope="col">排名</th>
                <th scope="col">菜品名称</th>
                <th scope="col">销售数量(份)</th>
            </tr>
            </thead>
            <tbody>
            <tr v-bind:key="dishRankItem.dish_name" v-for="(dishRankItem,index) in dishRank">
                <th scope="row">{{index+1}}</th>
                <td>{{dishRankItem.dish_name}}</td>
                <td>{{dishRankItem.amount}}</td>
            </tr>
            </tbody>
        </table>


    </div>
</template>

<script>
    export default {
        name: "Bill",
        data: function () {
            return {
                bill: [],
                dishRank: [],
            }
        },
        mounted() {
            this.axios.get("http://localhost:8080/doneorder").then(response => {
                this.bill = response.data;
            })
            this.axios.get("http://localhost:8080/orderdishrank").then(response => {
                this.dishRank = response.data;
            })
        },
        computed: {
            income() {
                var income = 0;
                for (let billItem of this.bill) {
                    income += billItem.price;
                }
                return income;
            }
        },
        methods: {
            getLocalTime(nS) {
                return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/, ' ');
            }
        }
    }
</script>

<style scoped>

</style>