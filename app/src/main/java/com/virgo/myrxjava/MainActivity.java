package com.virgo.myrxjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.virgo.myrxjava.rxbus.RxBusActivity;
import com.virgo.myrxjava.rxjava.MainAdapter;
import com.virgo.myrxjava.test.CreateDemo;
import com.virgo.myrxjava.test.DeferDemo;
import com.virgo.myrxjava.test.Empty_Never_ErrorDemo;
import com.virgo.myrxjava.test.FromDemo;
import com.virgo.myrxjava.test.IntervalDemo;
import com.virgo.myrxjava.test.JustDemo;
import com.virgo.myrxjava.test.RangeDemo;
import com.virgo.myrxjava.test.RepeatDemo;
import com.virgo.myrxjava.test.TimerDemo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.textDes)
    TextView textDes;
    @BindView(R.id.textOnNext)
    TextView textOnNext;
    @BindView(R.id.textOnError)
    TextView textOnError;
    @BindView(R.id.textOnCompleted)
    TextView textOnCompleted;


    private MainAdapter mAdapter;
    private List<String> mBtns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mBtns = new ArrayList<String>() {
        };
        mBtns.add("1");
        mBtns.add("2");
        mBtns.add("3");
        mBtns.add("4");
        mBtns.add("5");
        mBtns.add("6");
        mBtns.add("7");
        mBtns.add("8");
        mBtns.add("9");
        mBtns.add("10");
        mBtns.add("11");
        mBtns.add("12");
        mBtns.add("13");
        mBtns.add("14");
        mBtns.add("15");
        mBtns.add("16");

        //设置布局管理器
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        //设置adapter
        mAdapter = new MainAdapter(this, mBtns);
        mRecyclerView.setAdapter(mAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setOnItemClickLitener(new MainAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        new CreateDemo(textDes, textOnNext, textOnError, textOnCompleted).onCreate();
                        break;
                    case 1:
                        int nums[] = {1, 2, 3};
                        new JustDemo(textDes, textOnNext, textOnError, textOnCompleted).onJust(nums);
                        break;
                    case 2:
                        Integer ints[] = {11, 22, 33};
                        new FromDemo(textDes, textOnNext, textOnError, textOnCompleted).onFrom(ints);
                        break;
                    case 3:
                        new DeferDemo(textDes, textOnNext, textOnError, textOnCompleted).onDefer1();
                        break;
                    case 4:
                        new DeferDemo(textDes, textOnNext, textOnError, textOnCompleted).onDefer2();
                        break;
                    case 5:
                        new IntervalDemo(textDes, textOnNext, textOnError, textOnCompleted).onInterval(3, 1);
                        break;
                    case 6:
                        new TimerDemo(textDes, textOnNext, textOnError, textOnCompleted).onTimer(3);
                        break;
                    case 7:
                        new RangeDemo(textDes, textOnNext, textOnError, textOnCompleted).onRange(3, 10);
                        break;
                    case 8:
                        new RepeatDemo(textDes, textOnNext, textOnError, textOnCompleted).onRepeat(3, 10);
                        break;
                    case 9:
                        new Empty_Never_ErrorDemo(textDes, textOnNext, textOnError, textOnCompleted).onEmpty();
                        break;
                    case 10:
                        new Empty_Never_ErrorDemo(textDes, textOnNext, textOnError, textOnCompleted).onNever();
                        break;
                    case 11:
                        new Empty_Never_ErrorDemo(textDes, textOnNext, textOnError, textOnCompleted).onError();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @OnClick(R.id.btnRxBus)
    public void onClick() {
        startActivity(new Intent(this, RxBusActivity.class));
    }
}
