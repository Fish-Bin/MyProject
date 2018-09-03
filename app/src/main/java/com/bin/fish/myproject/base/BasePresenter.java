package com.bin.fish.myproject.base;

import android.widget.Toast;

import com.bin.fish.myproject.bean.Banner;
import com.bin.fish.myproject.net.HttpCallback;
import com.bin.fish.myproject.net.HttpInterface;
import com.bin.fish.myproject.util.L;
import com.bin.fish.myproject.window.BigToast;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.zip.DataFormatException;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class BasePresenter {

    @Inject
    public BigToast bigToast;

    @Inject
    public Toast toast;

    @Inject
    HttpInterface httpInterface;
    private CompositeDisposable group;

    private int taskCount = 0;

    @Inject
    public BasePresenter() {

    }

    /**
     * 获取banner
     */
    public void getBanner(HttpCallback<List<Banner>> callback) {
        requestHttp(httpInterface.getHomeBanner(), new Consumer<BaseBean<List<Banner>>>() {
            @Override
            public void accept(BaseBean<List<Banner>> baseBean) {
                callback.run(baseBean.getData());
            }
        });
    }

    /**
     * 获取首页新闻资讯
     */
    public void getArticleList(HttpCallback<BaseBean> callback) {
        requestHttp(httpInterface.getArticleList(), new Consumer<BaseBean>() {
            @Override
            public void accept(BaseBean baseBean) {
                callback.run(baseBean);
            }
        });
    }

    /**
     * 获取所有交易区
     */
    public void getAllTradeArea(HttpCallback<BaseBean> callback) {
        requestHttp(httpInterface.getAllTradeArea(), new Consumer<BaseBean>() {
            @Override
            public void accept(BaseBean baseBean) {
                callback.run(baseBean);
            }
        });
    }


    public <BEAN extends BaseBean> void requestHttp(Observable<BEAN> observable, Consumer<BEAN> onSuccessListener) {
        requestHttp(observable, onSuccessListener, null);
    }

    public <BEAN extends BaseBean> void requestHttp(Observable<BEAN> observable, Consumer<BEAN> onSuccessListener, Consumer<BEAN> onFailListener, Consumer<Throwable>... onErrorListener) {
        Consumer<BEAN> onNext = new Consumer<BEAN>() {
            @Override
            public void accept(BEAN bean) throws Exception {
                if (bean != null) {
                    if (bean.getError_code() == 0 || bean.getStatus() == 1100) {//请求成功
                        onSuccessListener.accept(bean);
                    } else {
                        L.i("taskCount=" + taskCount);
                        toast(bean.getMsg() + taskCount);
                        if (onFailListener != null)
                            onFailListener.accept(bean);
                    }
                }
                taskCount--;
            }
        };

        Consumer<Throwable> onError = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                try {
                    if (throwable instanceof DataFormatException || throwable instanceof JsonSyntaxException) {
                        toast("数据格式异常");
                    } else if (throwable instanceof IOException || throwable instanceof TimeoutException || throwable instanceof HttpException) {
                        toast("网络异常");
                    } else {
                        toast(throwable.getMessage());
                    }
                    if (onErrorListener.length != 0) {
                        onErrorListener[0].accept(throwable);
                    } else {
                        throwable.printStackTrace();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    toast("未知异常");
                } finally {
                    taskCount--;
                }
            }
        };

        Disposable subscribe = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError);
        addDisposable(subscribe);
    }


    public void toast(String msg) {
        toast.setText(msg);
        toast.show();
    }

    public void addDisposable(Disposable disposable) {
        taskCount++;
        if (group == null) {
            group = new CompositeDisposable();
        }
        group.add(disposable);
    }

    public void removeDisposable() {
        if (group != null) {
            group.dispose();
        }
    }

}
