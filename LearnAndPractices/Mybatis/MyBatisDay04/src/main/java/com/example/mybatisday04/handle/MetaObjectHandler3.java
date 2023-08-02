package com.example.mybatisday04.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MetaObjectHandler3 implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("insert start attribute filling");
        LocalDateTime currentTime = LocalDateTime.now();
        setValueIfNull(metaObject, "gmtCreate", currentTime);
        setValueIfNull(metaObject, "gmtModified", currentTime);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("update start attribute filling");
        LocalDateTime currentTime = LocalDateTime.now();
        setValueIfNull(metaObject, "gmtModified", currentTime);
    }

    private void setValueIfNull(MetaObject metaObject, String fieldName, Object fieldValue) {
        if (metaObject.hasSetter(fieldName)) {
            Object currentVal = getFieldValByName(fieldName, metaObject);
            if (currentVal == null) {
                setFieldValByName(fieldName, fieldValue, metaObject);
            }
        }
    }
}
