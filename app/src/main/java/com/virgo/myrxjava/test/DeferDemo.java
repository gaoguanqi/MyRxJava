package com.virgo.myrxjava.test;

import android.widget.TextView;

import com.virgo.myrxjava.AppContents;
import com.virgo.myrxjava.LogUtils;
import com.virgo.myrxjava.test.DemoUtils.Student;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/16.
 */
public class DeferDemo {

    private TextView mTextDes,mTextNext, mTextError, mTextCompleted;

    public DeferDemo(TextView textDes,TextView textOnNext, TextView textOnError, TextView textOnCompleted) {
        this.mTextDes = textDes;
        this.mTextNext = textOnNext;
        this.mTextError = textOnError;
        this.mTextCompleted = textOnCompleted;
    }



    public void onDefer1(){
        final String TAG = AppContents.TAG;

       //3.defer():直到有观察者订阅时才会创建Observable对象，并且为每一个观察者创建一个新的Observable对象
        mTextDes.setText("3.defer():直到有观察者订阅时才会创建Observable对象，并且为每一个观察者创建一个新的Observable对象  just方式，此时age已经进行了初始化，默认是1，create()为每一个订阅者都使用同一个Observable对象,所以将要发射的age的值已经确定了，就是int的默认值1，再设置新值 mStudent.setAge(11); 是没有效果的");

        Student mStudent = new Student();
        mStudent.setAge(1);

        Observable<Integer> observable = mStudent.justShowAge();
        mStudent.setAge(11);
        // just方式，此时age已经进行了初始化，默认是1，create()为每一个订阅者都使用同一个Observable对象，
        // 所以将要发射的age的值已经确定了，就是int的默认值1，再设置新值 mStudent.setAge(11); 是没有效果的
        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                LogUtils.e(TAG,"onCompleted1:完成");
                mTextCompleted.setText("完成");
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(TAG,"onError1:失败"+e.getMessage());
                mTextError.setText("失败");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.e(TAG,"onNext1:"+integer  +"——所在线程："+Thread.currentThread().getName());
                mTextNext.setText("执行："+integer);
            }
        });
    }

    public void onDefer2(){
        final String TAG = AppContents.TAG;

        //4.defer():直到有观察者订阅时才会创建Observable对象，并且为每一个观察者创建一个新的Observable对象
        mTextDes.setText("4.defer():直到有观察者订阅时才会创建Observable对象，并且为每一个观察者创建一个新的Observable对象 defger方式，只有在订阅后才会创建Observable对象，也就是代码中age值被设置成22时，才创建的Observable对象");
        Student mStudent = new Student();
        mStudent.setAge(2);
        Observable<Integer> observable = mStudent.deferShowAge();
        mStudent.setAge(22);
        //defger方式，只有在订阅后才会创建Observable对象，也就是代码中age值被设置成22时，才创建的Observable对象
        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                LogUtils.e(TAG,"onCompleted2:完成");
                mTextCompleted.setText("完成");
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(TAG,"onError2:失败"+e.getMessage());
                mTextError.setText("失败");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.e(TAG,"onNext2:"+integer  +"——所在线程："+Thread.currentThread().getName());
                mTextNext.setText("执行："+integer);
            }
        });
    }
}
