package com.dianxinos.optimizer;

/**
 * Created by zhangtengyuan on 2017/1/14.
 */
public class HomeItem {

    public int id;
    public String title;
    public String content;
    public String url; // can't be final as some APIs use different serialized names
    public String dataSource;
    public int page;
    public float weight; // used for sorting
    public int colspan;

    public RecommendDetailsBean.ResponseBean.DatasBean datasBean;

    public HomeItem(long id,
                    String title,
                    String url) {
        this.id = (int) id;
        this.title = title;
        this.url = url;
    }

    public HomeItem(long id) {
        this.id = (int) id;
    }

    public HomeItem() {
    }

    public HomeItem(RecommendDetailsBean.ResponseBean.DatasBean feedBean) {
        id = Integer.parseInt(feedBean.id);
        title = feedBean.title;
        datasBean = feedBean;
    }

    @Override
    public String toString() {
        return title;
    }

    /**
     * Equals check based on the id field
     */
    @Override
    public boolean equals(Object o) {
        return (o.getClass() == getClass() && ((HomeItem) o).id == id);
    }
}

