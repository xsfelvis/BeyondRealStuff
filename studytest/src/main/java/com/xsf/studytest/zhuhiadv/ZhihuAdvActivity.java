package com.xsf.studytest.zhuhiadv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xsf.studytest.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ZhihuAdvActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhihuadv);
        init();
    }

    private void init() {
        mRecyclerView = findViewById(R.id.id_recyclerview);
        List<String> mockDatas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mockDatas.add(i + "");
        }
        mRecyclerView.setLayoutManager(mLinearLayoutManager = new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new CommonAdapter<String>(ZhihuAdvActivity.this,
                R.layout.itemzhihuadv,
                mockDatas) {
            @Override
            protected void convert(ViewHolder holder, String o, int position) {
                if (position > 0 && position % 6 == 0) {
                    holder.setVisible(R.id.tv_title, false);
                    holder.setVisible(R.id.tv_desc, false);
                    holder.setVisible(R.id.iv_adv, true);
                } else {
                    holder.setVisible(R.id.tv_title, true);
                    holder.setVisible(R.id.tv_desc, true);
                    holder.setVisible(R.id.iv_adv, false);
                }
            }
        });


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int fPos = mLinearLayoutManager.findFirstVisibleItemPosition();
                int lPos = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                for (int i = fPos; i <= lPos; i++) {
                    View view = mLinearLayoutManager.findViewByPosition(i);
                    AdvImageView adImageView = view.findViewById(R.id.iv_adv);
                    if (adImageView.getVisibility() == View.VISIBLE) {
                        adImageView.setDy(mLinearLayoutManager.getHeight() - view.getTop());
                    }
                }
            }
        });

    }
}
