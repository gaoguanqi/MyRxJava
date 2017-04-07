package com.virgo.myrxjava.test;

import android.widget.TextView;

import com.virgo.myrxjava.AppContents;
import com.virgo.myrxjava.LogUtils;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/16.
 */
public class Empty_Never_ErrorDemo {

    private TextView mTextDes,mTextNext, mTextError, mTextCompleted;

    public Empty_Never_ErrorDemo(TextView textDes,TextView textOnNext, TextView textOnError, TextView textOnCompleted) {
        this.mTextDes = textDes;
        this.mTextNext = textOnNext;
        this.mTextError = textOnError;
        this.mTextCompleted = textOnCompleted;
    }

    //9.empty()、never()、error()：一般用于测试，有时候也可以结合其他的Observables，或者作为其他需要Observable的操作符的参数


    //创建一个不发射任何数据但是正常终止的 Observable
    public  void onEmpty() {
        final String TAG = AppContents.TAG;

        mTextDes.setText("9.empty() 创建一个不发射任何数据但是正常终止的 Observable");

        Observable.empty().subscribe(new Subscriber<Object>() {
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
            public void onNext(Object o) {
                LogUtils.e(TAG,"onNext:Object"+o.toString()  +"——所在线程："+Thread.currentThread().getName());
                mTextNext.setText("执行："+o.toString());
            }
        });




    }

    //10 创建一个不发射任何数据也不终止的 Observable
    public  void onNever() {
        final String TAG = AppContents.TAG;

        mTextDes.setText("10.onNever() 创建一个不发射任何数据也不终止的 Observable");

        Observable.never().subscribe(new Subscriber<Object>() {
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
            public void onNext(Object o) {
                LogUtils.e(TAG,"onNext:Object"+o.toString()  +"——所在线程："+Thread.currentThread().getName());
                mTextNext.setText("执行："+o.toString());
            }
        });


    }



    //11.onError() 创建一个不发射数据 以一个错误终止的 Observable
    public  void onError() {
        final String TAG = AppContents.TAG;

        mTextDes.setText("11.onError() 创建一个不发射数据 以一个错误终止的 Observable");

        Observable.error(new Throwable()).subscribe(new Observer<Object>() {
            @Override
            public void onCompleted() {
                LogUtils.e(TAG,"onCompleted:完成");
                mTextCompleted.setText("完成");
            }

            @Override
            public void onError(Throwable e) {
                // 必走 onError
                LogUtils.e(TAG,"onError:失败 必须的必"+e.getMessage());
                mTextError.setText("失败 必调此方法："+e.getMessage());
            }

            @Override
            public void onNext(Object o) {
                LogUtils.e(TAG,"onNext:Object"+o.toString()  +"——所在线程："+Thread.currentThread().getName());
                mTextNext.setText("执行："+o.toString());
            }
        });


    }
}
