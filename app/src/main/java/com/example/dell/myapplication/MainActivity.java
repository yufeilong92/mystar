package com.example.dell.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRlvContent;
    private ZiNestedScrollView mZinsw;
    public static String TAG = "【" + "MainActivity.class" + "】==";
    private ImageView mIvDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        mIvDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialoge dialoge = new Dialoge();
                dialoge.show(getSupportFragmentManager(), "2");
            }
        });
        int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
        int KEEP_ALIVE_TIME = 1;
        TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<Runnable>();
        ExecutorService executorService = new ThreadPoolExecutor(NUMBER_OF_CORES,
                NUMBER_OF_CORES*2, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT,
                taskQueue, new BackgroundThreadFactory(), new DefaultRejectedExecutionHandler());
    }

    private void initData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("测试呼叫" + i);
        }
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        mRlvContent.setLayoutManager(manager);
        RecyclerAdapter adapter = new RecyclerAdapter(this, list);
        mRlvContent.setAdapter(adapter);
        mZinsw.setScanScrollChangedListener(new ZiNestedScrollView.ISmartScrollChangedListener() {
            @Override
            public void onScrolledToBottom() {
                Log.e(TAG, "onScrolledToBottom:");
            }

            @Override
            public void onScrolledToTop() {
                Log.e(TAG, "onScrolledToTop: ");
            }
        });
    }

    private void initView() {
        mRlvContent = (RecyclerView) findViewById(R.id.rlv_content);
        mZinsw = (ZiNestedScrollView) findViewById(R.id.zinsw);
        mIvDialog = (ImageView) findViewById(R.id.iv_dialog);
    }

}
