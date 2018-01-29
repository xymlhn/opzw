package com.opzw.login.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.opzw.R;
import com.opzw.login.model.bean.Token;
import com.opzw.login.model.bean.User;

import com.opzw.login.presenter.UserPresenter;
import com.opzw.utils.DialogUtils;
import com.opzw.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;


/**
 * 文 件 名: LoginActivity
 * 创 建 人: Cartman
 * 创建日期: 2018/1/25 10:40
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class LoginActivity extends Activity implements ILoginView {
    protected Dialog progressDialog;
    UserPresenter userPresenter;
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
        userPresenter = new UserPresenter(this);

        Observable<CharSequence> observablePhone = RxTextView.textChanges(accountText);
        Observable<CharSequence> observableValid = RxTextView.textChanges(passwordText);

        Observable.combineLatest(observablePhone, observableValid, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence phone, CharSequence valid) throws Exception {
                return (phone.toString().length() > 0) && (valid.toString().length() >= 0);
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                RxView.enabled(loginBtn).accept(aBoolean);
                loginBtn.setBackgroundColor(getResources().getColor(aBoolean ? R.color.mainColor : R.color.disableColor));
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
                        userPresenter.login(map);
                    }
                });
    }

    @Override
    public void showLoading(int resId) {
        progressDialog = DialogUtils.showLoading(this, resId);
    }

    @Override
    public void showLoading() {
        progressDialog = DialogUtils.showLoading(this, R.string.loading);
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
        progressDialog = null;
    }

    @Override
    public void onUnknownError(@NonNull String error) {
        hideLoading();
        ToastUtils.showToastLong(this, error);
    }

    @Override
    public void onNetworkError() {
        hideLoading();
        ToastUtils.showToastLong(this, getString(R.string.error_unknown));
    }

    @Override
    public void onTimeout() {
        hideLoading();
        ToastUtils.showToastLong(this, getString(R.string.error_timeout));
    }

    @Override
    public void onLoginSuccess(Token user) {

    }
}
