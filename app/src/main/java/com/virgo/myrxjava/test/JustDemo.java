package com.virgo.myrxjava.test;

import android.widget.TextView;

import com.virgo.myrxjava.AppContents;
import com.virgo.myrxjava.LogUtils;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/16.
 */
public class JustDemo {

    private TextView mTextDes,mTextNext, mTextError, mTextCompleted;

    public JustDemo(TextView textDes,TextView textOnNext, TextView textOnError, TextView textOnCompleted) {
        this.mTextDes = textDes;
        this.mTextNext = textOnNext;
        this.mTextError = textOnError;
        this.mTextCompleted = textOnCompleted;
    }

    public void onJust(int nums[]){
        final String TAG = AppContents.TAG;

        //1.just():创建一个发射指定值得Observable对象，只是简单的将数据原样发射，原来是什么类型就发射什么类
        mTextDes.setText("1.just():创建一个发射指定值得Observable对象，只是简单的将数据原样发射，原来是什么类型就发射什么类");

        Observable.just(nums)
                .subscribe(new Subscriber<int[]>() {
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
                    public void onNext(int[] ints) {
                        for (int i = 0; i < ints.length; i++) {
                            LogUtils.e(TAG,"onNext:"+ints[i]  +"——所在线程："+Thread.currentThread().getName());
                            mTextNext.setText("执行："+ints[i]);
                        }
                    }
                });
    }
}
