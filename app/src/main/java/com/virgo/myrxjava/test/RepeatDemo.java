package com.virgo.myrxjava.test;

import android.widget.TextView;

import com.virgo.myrxjava.AppContents;
import com.virgo.myrxjava.LogUtils;

import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/3/16.
 */
public class RepeatDemo {

    private TextView mTextDes,mTextNext, mTextError, mTextCompleted;

    public RepeatDemo(TextView textDes,TextView textOnNext, TextView textOnError, TextView textOnCompleted) {
        this.mTextDes = textDes;
        this.mTextNext = textOnNext;
        this.mTextError = textOnError;
        this.mTextCompleted = textOnCompleted;
    }

    public  void onRepeat(int i,long nums){
        final String TAG = AppContents.TAG;

       // 8.repeat():创建一个发射特定数量重复多次的Observable对象，多个重载方法，默认在transpoline调度器上运行
        mTextDes.setText("8.repeat():创建一个发射特定数量重复多次的Observable对象，多个重载方法，默认在transpoline调度器上运行 \n 打印 "+i+" ，重复"+nums+"次");
        // 打印 1 ，重复 3次
        Observable.just(i).repeat(nums, Schedulers.trampoline()).subscribe(new Observer<Integer>() {
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
                LogUtils.e(TAG,"onNext:Integer"+integer  +"——所在线程："+Thread.currentThread().getName());
                mTextNext.setText("执行："+integer);
            }
        });

    }
}
