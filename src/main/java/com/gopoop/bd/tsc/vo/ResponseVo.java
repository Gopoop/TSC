package com.gopoop.bd.tsc.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVo<T> {
    private int code;
    private String message;
    private T data;


    public static ResponseVo successResp(Object data,String msg){
        return ResponseVo.builder().data(data).message(msg).code(0).build();
    }

    public static ResponseVo successResp(Object data){
        return ResponseVo.builder().data(data).message("请求处理成功").code(0).build();
    }

    public static ResponseVo successResp(){
        return ResponseVo.builder().message("请求处理成功").code(0).build();
    }

    public static ResponseVo failResp(String msg){
        return ResponseVo.builder().message(msg).code(1).build();
    }

}
