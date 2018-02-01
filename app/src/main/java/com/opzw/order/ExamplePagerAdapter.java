package com.opzw.order;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import java.util.List;

/**
 * Created by hackware on 2016/9/10.
 */

public class ExamplePagerAdapter extends FragmentPagerAdapter {

    List<Fragment> list;

    public ExamplePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ExamplePagerAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.list=list;
    }//写构造方法，方便赋值调用
    @Override
    public Fragment getItem(int arg0) {
        return list.get(arg0);
    }//根据Item的位置返回对应位置的Fragment，绑定item和Fragment

    @Override
    public int getCount() {
        return list.size();
    }//设置Item的数量

}
