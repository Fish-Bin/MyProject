package com.bin.fish.myproject.dagger;

import com.bin.fish.myproject.base.BaseActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class)
public interface ActivityComponent {
    void inject(BaseActivity activity);
}
