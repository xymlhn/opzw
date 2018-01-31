package com.opzw.main;


import android.content.Context;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.opzw.R;
import com.opzw.base.BaseFragment;
import com.opzw.guide.TestFragment;
import com.opzw.guide.nav.BorderShape;
import com.opzw.home.HomeFragment;
import com.opzw.me.MeFragment;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavFragment extends BaseFragment implements View.OnClickListener{

    NavigationButton mNavHome;
    NavigationButton mNavSales;
    NavigationButton mNavMe;
    private Context mContext;
    private int mContainerId;
    private FragmentManager mFragmentManager;
    private NavigationButton mCurrentNavButton;
    private OnNavigationReselectListener mOnNavigationReselectListener;

    public NavFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nav;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

        ShapeDrawable lineDrawable = new ShapeDrawable(new BorderShape(new RectF(0, 1, 0, 0)));
        lineDrawable.getPaint().setColor(getResources().getColor(R.color.list_divider_color));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{
                new ColorDrawable(getResources().getColor(R.color.white)),
                lineDrawable
        });
        root.setBackgroundDrawable(layerDrawable);

        mNavHome = findView(R.id.nav_item_home);
        mNavSales = findView(R.id.nav_item_sales);
        mNavMe = findView(R.id.nav_item_me);

        mNavHome.setOnClickListener(this);
        mNavSales.setOnClickListener(this);
        mNavMe.setOnClickListener(this);
        mNavHome.init(R.drawable.tab_icon_home,
                R.string.main_tab_name_home,
                HomeFragment.class);

        mNavSales.init(R.drawable.tab_icon_sales,
                R.string.main_tab_name_sales,
                TestFragment.class);

        mNavMe.init(R.drawable.tab_icon_me,
                R.string.main_tab_name_my,
                MeFragment.class);


    }

    @Override
    public void onClick(View v) {

        NavigationButton nav = (NavigationButton) v;
        doSelect(nav);

    }



    public void setup(Context context, FragmentManager fragmentManager, int contentId, OnNavigationReselectListener listener) {
        mContext = context;
        mFragmentManager = fragmentManager;
        mContainerId = contentId;
        mOnNavigationReselectListener = listener;

        // do clear
        clearOldFragment();
        // do select first
        doSelect(mNavHome);
    }

    public void select(int index) {
        if (mNavMe != null)
            doSelect(mNavMe);
    }

    @SuppressWarnings("RestrictedApi")
    private void clearOldFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        List<Fragment> fragments = mFragmentManager.getFragments();
        if (transaction == null || fragments == null || fragments.size() == 0)
            return;
        boolean doCommit = false;
        for (Fragment fragment : fragments) {
            if (fragment != this && fragment != null) {
                transaction.remove(fragment);
                doCommit = true;
            }
        }
        if (doCommit)
            transaction.commitNow();
    }

    private void doSelect(NavigationButton newNavButton) {

        NavigationButton oldNavButton = null;
        if (mCurrentNavButton != null) {
            oldNavButton = mCurrentNavButton;
            if (oldNavButton == newNavButton) {
                onReselect(oldNavButton);
                return;
            }
            oldNavButton.setSelected(false);
        }
        newNavButton.setSelected(true);
        doTabChanged(oldNavButton, newNavButton);
        mCurrentNavButton = newNavButton;
    }

    private void doTabChanged(NavigationButton oldNavButton, NavigationButton newNavButton) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (oldNavButton != null) {
            if (oldNavButton.getFragment() != null) {
                ft.detach(oldNavButton.getFragment());
            }
        }
        if (newNavButton != null) {
            if (newNavButton.getFragment() == null) {
                Fragment fragment = Fragment.instantiate(mContext,
                        newNavButton.getClx().getName(), null);
                ft.add(mContainerId, fragment, newNavButton.getTag());
                newNavButton.setFragment(fragment);
            } else {
                ft.attach(newNavButton.getFragment());
            }
        }
        ft.commit();
    }


    private void onReselect(NavigationButton navigationButton) {
        OnNavigationReselectListener listener = mOnNavigationReselectListener;
        if (listener != null) {
            listener.onReselect(navigationButton);
        }
    }


    public interface OnNavigationReselectListener {
        void onReselect(NavigationButton navigationButton);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initData() {
        super.initData();
    }
}
