package com.gars.percents;

import android.app.Activity;
import android.os.Bundle;

import com.gars.percents.fragments.MainFragment;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // загружаем главную страницу
        getFragmentManager().beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commit();

    }

}
