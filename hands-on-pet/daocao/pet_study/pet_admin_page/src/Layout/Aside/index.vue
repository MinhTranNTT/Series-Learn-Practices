<template>
    <div >    
        <el-menu
            active-text-color="#ffd04b"
            background-color="#545c64"
            text-color="#fff"    
        >
            <template v-for=" menu  in menuList" :key="menu.path">
                <!-- 判断是否有子元素 -->
                <el-sub-menu v-if="hasChildren(menu)" :index="menu.path">
                    <template  #title>
                        <svg-icon 
                            v-if="menu.icon"
                            slots = "prefix"
                            :name="menu.icon" 
                            width="18px" 
                            height="18px"
                        />
                        <span>{{menu.menuName}}</span>
                    </template>
                    <!-- 渲染子菜单 -->
                    <el-menu-item-group>
                        <el-menu-item 
                            v-for="children in menu.children"
                            :key="children.path"
                            :index="children.path"
                            @click="handleRouter(children)" 
                            >
                                <svg-icon
                                    v-if="children.icon"
                                    slots = "prefix"
                                    :name="children.icon" 
                                    width="18px" 
                                    height="18px"
                                />
                                <span>{{children.menuName}}</span>
                        </el-menu-item>
                    </el-menu-item-group>
                </el-sub-menu>
                <!-- 没有子菜单 -->
                <el-menu-item v-else :index="menu.path" @click="handleRouter(menu)">
                        <svg-icon
                            v-if="menu.icon"
                            slots = "prefix"
                            :name="menu.icon" 
                            width="18px"
                            height="18px"
                        />
                    <span>{{menu.menuName}}</span>
                </el-menu-item>    
            </template>
        </el-menu>    
    </div>
</template>

<script setup>
    import { useMenuStore } from "@/stores/menu.js"
    import { storeToRefs } from "pinia";
    import {useRouter} from "vue-router";

    // 放到ref里面,pinia提供了storeToRefs()可以将数据取出后放到ref中
    const menuStore = useMenuStore();
    const router = useRouter();
    const { menuList }  = storeToRefs(menuStore);
    console.log("menuList=====>",menuList);
    // 判断是否有子菜单
    function hasChildren(menu){
        if(menu.children && menu.children.length>0) return true;
        else return false;
    }
    // 跳转路由
    function handleRouter(menus){
        console.log("menus:",menus)
        let exisetab = menuStore.tabList.filter(item =>item.path == menus.path)
        if(exisetab == null || exisetab.length==0){
            let data = {title:menus.menuName,path:menus.path}
            menuStore.addTabList(data)
        }
        menuStore.setActive(menus.path)
        router.push(menus.path);
    }
</script>

<style lang="scss" scoped>

</style>