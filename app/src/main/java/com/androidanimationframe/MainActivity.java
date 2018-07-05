package com.androidanimationframe;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AnimateLayout.AnimateEndListener {

    private AnimationDrawable animationDrawable;
    private LayerDrawable layerDrawable;
    private TransitionDrawable transitionDrawable;
    private ScaleDrawable scaleDrawable;
    private TranslateAnimation translateAnimation = new TranslateAnimation(0f, 0f, 0f, 100f);
    ViewFlipper filpper;
    ImageView imageView;
    ImageView imageView2;
    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    AnimateLayout animate_layout;

    GridView listView;
    TempAdapter adapter;
    List<Integer> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filpper = this.findViewById(R.id.filpper);
        imageView = this.findViewById(R.id.imageView);
        imageView2 = this.findViewById(R.id.imageView2);
        /*ScaleDrawable drawable = (ScaleDrawable) imageView2.getDrawable();
        drawable.setLevel(1);*/

        textView = this.findViewById(R.id.textView);
        textView2 = this.findViewById(R.id.textView2);
        textView3 = this.findViewById(R.id.textView3);
        textView4 = this.findViewById(R.id.textView4);

        adapter = new TempAdapter(MainActivity.this,list,handler);
        listView = this.findViewById(R.id.recycler_icons);
        listView.setAdapter(adapter);

        animate_layout = this.findViewById(R.id.animate_layout);

        for (int i=0;i<=1;i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.knight_attack);
            filpper.addView(imageView);
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageView.setImageResource(R.drawable.knight_attack);
                imageView.setAnimation(translateAnimation);
                // 1. 设置动画
                animationDrawable = (AnimationDrawable) imageView.getDrawable();
                animationDrawable.setOneShot(false);
                // 2. 获取动画对象
                animationDrawable.start();
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.transition);
                // 1. 设置动画
                transitionDrawable = (TransitionDrawable) imageView.getDrawable();
                transitionDrawable.startTransition(2000);
                imageView.setAnimation(translateAnimation);
            }
        });

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Drawable[] layers = new Drawable[2];
                    layers[0] = MainActivity.this.getDrawable(R.drawable.ic_flag_home_new);
                    layers[1] = MainActivity.this.getDrawable(R.drawable.ic_flag_home_sale);
                    layerDrawable = new LayerDrawable(layers);
                    layerDrawable.setLayerInset(0, 0, 0, 0, 0);
                    layerDrawable.setLayerInset(1, 20, 20, 20, 20);
                    imageView.setImageDrawable(layerDrawable);
                }
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView2.animate().setDuration(500);
                // 步骤1:创建 需要设置动画的 视图View
                Animation translateAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.view_animation);
                // 步骤2:创建 动画对象 并传入设置的动画效果xml文件
                imageView2.startAnimation(translateAnimation);
                // 步骤3:播放动画
                //handler.sendEmptyMessage(1000);

                //handler.postDelayed(MyThread,300);

                animate_layout.startAnimate();

            }
        });

        animate_layout.setAnimateEndListener(new AnimateLayout.AnimateEndListener() {
            @Override
            public void animateEnd() {
                Log.d("MV","自定义动画结束==================");
                animate_layout.startAnimate();
            }
        });
        initData();
    }

    public void initData(){
        for (int i=0;i<16;i++){
            list.add(i);
        }
        adapter.notifyDataSetChanged();
    }

    private int viewCount;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1000:
                    if (listView.getChildCount() == list.size()) {
                        int count = listView.getChildCount();
                        if (viewCount == count){
                            viewCount = 0;
                        }
                        for (int i = viewCount; i < count; i++) {
                            View view =  listView.getChildAt(i);
                            AnimateLayout animateLayout = view.findViewById(R.id.animate_layout);
                            animateLayout.setAnimateEndListener(MainActivity.this);
                            animateLayout.startAnimate();
                            break;
                        }
                    }
                    break;
            }
        }
    };

    @Override
    public void animateEnd() {
        viewCount++;
        handler.sendEmptyMessage(1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
