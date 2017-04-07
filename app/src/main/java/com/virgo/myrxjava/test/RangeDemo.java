package com.virgo.myrxjava.test;

import android.widget.TextView;

import com.virgo.myrxjava.AppContents;
import com.virgo.myrxjava.LogUtils;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/3/16.
 */
public class RangeDemo {

    private TextView mTextDes,mTextNext, mTextError, mTextCompleted;

    public RangeDemo(TextView textDes,TextView textOnNext, TextView textOnError, TextView textOnCompleted) {
        this.mTextDes = textDes;
        this.mTextNext = textOnNext;
        this.mTextError = textOnError;
        this.mTextCompleted = textOnCompleted;
    }


    public  void onRange(int startTime,int num) {
        final String TAG = AppContents.TAG;



        //7.range():创建一个发射特定整数序列的Observable对象，
        // 可以通过参数指定运行线程，
        // 以下构造函数的参数说明：
        // 第一个参数：起始值，
        // 第二个参数：执行次数，
        // 第三个参数：运行线程。

        mTextDes.setText("7.range():创建一个发射特定整数序列的Observable对象，\n" +
                "         可以通过参数指定运行线程，\n" +
                "         以下构造函数的参数说明：\n" +
                "        第一个参数：起始值，\n" +
                "         第二个参数：执行次数，\n" +
                "         第三个参数：运行线程。\n 从 "+startTime+"开始一次打印 数字到 "+num+" 次");

        //从 3开始一次打印 数字到 10 次
        Observable.range(startTime,num, AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
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
               // integer = integer -  5;
                LogUtils.e(TAG,"onNext:Integer"+integer  +"——所在线程："+Thread.currentThread().getName());
                mTextNext.setText("执行："+integer);
            }
        });


    }
}
