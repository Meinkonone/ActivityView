package com.konone.review.activity.views.customrecyclerview

import android.view.ViewGroup

/**
 * Created by konone on 2/5/21.
 * * <p>适配器模式
 *
 * 适配器提供从特定于应用程序的数据集到{@link RecyclerView}中显示的视图的绑定
 * (即View和Data的绑定)
 *
 * 为什么要使用适配器模式
 *  - 把一个类的接口变换成客户端所期待的另一种接口，从而使原本接口不匹配而无法一起工作的两个类能够在一起工作。
 *  - 更好的复用/扩展
 *  - 解藕
 */
abstract class Adapter<VH : CustomRecyclerView.ViewHolder> {
    /**
     * 根据viewType创建不同内容的itemView
     */
    abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int)

    /**
     * 数据集和ViewHolder绑定在一起
     */
    abstract fun onBindViewHolder(holder: VH, position: Int)

    /**
     * 数据集和ViewHolder绑定在一起,带payloads参数
     */
    abstract fun onBindViewHolder(holder: VH, position: Int, payloads: List<Any>)

    /**
     *  获取当前position的itemView的类型
     *  RecyclerView的itemView可以使用不一样的类型
     */
    fun getItemViewType(poisiton: Int): Int {
        return 0
    }

    abstract fun getItemCount()
}