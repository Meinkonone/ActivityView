package com.konone.review.activity.views.customrecyclerview

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool

/**
 * Created by konone on 2/5/21.
 *
 * Recycler负责管理报废或分离的项目视图以供重复使用。
 *
 * “废弃”视图是仍附加到其父RecyclerView的视图，但已标记为可删除或重复使用。
 * {@link LayoutManager}对Recycler的典型用法是获取适配器数据集的视图，该视图表示给定位置或项目ID上的数据。
 * 如果要重用的视图被认为是“脏”的，则将要求适配器重新绑定它。
 * 如果不是这样，则LayoutManager可以快速重用该视图，而无需进行进一步的工作。
 * 没有{@link android.view.View＃isLayoutRequested() }请求的布局的干净视图可以由LayoutManager重新定位，而无需重新测量。
 */
class Recycler {
    /**
     * 四级缓存
     *  - 显示在屏幕上的scrapView -->mAttachedScrap/mChangedScrap
     *  - mCachedViews -->默认大小为2 (可以通过setItemViewCacheSize(count:Int)修改)
     *      用来和mAttachedScrap交互，在用户滑动recyclerView时，移出屏幕的ViewHolder放置在这里
     *      不会清除ViewHolder的数据信息，因此从cachedViews里面复用ViewHolder时不需要bindViewHolder来更新其信息
     *  - Extension --> 用户自定义的缓存
     *      实现ViewCacheExtension抽象类，并将其对象通过RecyclerView.getRecycledViewPool().setViewCacheExtension设置到recycler中
     *  - RecycledViewPool -->默认大小为5 (可以通过RecyclerView.getRecycledViewPool().setMaxRecycledViews修改)
     *      用来和mCachedViews交互，当mCachedViews的缓存满了之后，滑动继续时，会根据队列的FIFO原则，将mCachedView里面的ViewHolder put到pool中
     *      会重置ViweHolder的数据信息，因此从pool里面复用ViewHolder时需要调用bindViewHolder来为ViewHolder对象重新绑定数据
     */
    val mAttachedScrap = ArrayList<CustomRecyclerView.ViewHolder>()
    var mChangedScrap: ArrayList<RecyclerView.ViewHolder>? = null
    val mCachedViews = ArrayList<RecyclerView.ViewHolder>()
    var mViewCacheExtension: RecyclerView.ViewCacheExtension? = null
    var mRecyclerPool: RecycledViewPool? = null
}