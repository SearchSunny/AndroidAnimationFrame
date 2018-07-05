package com.androidanimationframe;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 *
 */

public class TempAdapter extends SimpleListAdapter<Integer> {

    private Context mContext;
    private Handler mHandler;
    public TempAdapter(Context context, List<Integer> datas, Handler handler) {
        super(context, datas);
        this.mContext = context;
        this.mHandler = handler;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.animate_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            mHandler.sendEmptyMessage(1000);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return holder.convertView;
    }

    private class ViewHolder{

        private View convertView;

        private ViewHolder(View convertView) {
            this.convertView = convertView;
        }
    }
}
