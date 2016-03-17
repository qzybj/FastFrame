package com.frame.volleypackageframe;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.frame.volleypackageframe.module.home.ui.HomeActivity;
import com.frame.volleypackageframe.module.news.ui.NewsActivity;
import com.frame.volleypackageframe.ui.simple.ui.SimpleBaseAdapterActivity;
import com.frame.volleypackageframe.ui.simple.ui.SimpleMultiBaseAdapterActivity;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class MainActivity extends AppCompatActivity  {

    @ViewInject(R.id.fab1)
    private FloatingActionButton fab1;
    @Event(value = R.id.fab1,type = View.OnClickListener.class)
    private void clickFab1(View view) {
        Intent intent = new Intent(this, SimpleBaseAdapterActivity.class);
        startActivity(intent);
    }

    @ViewInject(R.id.fab2)
    private FloatingActionButton fab2;
    @Event(value = R.id.fab2,type = View.OnClickListener.class)
    private void clickFab2(View view) {
        Intent intent = new Intent(this, SimpleMultiBaseAdapterActivity.class);
        startActivity(intent);
    }
    @ViewInject(R.id.fab3)
    private FloatingActionButton fab3;
    @Event(value = R.id.fab3,type = View.OnClickListener.class)
    private void clickFab3(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    @ViewInject(R.id.fab4)
    private FloatingActionButton fab4;
    @Event(value = R.id.fab4,type = View.OnClickListener.class)
    private void clickFab4(View view) {
        Intent intent = new Intent(this, NewsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        x.view().inject(this);

        fab1.setVisibility(View.GONE);
        fab2.setVisibility(View.GONE);
        fab3.setVisibility(View.GONE);
        fab4.setVisibility(View.GONE);
    }
}
