package com.dianxinos.optimizer;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dianxinos.optimizer.view.FourThreeLinearLayout;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhangtengyuan on 2017/1/14.
 */
public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements IDataLoadingImpl.DataLoadingCallbacks {

    private static final int TYPE_TREASURE = 0;
    private static final int TYPE_TOP_THREE_TREASURE = 1;
    private static final int TYPE_FEED_CARD = 2;
    private static final int TYPE_LOADING_MORE = -1;

    // we need to hold on to an activity ref for the shared element transitions :/
    private final Activity mActivity;
    private final LayoutInflater layoutInflater;
    private final
    @Nullable
    IDataLoadingImpl mDataManager;
    private final int columns;

    private List<HomeItem> items;
    private boolean showLoadingMore = false;

    public FeedAdapter(Activity activity, @Nullable FeedDataManager dataManager, int columns) {
        this.mActivity = activity;
        this.mDataManager = dataManager;
        if (mDataManager != null) {
            mDataManager.registerCallback(this);
        }
        this.columns = columns;
        layoutInflater = LayoutInflater.from(mActivity);
        items = new ArrayList<>();
        setHasStableIds(true);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TREASURE:
                return createTreasureHolder(parent);
            case TYPE_FEED_CARD:
                return createHomeCardHolder(parent);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_TREASURE:
                bindTreasureHolder((TreasureBean) getItem(position), (HomeTreasureHolder) holder);
                break;
            case TYPE_FEED_CARD:
                buildHomeCardHolder((FeedBean) getItem(position), (HomeCardHolder) holder);
                break;
        }
    }


    private void bindTreasureHolder(final TreasureBean bean, final HomeTreasureHolder holder) {
        holder.title.setText(bean.title);
        holder.title.setAlpha(1f); // interrupted add to pocket anim can mangle
        holder.imageView.setImageResource(R.drawable.treasure_box_icon_home_appmgr);

        holder.itemView.setTransitionName(bean.url);
    }

    private void buildHomeCardHolder(FeedBean item, HomeCardHolder holder) {
        holder.title.setText(item.title);
        holder.content.setText(item.content);

        holder.layout.setBackgroundResource(R.color.common_blue_alpha_20);
        holder.itemView.setTransitionName(item.url);
        if (item.datasBean != null && !TextUtils.isEmpty(item.datasBean.iconUrl)) {
            Glide.with(mActivity)
                    .load(item.datasBean.iconUrl)
                    .centerCrop()
                    .placeholder(R.color.common_blue_alpha_20)
                    .crossFade()
                    .into(holder.image);
        }

    }

    /**
     * onViewRecycled 需要主动释放大图资源，参考如下官方解释
     * <p>
     * Called when a view created by this adapter has been recycled.
     * <p>
     * <p>A view is recycled when a {@link RecyclerView.LayoutManager} decides that it no longer
     * needs to be attached to its parent {@link RecyclerView}. This can be because it has
     * fallen out of visibility or a set of cached views represented by views still
     * attached to the parent RecyclerView. If an item view has large or expensive data
     * bound to it such as large bitmaps, this may be a good place to release those
     * resources.</p>
     *
     * @param holder
     */
    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
    }

    @NonNull
    private HomeTreasureHolder createTreasureHolder(ViewGroup parent) {
        final HomeTreasureHolder holder = new HomeTreasureHolder(layoutInflater.inflate(
                R.layout.home_treasure_item_holder, parent, false));
        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final TreasureBean treasureBean = (TreasureBean) getItem(holder.getAdapterPosition());
                        Intent intent = new Intent(mActivity, ActionActivity.class);
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mActivity,
                                Pair.create(v, mActivity.getString(R.string.transition_shot)));
                        mActivity.startActivityForResult(intent, 200, options.toBundle());
                    }
                }
        );
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View commentsView) {
            }
        });
        return holder;
    }


    private HomeCardHolder createHomeCardHolder(ViewGroup parent) {

        final HomeCardHolder holder = new HomeCardHolder(layoutInflater.inflate(
                R.layout.home_treasure_function_card_holder, parent, false));
        holder.layout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //执行添加到百宝箱操作
                        //1、生成 overlay，执行位移动画
                        final ViewGroup container = (ViewGroup) v.getParent();
                        final TextView button = holder.content;
                        container.getOverlay().add(button);

                        //0.5 是自身 height 的一半，0.25应该换成 Treasure Item View height 的一半
                        float moveDurationY = (float) ((holder.getAdapterPosition() - getTreasureCount() + 0.5 + 0.25) * holder.layout.getHeight());
                        float moveDurationX = 0;
                        switch (getTreasureCount() % 3) {
                            case 1:
                                moveDurationX = (float) (0.33 * holder.layout.getWidth());
                                break;
                            case 0:
                                moveDurationX = (float) (0.66 * holder.layout.getWidth());
                                //增加一行
                                moveDurationY -= holder.layout.getHeight() * 0.5;
                                break;
                            case 2:
                                moveDurationX = 0;
                                break;
                        }
                        ObjectAnimator animY = ObjectAnimator.ofFloat(button, "translationY", -moveDurationY);
                        ObjectAnimator animX = ObjectAnimator.ofFloat(button, "translationX", -moveDurationX);

                        animY.addListener(new Animator.AnimatorListener() {

                            @Override
                            public void onAnimationStart(Animator arg0) {
                                for (int i = 0; i < items.size(); i++) {
                                    HomeItem homeItem = items.get(i);
                                    if (homeItem != null && !(homeItem instanceof TreasureBean)) {
                                        items.add(i, new TreasureBean(getTreasureCount(), getTreasureCount() + " ", " "));
                                        notifyItemInserted(i);
                                        break;
                                    }
                                }
                            }

                            @Override
                            public void onAnimationRepeat(Animator arg0) {
                            }

                            @Override
                            public void onAnimationEnd(Animator arg0) {
                                container.getOverlay().remove(button);
                                removeDataSource(holder.getAdapterPosition());
                            }

                            @Override
                            public void onAnimationCancel(Animator arg0) {
                                container.getOverlay().remove(button);
                            }
                        });

                        animY.setDuration(500);
                        animX.setDuration(500);

                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(animX,animY);
                        set.start();
                        //2、动画结束时增加百宝箱 Items


                        //3、执行删除 Item 动画
                    }
                }
        );
        return holder;
    }

    private void bindLoadingViewHolder(LoadingMoreHolder holder, int position) {
        // only show the infinite load progress spinner if there are already items in the
        // grid i.e. it's not the first item & data is being loaded
        holder.progress.setVisibility((position > 0 && mDataManager.isDataLoading())
                ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public int getItemViewType(int position) {
        if (position < getDataItemCount() && getDataItemCount() > 0) {
            HomeItem item = getItem(position);
            if (item instanceof TreasureBean) {
                return TYPE_TREASURE;
            } else if (item instanceof FeedBean) {
                return TYPE_FEED_CARD;
            }
        }
        return TYPE_LOADING_MORE;
    }

    private HomeItem getItem(int position) {
        return items.get(position);
    }

    public int getItemColumnSpan(int position) {
        switch (getItemViewType(position)) {
            case TYPE_LOADING_MORE:
                return columns;
            default:
                return getItem(position).colspan;
        }
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    /**
     * Main entry point for adding items to this adapter. Takes care of de-duplicating items and
     * sorting them (depending on the data source). Will also expand some items to span multiple
     * grid columns.
     */
    public void addAndResort(List<? extends HomeItem> newItems) {
//        weighItems(newItems);
        deduplicateAndAdd(newItems);
//        expandPopularItems();
        notifyDataSetChanged();
    }

    /**
     * De-dupe as the same item can be returned by multiple feeds
     */
    private void deduplicateAndAdd(List<? extends HomeItem> newItems) {
        final int count = getDataItemCount();
        for (HomeItem newItem : newItems) {
            boolean add = true;
            for (int i = 0; i < count; i++) {
                HomeItem existingItem = getItem(i);
                if (existingItem.equals(newItem)) {
                    add = false;
                    break;
                }
            }
            if (add) {
                add(newItem);
            }
        }
    }

    private void add(HomeItem item) {
        items.add(item);
    }

    public void removeDataSource(int id) {
        if (id > 0 && id < items.size()) {
            items.remove(id);
            notifyItemRemoved(id);

        }
    }

    @Override
    public long getItemId(int position) {
        if (getItemViewType(position) == TYPE_LOADING_MORE) {
            return -1L;
        }
        return getItem(position).id;
    }

    public int getItemPosition(final long itemId) {
        for (int position = 0; position < items.size(); position++) {
            if (getItem(position).id == itemId) return position;
        }
        return RecyclerView.NO_POSITION;
    }

    @Override
    public int getItemCount() {
        return getDataItemCount() + (showLoadingMore ? 1 : 0);
    }

    public int getDataItemCount() {
        return items.size();
    }

    private int getLoadingMoreItemPosition() {
        return showLoadingMore ? getItemCount() - 1 : RecyclerView.NO_POSITION;
    }

    @Override
    public void dataStartedLoading() {
        if (showLoadingMore) return;
        showLoadingMore = true;
        notifyItemInserted(getLoadingMoreItemPosition());
    }

    @Override
    public void dataFinishedLoading() {
        if (!showLoadingMore) return;
        final int loadingPos = getLoadingMoreItemPosition();
        showLoadingMore = false;
        notifyItemRemoved(loadingPos);
    }

    /**
     * 当前百宝箱Beans展示数量,用户Feed顶部recycle view 列数判断
     * 根据百宝箱添加的 beans 个数进行配置 header （需要填充空 bean）
     * @return
     */
    public int getTreasureCount() {
        int count = 0;

        for (HomeItem item : items) {
            if (item != null && item instanceof TreasureBean) {
                count++;
            }
        }
        return count;
    }

    static class HomeTreasureHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_title)
        TextView title;
        @BindView(R.id.item_img)
        ImageView imageView;

        HomeTreasureHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class LoadingMoreHolder extends RecyclerView.ViewHolder {

        ProgressBar progress;

        LoadingMoreHolder(View itemView) {
            super(itemView);
            progress = (ProgressBar) itemView;
        }

    }

    static class HomeCardHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.function_card_title)
        TextView title;
        @BindView(R.id.function_card_content)
        TextView content;
        @BindView(R.id.function_card_background)
        FourThreeLinearLayout layout;
        @BindView(R.id.feed_image)
        ImageView image;


        public HomeCardHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
