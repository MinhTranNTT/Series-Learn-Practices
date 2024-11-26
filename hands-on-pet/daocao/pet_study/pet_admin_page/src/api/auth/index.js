// 请求接口
import request from '@/utils/request.js'
// 登录接口

export function login(data){
    return request({
        url:'auth/sys',
        method:"POST",
        data:data
    })
}