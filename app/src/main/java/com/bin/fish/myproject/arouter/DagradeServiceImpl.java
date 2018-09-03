package com.bin.fish.myproject.arouter;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.bin.fish.myproject.util.L;

/**
 * 自定义ARouter跳转全局降级，当跳转失败的话，可以进行处理，保证程序健壮性
 * 必须实现DegradeService接口，并加上一个Path内容任意的注解
 */
@Route(path = "/degrade/DagradeServiceImpl")
public class DagradeServiceImpl implements DegradeService {
    @Override
    public void onLost(Context context, Postcard postcard) {
        L.i("界面无法跳转");
    }

    @Override
    public void init(Context context) {

    }
}
