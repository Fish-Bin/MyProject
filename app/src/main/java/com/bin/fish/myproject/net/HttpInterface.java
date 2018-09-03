package com.bin.fish.myproject.net;

import com.bin.fish.myproject.base.BaseBean;
import com.bin.fish.myproject.bean.Banner;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;

public interface HttpInterface {

    /**
     * 1.1.1  首页: bananer图
     *
     * @return banner图
     */
    @POST("Banner/bannerList")
    Observable<BaseBean<List<Banner>>> getHomeBanner();

    /**
     * 1.1.2  首页：获取首页新闻资讯
     */
    @POST("Article/getArticleList")
    Observable<BaseBean> getArticleList();


    /**
     * 首页：获取所有交易区
     */
    @POST("TradeCenter/getAllTradeArea")
    Observable<BaseBean> getAllTradeArea();
}
