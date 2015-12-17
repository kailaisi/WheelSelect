package com.android.wheelselect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int START_NUM =0 ;
    private static final int END_NUM = 90;
    private MyAdapter mAdapter;

    private android.widget.TextView tvage;
    private android.support.v7.widget.RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initDatas();
    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(manager);
        mAdapter=new MyAdapter(this,START_NUM,END_NUM);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    mAdapter.highlightItem(getMiddlePosition());
                    //将位置移动到中间位置
                    ((LinearLayoutManager)recyclerView.getLayoutManager()).scrollToPositionWithOffset(getScrollPosition(),0);
                    System.out.println(getScrollPosition()+"");
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                tvage.setText(String.valueOf(getMiddlePosition() + START_NUM));
            }
        });
        mAdapter.highlightItem(getMiddlePosition());
    }

    /**
     * 获取中间位置的position
     * @return
     */
    private int getMiddlePosition() {
        return getScrollPosition()+(mAdapter.ITEM_NUM/2);
    }

    /**
     * 获取滑动值, 滑动偏移 / 每个格子宽度
     *
     * @return 当前值
     */
    private int getScrollPosition() {
        return (int) (((double) recyclerView.computeHorizontalScrollOffset()
                / (double) mAdapter.getItemWidth())+0.5f);
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.tvage = (TextView) findViewById(R.id.tv_age);
    }
}
