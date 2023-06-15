package edu.czjt.reggie.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jianing.an
 */
@Data
//统一的返回结果类，用于封装请求的响应结果。
public class R<T> {

    private Integer code; //请求响应码，1 表示成功，0 或其它数字表示失败

    private String msg; //错误信息，请求响应的消息文本

    private T data; //请求响应的数据

    private Map map = new HashMap(); //动态数据，在某些场景下，我们需要在接口返回的结果中加入一些动态数据，比如记录操作结果的日志等。

    //静态方法：success：请求成功时使用，它接收一个泛型类型的 object 参数，表示请求成功时返回的数据
    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    //error：请求失败时使用，它接收一个字符串类型的 msg 参数，表示请求失败时的错误信息。
    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    //add：用于向 map 中添加动态数据，它接受一个字符串类型的 key 和一个 value 参数，并返回当前 R 对象本身，这样就可以进行链式调用。
    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
