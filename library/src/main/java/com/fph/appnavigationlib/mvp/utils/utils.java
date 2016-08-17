package com.fph.appnavigationlib.mvp.utils;

/**
 * 项 目 名 Appnavigationlib.
 * 描    述 utils
 * 创 建 人 WoKee.
 * 创建时间 2016 08 16.
 */
public class utils extends  com.fph.appnavigationlib.utils.Utils {

    /**
     *  link{com.google.common.base}
     * @param reference
     * @param <T>
     * @return
     */
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

}
