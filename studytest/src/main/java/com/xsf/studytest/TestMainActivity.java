package com.xsf.studytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xsf.studytest.spi.IAnimal;

import java.util.Iterator;
import java.util.ServiceLoader;


public class TestMainActivity extends AppCompatActivity implements View.OnClickListener {
    Button mBtnSpi;


    public static void jumpToTestMainActivity(Class claz, Context context) {
        Intent intent = new Intent();
        intent.setClass(context, claz);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);
        init();
    }

    private void init() {
        mBtnSpi = findViewById(R.id.btnSpi);
        mBtnSpi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnSpi) {
            testSpi();
        }
    }

    //spi技术相关学习
    private void testSpi() {
        //通过ServiceLoader来动态加载Service 其中load有几个方法重载
        ServiceLoader<IAnimal> serviceLoader = ServiceLoader.load(IAnimal.class);
        //ServiceLoader<IAnimal> serviceLoader = ServiceLoader.load(IAnimal.class,this.getClass().getClassLoader());
        //ServiceLoader<IAnimal> serviceLoader = ServiceLoader.load(IAnimal.class,IAnimal.class.getClassLoader());
        Iterator<IAnimal> it = serviceLoader.iterator();
        if (it.hasNext()) {
            it.next().run();
        }
    }
}
