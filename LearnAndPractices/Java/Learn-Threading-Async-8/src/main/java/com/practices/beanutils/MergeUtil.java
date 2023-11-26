package com.practices.beanutils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

public class MergeUtil {
    private static final CopyOptions OPTIONS = CopyOptions.create().setIgnoreNullValue(true).setOverride(false);

    private static final CopyOptions OVERRIDE_OPTIONS = CopyOptions.create().setIgnoreNullValue(true).setOverride(true);

    public static Object merge(Object sourceBean, Object targetBean) {
        BeanUtil.copyProperties(sourceBean, targetBean, OPTIONS);
        return targetBean;
    }

    public static Object mergeOverride(Object sourceBean, Object targetBean) {
        BeanUtil.copyProperties(sourceBean, targetBean, OVERRIDE_OPTIONS);
        return targetBean;
    }

}
