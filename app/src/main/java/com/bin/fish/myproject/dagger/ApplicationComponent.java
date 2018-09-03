package com.bin.fish.myproject.dagger;

import android.widget.Toast;

import com.bin.fish.myproject.base.BaseApplication;
import com.bin.fish.myproject.net.HttpInterface;
import com.bin.fish.myproject.window.BigToast;

import javax.inject.Singleton;

import dagger.Component;
@PerApplication
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseApplication application);

    HttpInterface provideHttpInterface();

    BigToast provideBigToast();

    Toast provideToast();

}
