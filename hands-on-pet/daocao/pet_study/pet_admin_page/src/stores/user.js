// 引入pinia
import { defineStore } from 'pinia'
// 定义store并导出
export const useUserStore = defineStore('user', {
    // 数据定义
    state: () => ({
        id:undefined,
        nickname:undefined,
        avatar:undefined
    }),
    // 获取数据
    getters: {
        Number: (state) => state.id,
        String: (state) => state.nickname,
        String: (state) => state.avatar,
    },
    // 操作数据
    actions: {
        setUserInfo(data){
            this.id = data.id;
            this.nickname = data.nickname;
            this.avatar = data.avatar;
        },
    },
    // 使用持久化
    // persist:{
    //   enabled:true,
    //   storage:localStorage,
    //   key:'useMenu',
    //   path:['menuList','routerList']
    // }
})