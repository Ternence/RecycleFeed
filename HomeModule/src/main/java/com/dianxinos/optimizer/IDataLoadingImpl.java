package com.dianxinos.optimizer;

/**
 * Created by zhangtengyuan on 2017/1/14.
 */

public interface IDataLoadingImpl {

    boolean isDataLoading();
    void registerCallback(DataLoadingCallbacks callbacks);
    void unregisterCallback(DataLoadingCallbacks callbacks);

    interface DataLoadingCallbacks {
        void dataStartedLoading();
        void dataFinishedLoading();
    }

}
