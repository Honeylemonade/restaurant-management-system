<template>
    <div id="app">
        <Header></Header>
        <router-view></router-view>
        <Footer></Footer>
    </div>
</template>

<script>
    //局部注册组建
    import Header from './components/Header'
    import Footer from './components/Footer'

    export default {
        name: 'app',
        components: {
            Header,
            Footer,
        },
    }
</script>

<style>
    #app {
        height: 100%;
    }
</style>
