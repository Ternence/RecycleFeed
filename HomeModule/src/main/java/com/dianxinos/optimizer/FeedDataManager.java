package com.dianxinos.optimizer;

import android.content.Context;
import android.util.Log;

import com.dianxinos.optimizer.data.BaseDataManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhangtengyuan on 2017/1/14.
 */
public abstract class FeedDataManager extends BaseDataManager<HomeItem> implements IDataLoadingImpl{
    private static final String TAG = "FeedDataManager";

    public FeedDataManager(Context context) {
        super(context);

    }

    @Override
    public boolean isDataLoading() {
        return false;
    }

    @Override
    public void registerCallback(DataLoadingCallbacks callbacks) {

    }

    @Override
    public void unregisterCallback(DataLoadingCallbacks callbacks) {

    }

    /**
     * 终止拉取数据
     */
    public void cancelLoading() {

    }

    public void loadAllDataSources() {
        //Init default top items.
        final ArrayList<HomeItem> data = new ArrayList<>();
        data.add(new TreasureBean(0," 0 ", "  "));
        data.add(new TreasureBean(1," 1 ", "  "));
        data.add(new TreasureBean(2," 2 ", "  "));
        data.add(new TreasureBean(3," 3 ", "  "));
        data.add(new TreasureBean(4," 4 ", "  "));
        data.add(new TreasureBean(5," 5 ", "  "));
        data.add(new TreasureBean(6," 6 ", "  "));
        data.add(new TreasureBean(7," 7 ", "  "));
        data.add(new FeedBean(11));
        onDataLoaded(data);


        loadRecommendData(data);
    }

    private void loadRecommendData(final ArrayList<HomeItem> data) {
        RecommendService recommendIndexService = new Retrofit.Builder().baseUrl(RecommendService.SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RecommendService.class);

        final RecommendService recommendDetailsService = new Retrofit.Builder().baseUrl(RecommendService.SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RecommendService.class);

        Call<RecommendBean> index = recommendIndexService.getRecommendIndex("A000000000001793", 1, 23, 0, "8.10.0", "cn.opda.a.phonoalbumshoushou");
        index.enqueue(new Callback<RecommendBean>() {
            @Override
            public void onResponse(Call<RecommendBean> call, Response<RecommendBean> response) {
                RecommendBean recommendIndex = response.body();
                List<RecommendBean.ResponseBean.DatasBean.SyfeedBean> feedIndex = recommendIndex.getResponse().getDatas().getSyfeed();
                StringBuffer ids = new StringBuffer();
                for (int i = 0; i < feedIndex.size(); i++) {
                    RecommendBean.ResponseBean.DatasBean.SyfeedBean syfeedBean = feedIndex.get(i);
                    String id = syfeedBean.getId();
                    ids.append(id);
                    if (i < feedIndex.size() - 1) {
                        ids.append(",");
                    }
                }
                Log.i(TAG, "onResponse: recommend index " + ids.toString());

                Call<RecommendDetailsBean> details = recommendDetailsService.getRecommendDetails(ids.toString());
                details.enqueue(new Callback<RecommendDetailsBean>() {
                    @Override
                    public void onResponse(Call<RecommendDetailsBean> call, Response<RecommendDetailsBean> response) {
                        RecommendDetailsBean feeds = response.body();
                        List<RecommendDetailsBean.ResponseBean.DatasBean> datas = feeds.getResponse().getDatas();
                        for (int i = 0; i < datas.size(); i++) {
                            data.add(new FeedBean(datas.get(i)));
                        }
                        onDataLoaded(data);

                        Log.i(TAG, "onResponse: details = " + feeds.toString());
                    }

                    @Override
                    public void onFailure(Call<RecommendDetailsBean> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<RecommendBean> call, Throwable t) {
                Log.i(TAG, "onFailure: false");

            }
        });
    }
}
