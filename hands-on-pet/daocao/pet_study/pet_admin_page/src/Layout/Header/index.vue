<template>
    <div class="header_container">
        <!-- 面包屑 -->
        <div class="header_left">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item v-for="(item,index) in routerListVue" :key="index">
                    {{item}}
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="header_right">
            <!-- 头像 -->
            <div class="avatar">
                <el-image :src="avatar" class="avatar_img"/>
            </div>
            <div>
                <el-dropdown>
                    <span class="el-dropdown-link">
                    {{ nickname }}
                    <el-icon class="el-icon--right">
                        <arrow-down />
                    </el-icon>
                    </span>
                    <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item>个人中心</el-dropdown-item>
                        <el-dropdown-item divided>退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </div>
        </div>
    </div>
</template>

<script  setup>
    // example
    // import { ref } from 'vue'
    // const breadcrumbList = ref(["ABC","DEF"]);
    // const avatar = ref("url_avatar");

    // const breadcrumb = ref("")
    // const route = useRoute()
    // const localStorageValue = ref('')
    // console.log(route.path) // 当前路由路径
    // console.log(route.params) // 当前路由参数
    // localStorageValue.value = localStorage.getItem("useMenu")
    // const useMenu = JSON.parse(localStorage.getItem("useMenu"));
    // const routerAllList = useMenu.routerList;
    import { storeToRefs } from "pinia";
    import { useRoute } from 'vue-router'
    // 放到ref里面,pinia提供了storeToRefs()可以将数据取出后放到ref中
    import { useMenuStore } from '@/stores/menu.js'
    import { ref,watch,onMounted} from 'vue'
    import { useUserStore } from "@/stores/user.js";
    // import { matchedRouteKey, useRoute } from 'vue-router'
    // ["菜单","系统菜单"]
    const routerListVue = ref([]) 
    const userStore = useUserStore();
    const menuStore = useMenuStore();
    const route = useRoute();
    // 这个相当于把pinia里面的数据取出来，拆包
    const { routerList }  = storeToRefs(menuStore);
    const {nickname,avatar} = storeToRefs(userStore);
    const routerAllList = routerList
    // 在组件挂载时调用一次 updateTitle 函数
    onMounted(() => {
        updateTitle(routerAllList)
    })

    // 使用 watch 函数监听 $route 对象的变化，并在变化时执行 generateRouterList 函数
    watch(() => route.path,
          () => {//要执行的方法
                 updateTitle(routerAllList)
                 console.log("routerList:",routerList) 
    },{immediate: true,deep: true})
    function updateTitle(routerAllList){
        console.log("当前路径:", route.path)
        // routerListVue.value = routerAllList.value
        //     .filter(item => item.path === route.path)
        //     .flatMap(item=>{return (item.meta.title == item.name)?[item.name]:[item.meta.title,item.name]})
    }
</script>

<style lang="scss" scoped>
.header_container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    box-sizing: border-box;
    height: 100%;

    .header_right {
        display: flex;
        justify-content: space-between;
        align-items: center;
        box-sizing: border-box;
        .avatar {
            cursor: pointer;
            margin-right: 12px;
            .avatar_img {
                width: 30px;
                height: 30px;
            }
        }
        .el-dropdown-link {
            cursor: pointer;
            display: flex;
            align-items: center;
            border: none;
            outline: none; // 取出边框
        }
    }
}
</style>