package com.virgo.myrxjava.test;

import android.widget.TextView;

import com.virgo.myrxjava.AppContents;
import com.virgo.myrxjava.LogUtils;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/16.
 */
public class FromDemo {
    private TextView mTextDes,mTextNext, mTextError, mTextCompleted;

    public FromDemo(TextView textDes,TextView textOnNext, TextView textOnError, TextView textOnCompleted) {
        this.mTextDes = textDes;
        this.mTextNext = textOnNext;
        this.mTextError = textOnError;
        this.mTextCompleted = textOnCompleted;
    }

    public void onFrom(Integer nums[]){
        final String TAG = AppContents.TAG;
        mTextDes.setText("2.from():可以转换Future、Itetable和数组，对于Iterable和数组，产生的Observable会发射Iterable或者数组的每一项数据；对于Future，Observable会发射Future.get()方法返回的单个数据，并且支持设置超时时间、时间单位、运行所在线程，如果超过设置的超时时间，就发射错误通知。");
        //2.from():可以转换Future、Itetable和数组，对于Iterable和数组，产生的Observable会发射Iterable或者数组的每一项数据；
        // 对于Future，Observable会发射Future.get()方法返回的单个数据，并且支持设置超时时间、时间单位、运行所在线程，如果超过设置的超时时间，就发射错误通知。
        Observable.from(nums).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                LogUtils.e(TAG,"onCompleted:完成");
                mTextCompleted.setText("完成");
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(TAG,"onError:失败"+e.getMessage());
                mTextError.setText("失败");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.e(TAG,"onNext:"+integer  +"——所在线程："+Thread.currentThread().getName());
                mTextNext.setText("执行："+integer);
            }
        });
    }
}
