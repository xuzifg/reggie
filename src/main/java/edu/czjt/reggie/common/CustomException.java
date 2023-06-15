package edu.czjt.reggie.common;

//create by jianing.an
 // 自定义业务异常类，用于封装应用程序的业务异常。
 
public class CustomException extends RuntimeException {
 //构造函数中传递一个字符串类型的 message 参数，该异常会使用此参数初始化其父类 RuntimeException 的消息文本。
    public CustomException(String message){
     //在子类的构造函数中调用父类的构造函数，并传递子类的参数。
        super(message);
    }
}
