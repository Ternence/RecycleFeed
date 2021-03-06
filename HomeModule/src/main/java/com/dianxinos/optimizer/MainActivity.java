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

import java.io.IOException;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final int COLUMN_ONE = 1;
    private static final int COLUMN_THREE = 3;

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
                if (position < adapter.getTreasureCount()) {
                    return COLUMN_ONE;
                } else {
                    return COLUMN_THREE;
                }
            }
        });
        grid.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .color(Color.TRANSPARENT)
                .sizeResId(R.dimen.divider_treasure_bean_height)
                .build());

        grid.setItemAnimator(new FadeInAnimator());

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
