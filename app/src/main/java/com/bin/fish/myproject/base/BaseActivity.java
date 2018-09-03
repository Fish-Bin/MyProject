package com.bin.fish.myproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bin.fish.myproject.annotation.InjectRes;
import com.bin.fish.myproject.dagger.ActivityComponent;
import com.bin.fish.myproject.dagger.DaggerActivityComponent;

import java.lang.annotation.Annotation;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity{

    @Inject
    public BasePresenter mPresenter;

    public ActivityComponent activityComponent;
    private int resId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (Annotation annotation : getClass().getAnnotations()) {
            if (annotation instanceof InjectRes) {
                resId = ((InjectRes) annotation).value();
            }
        }
        setContentView(resId);
        activityComponent = DaggerActivityComponent.builder().applicationComponent(BaseApplication.applicationComponent).build();
        activityComponent.inject(this);
        init();
    }

    protected abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.removeDisposable();
    }
}
