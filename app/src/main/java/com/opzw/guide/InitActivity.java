package com.opzw.guide;

import android.app.Activity;
import android.os.Bundle;

import com.opzw.R;
import com.opzw.bean.Token;
import com.opzw.login.LoginActivity;
import com.opzw.main.MainActivity;
import com.opzw.utils.SharedPrefUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class InitActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        Token token = SharedPrefUtils.getToken();
        if (token != null){
            MainActivity.openActivity(InitActivity.this);
        }else {
            LoginActivity.openActivity(InitActivity.this);
        }
        finish();

    }
}
