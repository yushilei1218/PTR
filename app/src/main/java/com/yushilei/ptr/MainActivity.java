package com.yushilei.ptr;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.yushilei.ptr.adapter.RecyAdapter;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class MainActivity extends AppCompatActivity implements PtrHandler {

    private PtrFrameLayout ptr;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ptr = (PtrFrameLayout) findViewById(R.id.ptr);

        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new GridLayoutManager(this, 2));
        recycler.setAdapter(new RecyAdapter(this));

        PtrUIHeader ptrUIHandler = new PtrUIHeader(this);
        ptr.addPtrUIHandler(ptrUIHandler);
        ptr.setHeaderView(ptrUIHandler);
        //ptr.autoRefresh();
        ptr.setPtrHandler(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ptr.autoRefresh();
    }

    String TAG = "PtrUIHeader";

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        Log.d(TAG, "checkCanDoRefresh");
        View view = recycler.getChildAt(0);
        int top = view.getTop();
        int pos = (int) view.getTag(R.id.id_pos);
        return pos == 0 && top <= 0;

    }

    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            ptr.refreshComplete();
            return true;
        }
    });

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        Log.d(TAG, "onRefreshBegin");
        mHandler.sendEmptyMessageDelayed(0x22, 2000);
    }
}
