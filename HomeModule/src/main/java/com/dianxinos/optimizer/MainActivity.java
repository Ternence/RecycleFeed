package com.dianxinos.optimizer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.dianxinos.optimizer.recycle.HorizontalDividerItemDecoration;

import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    @BindView(R.id.grid)
    RecyclerView grid;
    GridLayoutManager layoutManager;
    @BindInt(R.integer.num_columns)
    int columns;

    // data
    FeedDataManager dataManager;
    FeedAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //init toolbar and other ui component.

        //init feed data manager
        dataManager = new FeedDataManager(this) {
            @Override
            public void onDataLoaded(List<? extends HomeItem> data) {
                adapter.addAndResort(data);
            }
        };

        //init feed adapter
        adapter = new FeedAdapter(this, dataManager, columns);

        grid.setAdapter(adapter);
        layoutManager = new GridLayoutManager(this, 3);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Log.i(TAG, "getSpanSize: pos " + position);
                if (position < 6) {
                    // TODO:  根据百宝箱添加的 beans 个数进行配置 header （需要填充空 bean）
                    return 1;
                } else {
                    return 3;
                }
            }
        });
//        grid.addOnScrollListener(toolbarElevation);
//        grid.addOnScrollListener(flag_new InfiniteScrollListener(layoutManager, dataManager) {
//            @Override
//            public void onLoadMore() {
//                dataManager.loadAllDataSources();
//            }
//        });
        grid.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .color(Color.TRANSPARENT)
                .sizeResId(R.dimen.divider_treasure_bean_height)
                .build());

        grid.setItemAnimator(new DefaultItemAnimator());

        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        grid.setLayoutManager(layoutManager);
        grid.setHasFixedSize(true);
        grid.setNestedScrollingEnabled(false);


        //start load data .
        dataManager.loadAllDataSources();

        //check adapter whether is empty. And show loading.
    }


    @Override
    protected void onDestroy() {
        dataManager.cancelLoading();
        super.onDestroy();
    }

}
