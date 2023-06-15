package edu.czjt.reggie.common;

//create by jianing.an
public class BaseContext {
//包含一个静态成员变量threadLocal,维护了一个与当前线程相关联的 Long 类型的值。
    
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
  
    //setCurrentID:用于将当前线程的 threadLocal 对象的值设置为传入的 id 参数。
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    /**
     * 获取值
     * @return
     */
    //getCurrentId:用于获取当前线程的 threadLocal 对象的值。
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
