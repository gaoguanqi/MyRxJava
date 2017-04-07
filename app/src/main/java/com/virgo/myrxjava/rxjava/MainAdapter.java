package com.virgo.myrxjava.rxjava;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.virgo.myrxjava.R;

import java.util.List;

/**
 * Created by Administrator on 2017/3/2.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    public List<String> mData;
    private Context mContext;

    public interface OnItemClickLitener {

        void onItemClick(View view, int position);
    }
    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public MainAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mData = data;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.setData(mData.get(position));
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.btnItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.btnItem, pos);
                }
            });
        }
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public class ViewHolder extends RecyclerView.ViewHolder {

        private Button btnItem;

        public ViewHolder(View itemView) {
            super(itemView);
            btnItem = (Button)itemView.findViewById(R.id.btnItem);
        }

        public void setData(String data) {
            btnItem.setText(data);
        }
    }
}
