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
//public class MyMetaObjectHandler2 implements MetaObjectHandler {
//    @Override
//    public void insertFill(MetaObject metaObject) {
//        boolean hasGmtCreate = metaObject.hasSetter("gmtCreate");
//        boolean hasGmtModify = metaObject.hasSetter("gmtModified");
//        if (hasGmtCreate) {
//            Object gmtCreate = this.getFieldValByName("gmtCreate", metaObject);
//            if (gmtCreate == null) {
//                this.strictInsertFill(metaObject,"gmtCreate", LocalDateTime.class, LocalDateTime.now());
//            }
//        }
//        if (hasGmtModify) {
//            Object gmtModified = this.getFieldValByName("gmtModified", metaObject);
//            if (gmtModified == null) {
//                this.strictInsertFill(metaObject, "gmtModified", LocalDateTime.class, LocalDateTime.now());
//            }
//        }
//    }
//
//    @Override
//    public void updateFill(MetaObject metaObject) {
//        boolean hasGmtModified = metaObject.hasSetter("gmtModified");
//        if (hasGmtModified) {
//            Object gmtModified = this.getFieldValByName("gmtModified", metaObject);
//            if (gmtModified == null) {
//                this.strictInsertFill(metaObject,"gmtModified", LocalDateTime.class, LocalDateTime.now());
//            }
//        }
//    }
//}
