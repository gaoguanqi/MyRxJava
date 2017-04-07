package com.virgo.myrxjava.test;

import android.widget.TextView;

import com.virgo.myrxjava.AppContents;
import com.virgo.myrxjava.LogUtils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/3/16.
 */
public class TimerDemo {


    private TextView mTextDes,mTextNext, mTextError, mTextCompleted;

    public TimerDemo(android.widget.TextView textDes, TextView textOnNext, TextView textOnError, TextView textOnCompleted) {
        this.mTextDes = textDes;
        this.mTextNext = textOnNext;
        this.mTextError = textOnError;
        this.mTextCompleted = textOnCompleted;
    }


    public  void onTimer(long time) {
        final String TAG = AppContents.TAG;

        //6.timer():创建一个Observable对象，它在一个给定的延迟后发射一个特殊的值0，默认在computation调度器上运行，可以通过参数指定运行的线程
        mTextDes.setText("6.timer():创建一个Observable对象，它在一个给定"+"("+time+"秒)"+"的延迟后发射一个特殊的值0，默认在computation调度器上运行，可以通过参数指定运行的线程");
        //5秒后打印一个 0
        Observable.timer(time,TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(new Subscriber<Long>() {
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
            public void onNext(Long aLong) {
                LogUtils.e(TAG,"onNext:Long"+aLong  +"——所在线程："+Thread.currentThread().getName());
                mTextNext.setText("执行："+aLong);
            }
        });
    }
}
