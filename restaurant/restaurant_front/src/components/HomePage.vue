<template>
    <div class="container text-center mt-5">
        <div class="display-4 mt-5">欢迎使用餐饮管理系统</div>
        <div class="h2 font-weight-light mb-5">Welcome to the catering management system</div>
        <hr>
        <div class="row justify-content-center">
            <div class="text-left col-10">
                <li class="my-3">
                    <router-link class="text-dark font-weight-light mx-3 h1" to="/Menu">🛒点餐服务(Menu)</router-link>
                </li>
                <li class="my-3">
                    <router-link class="text-dark font-weight-light mx-3 h1" to="/CurrentOrder">📝当前订单(CurrentOrder)</router-link>
                </li>
                <li class="my-3">
                    <router-link class="text-dark font-weight-light mx-3 h1" to="/Bill">📊历史流水(Bill)</router-link>
                </li>
                <li class="my-3">
                    <router-link class="text-dark font-weight-light mx-3 h1" to="/DishSetter">⚙菜品设置(DishSetter)</router-link>
                </li>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "HomePage"
    }
</script>

<style scoped>

</style>