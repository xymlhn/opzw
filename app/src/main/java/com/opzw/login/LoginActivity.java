package com.opzw.login;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.opzw.App;
import com.opzw.R;
import com.opzw.bean.Result;
import com.opzw.bean.Token;

import com.opzw.main.MainActivity;
import com.opzw.service.ApiManager;
import com.opzw.service.CallbackWrapper;
import com.opzw.utils.DialogUtils;
import com.opzw.utils.SharedPrefUtils;
import com.opzw.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * 文 件 名: LoginActivity
 * 创 建 人: Cartman
 * 创建日期: 2018/1/25 10:40
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class LoginActivity extends Activity {
    protected Dialog progressDialog;
    private Button loginBtn;
    private EditText accountText;
    private EditText passwordText;

    public static void openActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBtn = findViewById(R.id.loginBtn);
        accountText = findViewById(R.id.account);
        passwordText = findViewById(R.id.password);

        Observable<CharSequence> observablePhone = RxTextView.textChanges(accountText);
        Observable<CharSequence> observableValid = RxTextView.textChanges(passwordText);

        Observable.combineLatest(observablePhone, observableValid, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence phone, CharSequence valid) throws Exception {
                return (phone.toString().length() > 0) && (valid.toString().length() > 0);
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                RxView.enabled(loginBtn).accept(aBoolean);
                loginBtn.setBackground(getResources().getDrawable(aBoolean ? R.drawable.shape : R.drawable.shape_disable));
            }
        });

        RxView.clicks(loginBtn)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Map<String, Object> map = new HashMap<String, Object>() {{
                            put("username", accountText.getText());
                            put("password", passwordText.getText());
                        }};
                        progressDialog = DialogUtils.showLoading(LoginActivity.this, R.string.loading);
                        ApiManager.getInstence().getService().login(map)
                                .subscribeOn(Schedulers.io())
                                .doOnNext(new Consumer<Result<Token>>() {
                                    @Override
                                    public void accept(Result<Token> tokenResult) throws Exception {
                                        //缓存token
                                        tokenResult.getData().setUserName(accountText.getText().toString());
                                        SharedPrefUtils.setParam(App.getContext(),SharedPrefUtils.TOKEN, new Gson().toJson(tokenResult.getData()));
                                    }
                                })
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new CallbackWrapper<Result<Token>>() {
                                    @Override
                                    protected void onSuccess(Result<Token> tokenResult) {
                                        hideLoading();
                                        MainActivity.openActivity(LoginActivity.this);
                                    }

                                    @Override
                                    protected void onFail(String t) {
                                        hideLoading();
                                        ToastUtils.showToastLong(LoginActivity.this,t);
                                    }
                                });
                    }
                });
    }



    private void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
        progressDialog = null;
    }


    public void onLoginSuccess(Token user) {

    }
}
