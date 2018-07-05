package com.androidanimationframe;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 *
 */

public class AnimateLayout extends LinearLayout {

    private Context mContext;

    public AnimateLayout(Context context) {
        super(context);
    }

    public AnimateLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public AnimateLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private ImageView imageView;

    public void initView(){
        View view = LinearLayout.inflate(mContext,R.layout.item_animate_layout,null);
        imageView = view.findViewById(R.id.imageView2);
        this.addView(view);

    }

    private Handler mHandler = new Handler();

    public void startAnimate(){
        Log.d("MV","开始执行自定义动画==================");
        //imageView.animate().setDuration(500);
        mHandler.postDelayed(runnable,300);
    }

    public void stopAnimate(){
        Log.d("MV","停止自定义动画==================");
        //imageView.animate().setDuration(500);
        mHandler.removeCallbacks(runnable);
    }

    public Runnable runnable = new  Runnable(){
        @Override
        public void run() {
            Log.d("MV","getTranslationY()="+imageView.getTranslationY());
            if (imageView.getTranslationY() == 0){
                imageView.animate().translationY(110);
                mHandler.removeCallbacks(this);
                //执行结束
                animateEndListener.animateEnd();
            }else{
                imageView.animate().translationY(0);
                mHandler.postDelayed(this, 1000);
            }
        }
    };

    private AnimateEndListener animateEndListener;

    public void setAnimateEndListener(AnimateEndListener animateEndListener){

        this.animateEndListener = animateEndListener;
    }

    public interface AnimateEndListener{

        void animateEnd();
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == GONE){
            stopAnimate();
        }
    }
}
