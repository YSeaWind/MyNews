package com.lenovo.mynews.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lenovo.mynews.R;
import com.lenovo.mynews.bean.Data;

import java.util.List;

public class CollectRecycleAdapter extends RecyclerView.Adapter<CollectRecycleAdapter.ViewHolder> {
    private List<Data> newsList;
    private int layoutPosition; //记录当前点击位置
    private Context context;

    //点击事件接口私有属性
    private OnItemClickListener onItemClickListener = null;

    //setter方法
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    //点击事件回调接口
    public interface OnItemClickListener {
        void onItemClick( View view,int position);
    }

    /**
     * 构造函数,用于把要展示的数据源传入,并赋予值给全局变量
     * @param newsList
     */
    public CollectRecycleAdapter(List<Data> newsList,Context context) {
        this.newsList = newsList;
        this.context=context;
    }
    /**
     * 定义内部类ViewHolder,并继承RecyclerView.ViewHolder。
     * 传入的View参数通常是RecyclerView子项的最外层布局。
     */
     class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitelMenu;

        public ViewHolder(View view) {
            super(view);
            tvTitelMenu=view.findViewById(R.id.titleButton);
        }

    }

    /**
     * onCreateViewHolder()用于创建ViewHolder实例,并把加载的布局传入到构造函数去,再把ViewHolder实例返回
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_news_title_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        //实现点击效果
        //holder.itemView.setTag(viewType);

        return holder;
    }

    /**
     * onBindViewHolder()则是用于对子项的数据进行赋值,
     * 会在每个子项被滚动到屏幕内时执行。position得到当前项的User实例。
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Data data = newsList.get(position);
        holder.tvTitelMenu.setText(data.getUniquekey());
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //程序执行到此，会去执行具体实现的onItemClick()方法
                if (onItemClickListener != null) {
                    //获取当前点击的位置
                    layoutPosition = holder.getLayoutPosition();
                    notifyDataSetChanged();
                    onItemClickListener.onItemClick(holder.itemView, (int) holder.itemView.getTag());
                    //onItemClickListener.onItemClick(v);
                }
            }
        });
        //更改状态
        if (position == layoutPosition) {
                holder.tvTitelMenu.setTextColor(Color.parseColor("#ff0000"));
        } else {
            holder.tvTitelMenu.setTextColor(Color.parseColor("#000000"));
        }
    }
    /**
     * getItemCount()返回RecyclerView的子项数目
     * @return
     */
    @Override
    public int getItemCount() {
        return newsList.size();
    }
    public void setBg(int pos){
        this.layoutPosition = pos;
        notifyDataSetChanged();
    }
}

