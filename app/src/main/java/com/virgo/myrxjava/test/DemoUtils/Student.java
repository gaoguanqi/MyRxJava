package com.virgo.myrxjava.test.DemoUtils;

import rx.Observable;
import rx.functions.Func0;

/**
 * Created by Administrator on 2017/3/16.
 */
public class Student {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    //just方式，此时age已经进行了初始化，默认是0，create()为每一个订阅者都使用同一个Observable对象，
    // 所以将要发射的age的值已经确定了，就是int的默认值0，再设置新值是没有效果的
    public Observable <Integer> justShowAge () {
        return Observable.just(getAge());
    }

    //defger方式，只有在订阅后才会创建Observable对象，也就是代码中age值被设置成28时，才创建的Observable对象
    public Observable <Integer> deferShowAge () {
        return Observable.defer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                return Observable.just(getAge());
            }
        });
    }

}
