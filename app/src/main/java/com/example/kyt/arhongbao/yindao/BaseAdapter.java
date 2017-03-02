package com.example.kyt.arhongbao.yindao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ZYF on 2016/11/17.
 */
public class BaseAdapter extends RecyclerView.Adapter {

    public Context context;//上下文
    public List<Object> listDatas;//数据源
    public List<Object> listDatas1, listDatas2, listDatas3, listDatas4;//多数据源
    public LayoutInflater mInflater;
    public OnViewClickListener onViewClickListener;//item子view点击事件
    public OnItemClickListener onItemClickListener;//item点击事件
    public OnItemLongClickListener onItemLongClickListener;//item长按事件


    /**
     * 单数据源
     *
     * @param context
     * @param listDatas
     */
    public BaseAdapter(Context context, List<Object> listDatas) {
        init(context, listDatas);
    }

    /**
     * 如果item的子View有点击事件，可使用该构造方法
     * 单数据源
     *
     * @param context
     * @param listDatas
     * @param onViewClickListener
     */
    public BaseAdapter(Context context, List<Object> listDatas, OnViewClickListener onViewClickListener) {
        init(context, listDatas);
        this.onViewClickListener = onViewClickListener;
    }


    /**
     * 多数据源
     *
     * @param context
     * @param listDatas1
     * @param listDatas2
     */
    public BaseAdapter(Context context, List<Object> listDatas1, List<Object> listDatas2, OnViewClickListener onViewClickListener) {
        init(context, listDatas1, listDatas2);
        this.onViewClickListener = onViewClickListener;
    }
    /**
     * 多数据源
     *
     * @param context
     * @param listDatas1
     * @param listDatas2
     * @param listDatas3
     */
    public BaseAdapter(Context context, List<Object> listDatas1, List<Object> listDatas2, List<Object> listDatas3, OnViewClickListener onViewClickListener) {
        init(context, listDatas1, listDatas2,listDatas3);
        this.onViewClickListener = onViewClickListener;
    }

/**
     * 如果item的子View有点击事件，可使用该构造方法
     * 多数据源
     *
     * @param context
     * @param listDatas1 banner图列表
     * @param listDatas2 区域列表
     * @param listDatas3 推荐列表
     * @param listDatas4 商家列表
     * @param onViewClickListener
     */
    public BaseAdapter(Context context, List<Object> listDatas1, List<Object> listDatas2, List<Object> listDatas3, List<Object> listDatas4, OnViewClickListener onViewClickListener) {
        init(context, listDatas1, listDatas2, listDatas3, listDatas4);
        this.onViewClickListener = onViewClickListener;
    }
    /**
     * 初始化
     * 单数据源
     *
     * @param context
     * @param listDatas
     */
    void init(Context context, List<Object> listDatas) {
        this.context = context;
        this.listDatas = listDatas;
        this.mInflater = LayoutInflater.from(context);
    }

    /**
     * 初始化
     * 单数据源
     *
     * @param context
     * @param listDatas1
     * @param listDatas2
     */
    void init(Context context, List<Object> listDatas1, List<Object> listDatas2) {
        this.context = context;
        this.listDatas1 = listDatas1;
        this.listDatas2 = listDatas2;
        this.mInflater = LayoutInflater.from(context);
    }
    /**
     * 初始化
     * 单数据源
     *
     * @param context
     * @param listDatas1
     * @param listDatas2
     * @param  listDatas3
     */
    void init(Context context, List<Object> listDatas1, List<Object> listDatas2, List<Object> listDatas3) {
        this.context = context;
        this.listDatas1 = listDatas1;
        this.listDatas2 = listDatas2;
        this.listDatas3 = listDatas3;
        this.mInflater = LayoutInflater.from(context);
    }

    /**
     * 初始化
     * 多数据源
     * 4块区域
     * @param context
     * @param listDatas1 banner图列表
     * @param listDatas2 区域列表
     * @param listDatas3 推荐列表
     * @param listDatas4 商家列表
     */
    void init(Context context, List<Object> listDatas1, List<Object> listDatas2, List<Object> listDatas3, List<Object> listDatas4){
        this.context = context;
        this.listDatas1 = listDatas1;
        this.listDatas2 = listDatas2;
        this.listDatas3 = listDatas3;
        this.listDatas4 = listDatas4;
        this.mInflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {//item点击事件
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {//item长按事件
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.onItemLongClick(position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDatas.size();
    }

    public interface OnViewClickListener {
        /**
         * @param position item position
         * @param viewtype 点击的view的类型，调用时根据不同的view传入不同的值加以区分
         */
        void onItemViewClick(int position, int viewtype);
    }

    /**
     * item点击事件
     * 根据position区分
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    /**
     * item长按事件
     */
    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    /**
     * 设置item点击事件
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 设置item长按事件
     *
     * @param onItemLongClickListener
     */
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    /**
     * view的点击事件
     */
    class ViewClickListener implements View.OnClickListener{
        OnViewClickListener onViewClickListener;
        int position;
        int viewtype;

        public ViewClickListener(OnViewClickListener onViewClickListener, int position, int viewtype) {
            this.onViewClickListener = onViewClickListener;
            this.position = position;
            this.viewtype = viewtype;
        }

        @Override
        public void onClick(View v) {
            onViewClickListener.onItemViewClick(position,viewtype);
        }
    }
}
