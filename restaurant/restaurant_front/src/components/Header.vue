<template>
    <div>
        <nav class="navbar navbar-expand bg-dark">
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item h4 text-dark">
                        😉
                    </li>
                    <li class="nav-item mt-1">
                        <router-link class="text-white font-weight-lighter m-3" to="/">🍚主页</router-link>
                    </li>
                    <li class="nav-item mt-1">
                        <router-link class="text-white font-weight-lighter mx-3" to="/Menu">🛒点餐服务</router-link>
                    </li>
                    <li class="nav-item mt-1">
                        <router-link class="text-white font-weight-lighter mx-3" to="/CurrentOrder">📝当前订单</router-link>
                    </li>
                    <li class="nav-item mt-1">
                        <router-link class="text-white font-weight-lighter mx-3" to="/Bill">📊历史流水</router-link>
                    </li>
                    <li class="nav-item mt-1">
                        <router-link class="text-white font-weight-lighter mx-3" to="/DishSetter">⚙菜品设置</router-link>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</template>

<script>
    export default {
        name: "Header"
    }
</script>

<style scoped>

</style>