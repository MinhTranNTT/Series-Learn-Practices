<template>
    <el-tabs
      v-model="activeTab"
      type="card"
      editable
      class="demo-tabs"
      @tab-change="gotoTab"
      @tab-remove="removeTab"
      @edit="handleTabsEdit"
    >
      <el-tab-pane
        v-for="item in tabList"
        :key="item.lastPath"
        :label="item.title"
        :name="item.path"
      >
       <RouterView/>
      </el-tab-pane>
    </el-tabs>
  </template>

<script setup>
    import { RouterView } from 'vue-router'
    import { ref } from 'vue'
    import { useMenuStore } from "@/stores/menu.js"
    import { storeToRefs } from "pinia";
    import {useRouter} from "vue-router";
    // 放到ref里面,pinia提供了storeToRefs()可以将数据取出后放到ref中
    const menuStore = useMenuStore();
    const router = useRouter();
    const { tabList,activeTab }  = storeToRefs(menuStore);

    function gotoTab(path){
      menuStore.setActive(path)
      router.push(path)
    }
    function removeTab(path){
      //console.log("path:",path)
      //console.log("menuStore.activeTab:",menuStore.activeTab)
      if(path==menuStore.activeTab){
           menuStore.removeTabList(path)
           //console.log("tabList.length:",tabList.length)
           //console.log("tabList:",tabList)
           if(tabList.value.length>0){
            let lastPath = tabList.value[tabList.value.length-1];
            //console.log("lastPath:",lastPath)
            menuStore.setActive(lastPath.path)
            router.push(lastPath.path)
        }
      }
      else{
        menuStore.removeTabList(path)
       } 
    }
</script>

<style lang="scss" scoped>

</style>