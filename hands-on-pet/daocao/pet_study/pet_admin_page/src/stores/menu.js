import { searchSelfRouter } from '@/api/user/index.js';
import { defineStore } from 'pinia'

export const useMenuStore = defineStore('menu', {
    // 数据定义
    state: () => ({
        menuList: [],
        // routerList:[], // 动态路由数据，也就是左侧菜单的路由信息
        // tabList:[{title:'首页',path:'/index'}],// 存放tab列表的数据
        // activeTab:'/index'
    }),

    // 获取数据
    getters: {
        Array: (state) => state.menuList,
        // Array: (state) => state.routerList,
        // Array: (state) => state.tabList,
        // String: (state) => state.activeTab,
    },
    // 操作数据
    actions: {
        setMenuList(data) {
            console.log("setMenuList====>", data);
            this.menuList = data;
        },
    }
});