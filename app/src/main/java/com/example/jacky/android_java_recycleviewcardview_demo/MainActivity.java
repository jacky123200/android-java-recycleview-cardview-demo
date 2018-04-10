package com.example.jacky.android_java_recycleviewcardview_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int buttonIDs[]={R.id.btn_recycleview_gridlayout,R.id.btn_recycleview_staggeredgridlayout,R.id.btn_recycleview_linearlayout,R.id.btn_cardViewLinearLayout};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init()
    {
        setOnClickListener();
    }

    private void setOnClickListener()
    {
        Button b= null;
       for(int i=0;i<buttonIDs.length;i++){
           if((b=(Button)findViewById(buttonIDs[i]))!=null)
               b.setOnClickListener(this);
       }
    }

    @Override
    public void onClick(View view)
    {
        Intent i  = new Intent(this,RecycleViewActivity.class);
        switch(view.getId())
        {
            case R.id.btn_recycleview_linearlayout:
                i.putExtra("layoutmanager","linearlayout");
                break;
            case R.id.btn_recycleview_gridlayout:
                i.putExtra("layoutmanager","gridlayout");
                break;
            case R.id.btn_recycleview_staggeredgridlayout:
                i.putExtra("layoutmanager","staggeredgridlayout");
                break;
            case R.id.btn_cardViewLinearLayout:
                i.putExtra("layoutmanager","linearlayout");
                i.putExtra("isCardView",true);
                break;
        }
        startActivity(i);
    }
}
