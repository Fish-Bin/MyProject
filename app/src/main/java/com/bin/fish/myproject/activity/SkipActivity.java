package com.bin.fish.myproject.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bin.fish.myproject.R;
import com.bin.fish.myproject.annotation.InjectRes;
import com.bin.fish.myproject.arouter.ArouterConst;
import com.bin.fish.myproject.base.BaseActivity;

@InjectRes(value = R.layout.activity_skip)
@Route(path = ArouterConst.Activity_SkipActivity, extras = ArouterConst.FLAG_LOGIN | ArouterConst.FLAG_VERIFY)
public class SkipActivity extends BaseActivity {
    @Override
    protected void init() {
    }
}
