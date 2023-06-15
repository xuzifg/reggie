package edu.czjt.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by jianing.an
 */
@Component
@Slf4j
//元对象处理器，它实现了 MyBatis Plus 框架提供的 MetaObjectHandler 接口，用于将公共字段自动填充到实体类中。
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
  //包含两个实现了 MetaObjectHandler 接口的抽象方法，在这些方法中，我们会将公共字段的值自动填充到数据库中。
  //insertFill会在插入对应的实体类时被自动调用。
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充[insert]...");
        log.info(metaObject.toString());

        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", BaseContext.getCurrentId());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
    }

    @Override
  //updateFill 方法会在更新对应的实体类时被自动调用。
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充[update]...");
        log.info(metaObject.toString());

        metaObject.setValue("updateTime", LocalDateTime.now());
      //使用 BaseContext.getCurrentId() 方法来获取当前线程的 id，并将其填充到实体类中。
        metaObject.setValue("updateUser", BaseContext.getCurrentId());

    }
}

