package com.hsbc.cmb.connect.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseEntity {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    //把构造方法私有
    private ResponseEntity() {}

    //成功静态方法
    public static ResponseEntity success() {
        ResponseEntity r = new ResponseEntity();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS.getResponseCode());
        r.setMessage("成功");
        return r;
    }

    //失败静态方法
    public static ResponseEntity fail() {
        ResponseEntity r = new ResponseEntity();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR.getResponseCode());
        r.setMessage("失败");
        return r;
    }

//    public ResponseEntity success(Boolean success){
//        this.setSuccess(success);
//        return this;
//    }
//
    public ResponseEntity message(String message){
        this.setMessage(message);
        return this;
    }

    public ResponseEntity code(Integer code){
        this.setCode(code);
        return this;
    }

    public ResponseEntity data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public ResponseEntity data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
