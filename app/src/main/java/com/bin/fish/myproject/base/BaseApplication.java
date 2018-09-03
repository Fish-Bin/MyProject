package com.bin.fish.myproject.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bin.fish.myproject.dagger.ApplicationComponent;
import com.bin.fish.myproject.dagger.ApplicationModule;
import com.bin.fish.myproject.dagger.DaggerApplicationComponent;

public class BaseApplication extends Application {

    public static BaseApplication app;
    public static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule()).build();
        applicationComponent.inject(this);
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }
}
