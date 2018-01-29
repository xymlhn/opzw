package com.opzw.guide;

import android.app.Activity;
import android.os.Bundle;

import com.opzw.R;
import com.opzw.login.view.LoginActivity;
import com.opzw.main.MainActivity;

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

        Observable.interval(1, TimeUnit.SECONDS, Schedulers.io()).take(3)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return 3 - (aLong + 1);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (aLong == 0) {
//                            LoginActivity.openActivity(InitActivity.this);
                            MainActivity.openActivity(InitActivity.this);
                            finish();
                        }
                    }
                });

    }
}
