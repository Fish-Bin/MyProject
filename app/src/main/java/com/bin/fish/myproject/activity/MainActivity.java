package com.bin.fish.myproject.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bin.fish.myproject.R;
import com.bin.fish.myproject.annotation.InjectRes;
import com.bin.fish.myproject.arouter.ArouterConst;
import com.bin.fish.myproject.base.BaseActivity;
import com.bin.fish.myproject.util.L;

@InjectRes(value = R.layout.activity_main)
@Route(path = ArouterConst.Activity_MainActivity)
public class MainActivity extends BaseActivity {
    @Override
    protected void init() {
        mPresenter.getBanner(bean -> L.i("url=" + bean.get(0).getPic_url() + ""));
        mPresenter.getArticleList(baseBean -> {
        });
        mPresenter.getAllTradeArea(baseBean -> {
        });
        findViewById(R.id.tv_login).setOnClickListener(view -> ARouter.getInstance().build(ArouterConst.Activity_SkipActivity).navigation());
    }
}
