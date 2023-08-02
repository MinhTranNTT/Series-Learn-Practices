//package com.example.mybatisday04.handle;
//
//import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.reflection.MetaObject;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
//@Component
//@Slf4j
//public class MyMetaObjectHandler implements MetaObjectHandler {
//    @Override
//    public void insertFill(MetaObject metaObject) {
//        log.info("insert start attribute filling");
//        this.strictInsertFill(metaObject,"gmtCreate", LocalDateTime.class, LocalDateTime.now());
//        this.strictInsertFill(metaObject,"gmtModified", LocalDateTime.class, LocalDateTime.now());
//    }
//
//    @Override
//    public void updateFill(MetaObject metaObject) {
//        log.info("update start attribute filling");
//        this.strictUpdateFill(metaObject,"gmtModified", LocalDateTime.class, LocalDateTime.now());
//    }
//}
