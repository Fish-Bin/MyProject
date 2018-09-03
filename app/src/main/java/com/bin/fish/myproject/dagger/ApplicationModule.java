package com.bin.fish.myproject.dagger;

import android.widget.Toast;

import com.bin.fish.myproject.base.BaseApplication;
import com.bin.fish.myproject.net.HttpInterface;
import com.bin.fish.myproject.util.L;
import com.bin.fish.myproject.window.BigToast;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@PerApplication
@Module
public class ApplicationModule {

    public ApplicationModule() {
    }

    @Provides
    @PerApplication
    BigToast provideBigToast() {
        return BigToast.getInstance();
    }

    @Provides
    @PerApplication
    HttpInterface provideHttpInterface() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor(message -> L.i("HttpLog" + message)).setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(12000, TimeUnit.MILLISECONDS)
                .writeTimeout(12000, TimeUnit.MILLISECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://192.169.1.210:8567/api/")
                .client(okHttpClient)
                .build();
        return retrofit.create(HttpInterface.class);
    }

    @Provides
    @PerApplication
    Toast provideToast() {
        Toast toast = Toast.makeText(BaseApplication.app, "", Toast.LENGTH_SHORT);
        return toast;
    }
}
