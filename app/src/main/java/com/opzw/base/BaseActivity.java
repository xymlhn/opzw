package com.opzw.base;
/**
 * 文 件 名: BaseActivity
 * 创 建 人: Cartman
 * 创建日期: 2017/10/27 11:18
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：activity基类
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.opzw.R;
import com.opzw.utils.DialogUtils;
import com.opzw.utils.ToastUtils;


/**
 * The type Base activity.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        onBackClick();
        setListener();
        initData();
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            return;
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(0xff508DFF);
    }

    /**
     * 设置ContentView
     *
     * @return R.layout.xxx
     */
    protected abstract int getLayoutId();

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * add Listener
     */
    protected abstract void setListener();

    /**
     * 初始化数据
     */
    protected abstract void initData();


    protected void initTitle(int title) {
        ((TextView) findViewById(R.id.titleText)).setText(title);
    }

    public void addBackClick(View.OnClickListener listener) {
        ImageButton backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(listener);
    }

    public void addRightBtnClick(int resId, View.OnClickListener listener) {
        ImageButton rightBtn = findViewById(R.id.rightBtn);
        rightBtn.setBackgroundResource(resId);
        rightBtn.setVisibility(View.VISIBLE);
        rightBtn.setOnClickListener(listener);
    }

    /*隐藏返回按钮*/
    public void hideBack() {
        ImageButton backBtn = findViewById(R.id.backBtn);
        backBtn.setVisibility(View.GONE);
    }

    /*返回按钮默认点击事件*/
    private void onBackClick() {
        this.addBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

}
