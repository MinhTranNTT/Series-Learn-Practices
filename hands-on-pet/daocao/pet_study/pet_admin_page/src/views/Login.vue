<template>
    <div class="login_container">
        <div class="login_form">
            <h3 class="title">Login Page</h3>
            <el-form ref="formRef" :model="loginForm" label-width="auto"> 
                <el-form-item >
                    <el-input v-model="loginForm.account"  placeholder="username" >
                        <template #prefix>
                            <el-icon class="el-input__icon"><User /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                
                <el-form-item >
                    <el-input v-model="loginForm.password" placeholder="password" >
                        <template #prefix>
                            <el-icon class="el-input__icon"><Lock /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>

                <div class="rememberMe">
                    <!-- remember me -->
                    <el-checkbox v-model="loginForm.rememberMe" label="remember me" size="large"/>
                    <!-- forget password -->
                    <el-text class="forgetpassword" type="primary">Forgot password</el-text>
                </div>
                <el-divider>XinChao</el-divider>
                <div class="other_login">
                    <el-icon class="other_login_item"><ChromeFilled /></el-icon>
                    <el-icon class="other_login_item"><ChromeFilled /></el-icon>
                    <el-icon class="other_login_item"><ChromeFilled /></el-icon>
                </div>
                <el-form-item>
                        <!-- 按钮 -->
                        <el-button style="width: 100%;" type="primary" @click="handleLogin">Login</el-button>
                </el-form-item>  

            </el-form>
        </div>
    </div>
</template>

<script setup>
    // import ref
    import { ref } from 'vue';
    
    // import login method
    import { login } from '@/api/auth/index.js';
    import { searchSelfRouter } from '@/api/user/index.js';
    import { setToken } from '@/utils/token';
    import { useMenuStore } from '@/stores/menu.js';
    import { useRouter } from 'vue-router';

    const router = useRouter();
    // 声明表单绑定值
    const menuStore = useMenuStore();

    const loginForm = ref({
        account: undefined,
        password: undefined,
        rememberMe: undefined
    });

    function handleLogin() {
        login(loginForm.value).then((res)=>{
            // console.log("handleLogin() ====>",res);
            // 判断是否成功
            if(res.data.code == 200){
              // console.log("Okie====>",res);
              // 将用户token存入到pinia/sessionStorage中
                setToken("daocaoToken",res.data.token);
                // TODO 获取用户的路由访问权限
                searchSelfRouter().then(res=>{
                    console.log("res=====>",res);
                    // 将路由信息存储到pinia中
                    menuStore.setMenuList(res.data.data);
                    // 跳转页面  /index
                    // 1.在路由守卫上渲染动态路由
                    // 2.开发项目主页面【左侧导航，头部，主体部分】
                    router.push("/index");
                }); 
                // searchUserInfo().then(res=>{
                //     // 存储到pinia中
                //     if(res.data.code == 200){
                //         userStore.setUserInfo(res.data.data);
                //     }
                // });
                console.log("登录成功!");

            }
        }).catch((err) => {
          console.log("Catch");
        })
    };

    // function handleLogin(){
    //     // 调用login方法
    //     login(loginForm.value).then((res)=>{
    //         console.log("登录====>",res);
    //         // 判断是否成功
    //         if(res.data.code == 200){
    //             // 将用户token存入到pinia/sessionStorage中
    //             setToken("daocaoToken",res.data.token);
    //             // TODO 获取用户的路由访问权限
    //             searchSelfRouter().then(res=>{
    //                 console.log("res=====>",res);
    //                 // 将路由信息存储到pinia中
    //                 menuStore.setMenuList(res.data.data);
    //                 // 跳转页面  /index
    //                 // 1.在路由守卫上渲染动态路由
    //                 // 2.开发项目主页面【左侧导航，头部，主体部分】
    //                 router.push("/index")
    //             }); 
    //             searchUserInfo().then(res=>{
    //                 // 存储到pinia中
    //                 if(res.data.code == 200){
    //                     userStore.setUserInfo(res.data.data);
    //                 }
    //             });
    //             console.log("登录成功!");
    //         }
    //     })
    // };

</script>

<style lang="scss" scoped>
.login_container {
  // 背景图
  background-image: url("../assets/bgimg/kiemlai.png");
  background-size: 100%;
  height: 100vh;
  display: flex;
  justify-content: flex-end;
  .login_form {
    display: flex;
    justify-content: center;
    align-items: center;
    //设置换行
    flex-direction: column;
    width: 50%;
    background-color: #fff;

    .title {
      margin-bottom: 20px;
    }
    .rememberMe {
      display: flex;
      justify-content: space-between;
      align-items: center;
      .forgetpass {
        cursor: pointer;
      }
    }
    // 其他登录
    .other_login {
      display: flex;
      justify-content: center;
      margin-bottom: 20px;
      .other_login_item {
        margin-right: 30px;
        margin-left: 30px;
        cursor: pointer;
      }
    }
  }

  // 设置form的宽度
  .el-form {
    width: 60%;
  }
  .el-form-item {
    width: 100%;
  }
}
</style>