package com.virgo.myrxjava.test;

import android.widget.TextView;

import com.virgo.myrxjava.AppContents;
import com.virgo.myrxjava.LogUtils;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/16.
 */
public class CreateDemo {

    private TextView mTextDes,mTextNext, mTextError, mTextCompleted;

    public CreateDemo(TextView textDes,TextView textOnNext, TextView textOnError, TextView textOnCompleted) {
        this.mTextDes = textDes;
        this.mTextNext = textOnNext;
        this.mTextError = textOnError;
        this.mTextCompleted = textOnCompleted;
    }

    public void onCreate() {
        final String TAG = AppContents.TAG;
        mTextDes.setText("0.create():从头开始创建一个Observable对象，默认不在任何特定的调度器上运行");

        //1.create():从头开始创建一个Observable对象，默认不在任何特定的调度器上运行，
        //Observable.create(new Observable.OnSubscribe<Integer>
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        for (int i = 0; i < 6; i++) {
                            subscriber.onNext(i);
                        }
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                LogUtils.e(TAG, "onCompleted:完成");
                mTextCompleted.setText("完成");
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(TAG, "onError:失败" + e.getMessage());
                mTextError.setText("失败");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.e(TAG, "onNext:Integer" + integer + "——所在线程：" + Thread.currentThread().getName());
                mTextNext.setText("执行："+integer);
            }
        });

    }
}
