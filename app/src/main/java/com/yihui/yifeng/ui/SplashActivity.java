package com.yihui.yifeng.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yihui.yifeng.MainActivity;
import com.yihui.yifeng.R;

import java.util.Random;


/**
 * 进入页面
 * <p>
 * Created by yihui on 2018/1/22.
 */
public class SplashActivity extends Activity {

    private ImageView mBackgroundImage;

    private ImageView infoBgImg;

    private TextView mTitleText;

    private TextView mVersionText;

    private int[] ary = new int[] {R.drawable.pic_background_1, R.drawable.pic_background_2, R.drawable.pic_background_3, R.drawable.pic_background_4};

    private int getBgDrawable() {
        return ary[new Random().nextInt(ary.length)];
    }


    private void initInfoBg() {
        infoBgImg = findViewById(R.id.image_info_bg);
        infoBgImg.setImageDrawable(getResources().getDrawable(ary[0]));
    }


    private void initAdBg1() {
        mBackgroundImage = findViewById(R.id.image_background);
        mBackgroundImage.setImageDrawable(getResources().getDrawable(getBgDrawable()));

        Animation animImage = AnimationUtils.loadAnimation(this, R.anim.image_welcome);
        mBackgroundImage.startAnimation(animImage);
        animImage.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束时打开首页
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.activity_slide_in, R.anim.no_anim);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    private void initAdBg() {
        mBackgroundImage = findViewById(R.id.image_background);
        mBackgroundImage.setImageDrawable(getResources().getDrawable(getBgDrawable()));
        mBackgroundImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 点击
                Toast.makeText(SplashActivity.this, "点击了", Toast.LENGTH_SHORT).show();
            }
        });


        final TextView countDown = findViewById(R.id.splash_timedown);
        CountDownTimer timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                countDown.setText("倒计时:" + (l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                //动画结束时打开首页
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.activity_slide_in, R.anim.no_anim);
                finish();
            }
        };
        timer.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 下半的提示文案信息
        initInfoBg();


        // 上半的广告动画
        initAdBg();
    }
}
