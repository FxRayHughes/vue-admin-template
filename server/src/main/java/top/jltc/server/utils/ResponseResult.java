package top.jltc.server.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {

  private Integer code;

  private String msg;

  private T data;

  public ResponseResult(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public ResponseResult(Integer code, T data) {
    this.code = code;
    this.data = data;
  }

  public static <T> ResponseResult<T> error(T string) {
    return new ResponseResult<>(500, string);
  }

  public static <T> ResponseResult<T> success(T data) {
    return new ResponseResult<>(200, data);
  }

  public static <T> ResponseResult<T> success(T data, String msg) {
    ResponseResult<T> tResponseResult = new ResponseResult<>(200, data);
    tResponseResult.setMsg(msg);
    return tResponseResult;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public ResponseResult(Integer code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }
}
