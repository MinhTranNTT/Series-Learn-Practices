<template>
    <div class="common-layout">
        <el-container>
            <el-aside class="aside" :style="{height:height+'px',minHeight:minHeight+'px'}" >
                <Aside />
            </el-aside>
            <el-container>
                <el-header>
                    <Header />
                </el-header>
                <el-main>
                    <Main />
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script setup>
    import Aside from './Aside/index.vue';
    import { ref, onMounted } from 'vue';
    import Header from './Header/index.vue';
    import Main from './Main/index.vue';

    const height = ref(0)
    const minHeight = ref(0)
    // 监听页面缩放事件，这是一个钩子函数
    onMounted(() => {
        getHeight();
        minHeight.value = document.documentElement.clientHeight;
        window.addEventListener('resize', getHeight)
    })
    // 获取文档高度
    function getHeight() {
        height.value = document.documentElement.clientHeight;
        // console.log(height.value);
    }
</script>

<style lang="scss" scoped>
.aside {
    background-color: #545c64;
}
</style>