package com.dianxinos.optimizer;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dianxinos.optimizer.view.FourThreeLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhangtengyuan on 2017/1/14.
 */
public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements IDataLoadingImpl.DataLoadingCallbacks {

    public static final int REQUEST_CODE_VIEW_SHOT = 5407;

    private static final int TYPE_TREASURE = 0;
    private static final int TYPE_TOP_THREE_TREASURE = 1;
    private static final int TYPE_HOME_CARD = 2;
    private static final int TYPE_LOADING_MORE = -1;

    // we need to hold on to an activity ref for the shared element transitions :/
    private final Activity mActivity;
    private final LayoutInflater layoutInflater;
    private final @Nullable
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
//            case TYPE_TOP_THREE_TREASURE:
//                return createDribbbleShotHolder(parent);
            case TYPE_HOME_CARD:
                return createHomeCardHolder(parent);
//            case TYPE_LOADING_MORE:
//                return flag_new LoadingMoreHolder(layoutInflater.inflate(R.layout.infinite_loading, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_TREASURE:
                bindTreasureHolder((TreasureBean) getItem(position), (HomeTreasureHolder) holder);
                break;
//            case TYPE_TOP_THREE_TREASURE:
//                bindDribbbleShotHolder(
//                        (Shot) getItem(position), (DribbbleShotHolder) holder, position);
//                break;
            case TYPE_HOME_CARD:
                buildHomeCardHolder((HomeCardBean) getItem(position), (HomeCardHolder) holder);
                break;
//            case TYPE_LOADING_MORE:
//                bindLoadingViewHolder((LoadingMoreHolder) holder, position);
//                break;
        }
    }


    private void bindTreasureHolder(final TreasureBean bean, final HomeTreasureHolder holder) {
        holder.title.setText(bean.title);
        holder.title.setAlpha(1f); // interrupted add to pocket anim can mangle
        holder.imageView.setImageResource(R.drawable.treasure_box_icon_home_appmgr);

        holder.itemView.setTransitionName(bean.url);
    }

    private void buildHomeCardHolder(HomeCardBean item, HomeCardHolder holder) {
        holder.title.setText(item.title);
        holder.content.setText("content: " + item.title);

        holder.layout.setBackgroundResource(R.color.common_blue_alpha_20);
        holder.itemView.setTransitionName(item.url);

    }

    /**
     * onViewRecycled 需要主动释放大图资源，参考如下官方解释
     *
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
                        //todo action.
                    }
                }
        );
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View commentsView) {
                Toast.makeText(mActivity, "ImageView Click",Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }


    private HomeCardHolder createHomeCardHolder(ViewGroup parent) {

        final HomeCardHolder holder = new HomeCardHolder(layoutInflater.inflate(
                R.layout.home_treasure_function_card_holder, parent, false));
//        holder.itemView.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        final TreasureBean treasureBean = (TreasureBean) getItem(holder.getAdapterPosition());
//                    }
//                }
//        );
//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View commentsView) {
//                Toast.makeText(mActivity, "ImageView Click",Toast.LENGTH_SHORT).show();
//            }
//        });
        return holder;
    }

  /*  @NonNull
    private DribbbleShotHolder createDribbbleShotHolder(ViewGroup parent) {
        final DribbbleShotHolder holder = flag_new DribbbleShotHolder(
                layoutInflater.inflate(R.layout.dribbble_shot_item, parent, false));
        holder.image.setBadgeColor(initialGifBadgeColor);
        holder.image.setOnClickListener(flag_new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = flag_new Intent();
                intent.setClass(mActivity, DribbbleShot.class);
                intent.putExtra(DribbbleShot.EXTRA_SHOT,
                        (Shot) getItem(holder.getAdapterPosition()));
                setGridItemContentTransitions(holder.image);
                ActivityOptions options =
                        ActivityOptions.makeSceneTransitionAnimation(mActivity,
                                Pair.create(view, mActivity.getString(R.string.transition_shot)),
                                Pair.create(view, mActivity.getString(R.string
                                        .transition_shot_background)));
                mActivity.startActivityForResult(intent, REQUEST_CODE_VIEW_SHOT, options.toBundle());
            }
        });
        // play animated GIFs whilst touched
        holder.image.setOnTouchListener(flag_new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // check if it's an event we care about, else bail fast
                final int action = event.getAction();
                if (!(action == MotionEvent.ACTION_DOWN
                        || action == MotionEvent.ACTION_UP
                        || action == MotionEvent.ACTION_CANCEL)) return false;

                // get the image and check if it's an animated GIF
                final Drawable drawable = holder.image.getDrawable();
                if (drawable == null) return false;
                GifDrawable gif = null;
                if (drawable instanceof GifDrawable) {
                    gif = (GifDrawable) drawable;
                } else if (drawable instanceof TransitionDrawable) {
                    // we fade in images on load which uses a TransitionDrawable; check its layers
                    TransitionDrawable fadingIn = (TransitionDrawable) drawable;
                    for (int i = 0; i < fadingIn.getNumberOfLayers(); i++) {
                        if (fadingIn.getDrawable(i) instanceof GifDrawable) {
                            gif = (GifDrawable) fadingIn.getDrawable(i);
                            break;
                        }
                    }
                }
                if (gif == null) return false;
                // GIF found, start/stop it on press/lift
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        gif.start();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        gif.stop();
                        break;
                }
                return false;
            }
        });
        return holder;
    }

    private void bindDribbbleShotHolder(final Shot shot,
                                        final DribbbleShotHolder holder,
                                        int position) {
        final int[] imageSize = shot.images.bestSize();
        Glide.with(mActivity)
                .load(shot.images.best())
                .listener(flag_new RequestListener<String, GlideDrawable>() {

                    @Override
                    public boolean onResourceReady(GlideDrawable resource,
                                                   String model,
                                                   Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache,
                                                   boolean isFirstResource) {
                        if (!shot.hasFadedIn) {
                            holder.image.setHasTransientState(true);
                            final ObservableColorMatrix cm = flag_new ObservableColorMatrix();
                            final ObjectAnimator saturation = ObjectAnimator.ofFloat(
                                    cm, ObservableColorMatrix.SATURATION, 0f, 1f);
                            saturation.addUpdateListener(flag_new ValueAnimator.AnimatorUpdateListener
                                    () {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    // just animating the color matrix does not invalidate the
                                    // drawable so need this update listener.  Also have to create a
                                    // flag_new CMCF as the matrix is immutable :(
                                    holder.image.setColorFilter(flag_new ColorMatrixColorFilter(cm));
                                }
                            });
                            saturation.setDuration(2000L);
                            saturation.setInterpolator(getFastOutSlowInInterpolator(mActivity));
                            saturation.addListener(flag_new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    holder.image.clearColorFilter();
                                    holder.image.setHasTransientState(false);
                                }
                            });
                            saturation.start();
                            shot.hasFadedIn = true;
                        }
                        return false;
                    }

                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable>
                            target, boolean isFirstResource) {
                        return false;
                    }
                })
                .placeholder(shotLoadingPlaceholders[position % shotLoadingPlaceholders.length])
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .fitCenter()
                .override(imageSize[0], imageSize[1])
                .into(flag_new DribbbleTarget(holder.image, false));
        // need both placeholder & background to prevent seeing through shot as it fades in
        holder.image.setBackground(
                shotLoadingPlaceholders[position % shotLoadingPlaceholders.length]);
        holder.image.showBadge(shot.animated);
        // need a unique transition name per shot, let's use it's url
        holder.image.setTransitionName(shot.html_url);
    }

    @NonNull
    private ProductHuntStoryHolder createProductHuntStoryHolder(ViewGroup parent) {
        final ProductHuntStoryHolder holder = flag_new ProductHuntStoryHolder(
                layoutInflater.inflate(R.layout.product_hunt_item, parent, false));
        holder.comments.setOnClickListener(flag_new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTabActivityHelper.openCustomTab(
                        mActivity,
                        flag_new CustomTabsIntent.Builder()
                                .setToolbarColor(ContextCompat.getColor(mActivity, R.color.product_hunt))
                                .addDefaultShareMenuItem()
                                .build(),
                        Uri.parse(((Post) getItem(holder.getAdapterPosition())).discussion_url));
            }
        });
        holder.itemView.setOnClickListener(flag_new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTabActivityHelper.openCustomTab(
                        mActivity,
                        flag_new CustomTabsIntent.Builder()
                                .setToolbarColor(ContextCompat.getColor(mActivity, R.color.product_hunt))
                                .addDefaultShareMenuItem()
                                .build(),
                        Uri.parse(((Post) getItem(holder.getAdapterPosition())).redirect_url));
            }
        });
        return holder;
    }

    private void bindProductHuntPostView(final Post item, ProductHuntStoryHolder holder) {
        holder.title.setText(item.name);
        holder.tagline.setText(item.tagline);
        holder.comments.setText(String.valueOf(item.comments_count));
    }
*/
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
            } else if (item instanceof TopThreeTreasureBean) {
                return TYPE_TOP_THREE_TREASURE;
            } else if (item instanceof HomeCardBean) {
                return TYPE_HOME_CARD;
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

    public void removeDataSource(String dataSource) {
        for (int i = items.size() - 1; i >= 0; i--) {
            HomeItem item = items.get(i);
            if (dataSource.equals(item.dataSource)) {
                items.remove(i);
            }
        }
        notifyDataSetChanged();
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

//        return flag_new Class[] { DesignerNewsStoryHolder.class, ProductHuntStoryHolder.class };

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
/*

    */
/* package *//*
 static class ProductHuntStoryHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.hunt_title) TextView title;
        @BindView(R.id.tagline) TextView tagline;
        @BindView(R.id.story_comments) TextView comments;

        ProductHuntStoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
*/

    /* package */ static class LoadingMoreHolder extends RecyclerView.ViewHolder {

        ProgressBar progress;

        LoadingMoreHolder(View itemView) {
            super(itemView);
            progress = (ProgressBar) itemView;
        }

    }

    static class HomeCardHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.function_card_title)
        TextView title;
        @BindView(R.id.function_card_content)
        TextView content;
        @BindView(R.id.function_card_background)
        FourThreeLinearLayout layout;


        public HomeCardHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
