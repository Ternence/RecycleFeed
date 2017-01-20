package com.dianxinos.optimizer;

/**
 * Created by zhangtengyuan on 2017/1/18.
 */
public class FeedBean extends HomeItem {

    public FeedBean(int id) {
        super(id);
    }

    public FeedBean(RecommendDetailsBean.ResponseBean.DatasBean datasBean) {
        super(datasBean);
    }
}
