package com.dianxinos.optimizer;

/**
 * Created by zhangtengyuan on 2017/1/14.
 */
public abstract class HomeItem {

    public final long id;
    public final String title;
    public String url; // can't be final as some APIs use different serialized names
    public String dataSource;
    public int page;
    public float weight; // used for sorting
    public int colspan;

    public HomeItem(long id,
                    String title,
                    String url) {
        this.id = id;
        this.title = title;
        this.url = url;
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

