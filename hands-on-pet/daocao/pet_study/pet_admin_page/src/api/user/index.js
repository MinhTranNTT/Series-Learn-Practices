// Request interface
import request from '@/utils/request.js';

// 登录接口
export function searchSelfRouter(data){
    return request({
        url:'/sys/menu/self',
        method:"GET",
        data:data
    })
}