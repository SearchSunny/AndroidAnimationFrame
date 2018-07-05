package com.androidanimationframe;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 *
 */

public abstract class SimpleListAdapter<T> extends BaseAdapter {

    private Context context;
    private List<T> datas;

    public SimpleListAdapter(Context context, List<T> datas) {
        this.context = context;
        this.datas = datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Context getContext() {
        return context;
    }

}
