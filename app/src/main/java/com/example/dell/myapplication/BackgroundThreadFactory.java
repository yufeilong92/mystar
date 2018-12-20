package com.example.dell.myapplication;

import android.support.annotation.NonNull;

import java.util.concurrent.ThreadFactory;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: BackgroundThreadFactory.java
 * @Package com.example.dell.myapplication
 * @Description: todo
 * @author: YFL
 * @date: 2018/12/19 23:06
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018/12/19 星期三
 * 注意：本内容仅限于学川教育有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
class BackgroundThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(@NonNull Runnable r) {
        return null;
    }
}
