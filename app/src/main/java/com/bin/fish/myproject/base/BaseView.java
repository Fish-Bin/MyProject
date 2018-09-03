package com.bin.fish.myproject.base;

import com.bin.fish.myproject.util.L;

/**
 * function : 基础View，MVP专用
 *
 * @author ACChain
 * @date 2017/12/1
 */

public interface BaseView {
    default void showToast(){
        L.i("");
    }
}
