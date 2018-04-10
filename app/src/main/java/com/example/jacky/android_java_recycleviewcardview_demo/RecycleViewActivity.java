package com.example.jacky.android_java_recycleviewcardview_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Jacky on 2018/4/10.
 */

public class RecycleViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview);

        Intent intent = getIntent();
        String layoutManagerType = intent.getStringExtra("layoutmanager");
        switch (layoutManagerType)
        {
            case "linearlayout":
                break;
            case "gridlayout":
                break;
            case "staggeredgridlayout":
                break;
        }
    }
}
