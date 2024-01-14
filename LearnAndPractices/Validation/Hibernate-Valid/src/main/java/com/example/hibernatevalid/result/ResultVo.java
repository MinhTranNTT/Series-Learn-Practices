package com.example.hibernatevalid.result;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) //Only items that are not empty will be added to the serialization,
// which means that only non-null parameters will be returned in the returned information.
public class ResultVo {
    /**
     * Whether the backend is processed successfully
     */
    private boolean success;
    /**
     * Return status code
     */
    private String code;
    /**
     * The processed information returned
     */
    private String msg;
    /**
     * Return value to front end
     */
    private Object data;

    /**
     * successful return
     * @return
     */
    public static ResultVo success(){
        ResultVo resultVo = new ResultVo();
        resultVo.setSuccess(true);
        return resultVo;
    }

    /**
     * successful return
     * @param data
     * @return
     */
    public static ResultVo success(Object data){
        ResultVo resultVo = new ResultVo();
        resultVo.setSuccess(true);
        resultVo.setData(data);
        return resultVo;
    }

    /**
     * Return on failure
     * @param errorCode
     * @return
     */
    public static ResultVo fail(ErrorCode errorCode){
        ResultVo resultVo = new ResultVo();
        resultVo.setSuccess(false);
        resultVo.setCode(errorCode.getCode());
        resultVo.setMsg(errorCode.getMsg());
        return resultVo;
    }

    /**
     * Return on failure
     * @param errorCode
     * @param data
     * @return
     */
    public static ResultVo fail(ErrorCode errorCode,Object data){
        ResultVo resultVo = new ResultVo();
        resultVo.setSuccess(false);
        resultVo.setCode(errorCode.getCode());
        resultVo.setMsg(errorCode.getMsg());
        resultVo.setData(data);
        return resultVo;
    }
}
