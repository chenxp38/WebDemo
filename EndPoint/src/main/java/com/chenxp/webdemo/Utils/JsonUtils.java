package com.chenxp.webdemo.Utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * @Description:
 * @author: chenxp
 * @date: 2020/8/17 14:29
 * json工具类
 */
public class JsonUtils {
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 响应业务状态
     *  200：表示成功
     *  400：表示错误，错误信息在msg字段中
     *  501：bean验证错误，不管多少个错误都以map形式返回
     *  502：拦截器拦截到用户token出错
     *  555：异常抛出信息
     */
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;


    public static JsonUtils build(Integer status, String msg, Object data) {
        return new JsonUtils(status, msg, data);
    }

    public static JsonUtils ok(Object data) {
        return new JsonUtils(data);
    }
    public static JsonUtils ok() {
        return new JsonUtils(null);
    }

    public static JsonUtils errorMsg(String msg) {
        return new JsonUtils(400, msg, null);
    }

    public static JsonUtils errorMap(Object data) {
        return new JsonUtils(501, "error", data);
    }

    public static JsonUtils errorTokenMsg(String msg) {
        return new JsonUtils(502, msg, null);
    }

    public static JsonUtils errorException(String msg) {
        return new JsonUtils(555, msg, null);
    }

    public JsonUtils() {

    }


    public JsonUtils(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    public JsonUtils(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }


    /**
     * @Author chenxp
     * @Description 将json结果集转化为JsonUtils对象
     *     		    需要转换的对象是一个类
     * @Date 14:51 2020/8/17
     * @Param
     * @return
     */
    public static JsonUtils formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, JsonUtils.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @Author chenxp
     * @Description 没有object对象的转化
     * @Date 14:52 2020/8/17
     * @Param
     * @return
     */
    public static JsonUtils format(String json) {
        try {
            return MAPPER.readValue(json, JsonUtils.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author chenxp
     * @Description //Object是集合转化
     *          	   需要转换的对象是一个list
     * @Date 14:53 2020/8/17
     * @Param
     * @return
     *
     */
    public static JsonUtils formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
