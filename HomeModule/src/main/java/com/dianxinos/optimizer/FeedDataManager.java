package com.dianxinos.optimizer;

import android.content.Context;

import com.dianxinos.optimizer.data.BaseDataManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangtengyuan on 2017/1/14.
 */
public abstract class FeedDataManager extends BaseDataManager<HomeItem> implements IDataLoadingImpl{
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
        ArrayList<HomeItem> data = new ArrayList<>();
        data.add(new TreasureBean(0," 0 ", "  "));
        data.add(new TreasureBean(1," 1 ", "  "));
        data.add(new TreasureBean(2," 2 ", "  "));
        data.add(new TreasureBean(3," 3 ", "  "));
        data.add(new TreasureBean(4," 4 ", "  "));
        data.add(new TreasureBean(5," 5 ", "  "));
        data.add(new TreasureBean(6," 6 ", "  "));
        data.add(new TreasureBean(7," 7 ", "  "));
        data.add(new TreasureBean(7," 7 ", "  "));
        data.add(new TreasureBean(7," 7 ", "  "));
        data.add(new TreasureBean(7," 7 ", "  "));
        data.add(new TreasureBean(7," 7 ", "  "));
        data.add(new TreasureBean(7," 7 ", "  "));
        data.add(new TreasureBean(7," 7 ", "  "));
        data.add(new TreasureBean(7," 7 ", "  "));
        data.add(new TreasureBean(7," 7 ", "  "));
        data.add(new TreasureBean(7," 7 ", "  "));
        data.add(new TreasureBean(7," 7 ", "  "));

        onDataLoaded(data);

    }
}
