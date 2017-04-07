package com.virgo.myrxjava.rxbus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.virgo.myrxjava.R;
import com.virgo.myrxjava.rxbus.RxBus;
import com.virgo.myrxjava.rxbus.RxBusActivity;
import com.virgo.myrxjava.rxbus.event.ClickEvent;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dingmouren on 2016/12/26.
 */

public class FragmentLeft extends Fragment {
    private RxBus mRxBus;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_left_layout,container,false);
        ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRxBus = ((RxBusActivity)getActivity()).getRxBus();
    }

    @OnClick(R.id.btn_rxbus)
    public void onTabButtonClick(){
        if (mRxBus.hasObservers())
        mRxBus.post(new ClickEvent());
    }
}
