package com.opzw.guide;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.opzw.R;
import com.opzw.guide.nav.OpenCalligraphyFactory;
import com.prolificinteractive.parallaxpager.ParallaxContextWrapper;

public class GuideActivity extends AppCompatActivity{

    public static void openActivity(Context context) {
        Intent intent = new Intent(context, GuideActivity.class);
        context.startActivity(intent);
    }

    @Override protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(
                new ParallaxContextWrapper(newBase, new OpenCalligraphyFactory())
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, new GuideFragment())
                    .commit();
        }
    }
}
