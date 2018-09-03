package com.bin.fish.myproject.arouter;

import android.content.Context;

import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bin.fish.myproject.util.L;

@Interceptor(priority = 1, name = "interceptor")
public class ArouterInterceptor implements IInterceptor {

    public final int[] allFlag = new int[]{
            ArouterConst.FLAG_LOGIN,
            ArouterConst.FLAG_VERIFY,
            ArouterConst.FLAG_PHONE,
            ArouterConst.FLAG_EMAIL
    };


    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        int requireFlag = postcard.getExtra() | Integer.MIN_VALUE;

        L.i("需要的=" + Integer.toBinaryString(requireFlag));
        if (requireFlag == Integer.MIN_VALUE) {
            //目标界面没有权限，直接跳过去
            callback.onContinue(postcard);
            return;
        }
        boolean[] currentBoolean = new boolean[]{false, false, false, false};

        int currentFlage = Integer.MIN_VALUE;
        for (int i = 0; i < allFlag.length; i++) {
            if (currentBoolean[i]) {
                currentFlage = currentFlage | allFlag[i];
            }
            L.i("当前的=" + Integer.toBinaryString(currentFlage));
        }
        for (int i = 0; i < allFlag.length; i++) {
            //1.需要这个权限，2.当前没有这个权限
            if ((requireFlag & allFlag[i]) != 0 && (currentFlage & allFlag[i]) == 0) {
                switch (i) {
                    case 0:
                        L.i("需要登录权限");
                        dispatchLogin(postcard, callback);
                        break;
                    case 1:
                        L.i("需要认证权限");
                        break;
                    case 2:
                        L.i("需要手机权限");
                        break;
                    case 3:
                        L.i("需要邮箱权限");
                        break;
                }
            }
        }
    }

    private void dispatchLogin(Postcard postcard, InterceptorCallback callback) {
        Postcard newPostcard = ARouter.getInstance().build(ArouterConst.Activity_LoginActivity);
        LogisticsCenter.completion(newPostcard);
        postcard.setExtra(newPostcard.getExtra()).setPath(newPostcard.getPath()).setGroup(newPostcard.getGroup()).setDestination(newPostcard.getDestination());
        process(postcard, callback);
    }


    @Override
    public void init(Context context) {
        // 拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次
    }
}
