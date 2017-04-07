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
public class IntervalDemo {



    private TextView mTextDes,mTextNext, mTextError, mTextCompleted;

    public IntervalDemo(TextView textDes,TextView textOnNext, TextView textOnError, TextView textOnCompleted) {
        this.mTextDes = textDes;
        this.mTextNext = textOnNext;
        this.mTextError = textOnError;
        this.mTextCompleted = textOnCompleted;
    }


    //创建一个按固定时间间隔发射整数数列的Observable对象，有多个重载方法，可以都看一下，
    //介绍一下三个参数的构造函数：
    // 第一个参数：第一次延时多久发射数据，
    // 第二个参数：发射数据的时间间隔（从第二次开始），
    // 第三个参数：时间单位，
    // 第四个参数：指定发射相数据所在的线程，
    //默认是在computation调度器上运行，


    public  void onInterval(long time1,long time2){
        final String TAG = AppContents.TAG;
        mTextDes.setText("// 5.Interval 创建一个按固定时间间隔发射整数数列的Observable对象，有多个重载方法，可以都看一下，\n" +
                "    介绍一下三个参数的构造函数：\n" +
                "     第一个参数：第一次延时多久发射数据，\n" +
                "     第二个参数：发射数据的时间间隔（从第二次开始），\n" +
                "     第三个参数：时间单位，\n" +
                "     第四个参数：指定发射相数据所在的线程，\n" +
                "    默认是在computation调度器上运行，\n "+time1+"秒后 每隔"+time2+"秒执行");

        //3秒后 每隔1秒执行
        Observable.interval(time1,time2, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(new Subscriber<Long>() {
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

        Object asd = Observable;

        
    }
}
