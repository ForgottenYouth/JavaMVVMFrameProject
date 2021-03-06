/**
 * FileName: BaseApplication
 * Author: shiwenliang
 * Date: 2021/10/22 10:22
 * Description: 自定义Applicationde 基类
 */
package com.leon.base.application;

import android.app.Application;
import android.os.Looper;
import android.os.MessageQueue;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.mmkv.MMKV;

public class BaseApplication extends Application {

    private static BaseApplication mInstance = null;

    public static BaseApplication getInstance() {
        if (mInstance == null) {
            mInstance = new BaseApplication();
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MMKV.initialize(this);
        initARoute();
    }

    private void initARoute() {
        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    private boolean isDebug() {
        return true;
    }
}
