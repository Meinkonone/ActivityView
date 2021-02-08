package com.konone.review.activity.views.customrecyclerview

/**
 * Created by konone on 2/5/21.
 *
 * LayoutManager负责在RecyclerView中测量和放置项目视图，并确定何时回收用户不再可见的项目视图的策略。
 * 即在自定义View的onMeasure/onLayout时，用来确定childView的大小和位置
 *
 * 通过更改LayoutManager，可以使用RecyclerView来实现标准的垂直滚动列表，统一网格，交错网格，水平滚动集合等。
 * 系统提供了常用的LayoutManager有：LinearLayoutManager，GridLayoutManager etc
 *
 * 如果LayoutManager指定一个默认构造函数或一个带有签名的构造函数（{@link Context}，{@ link AttributeSet}，{@ code int}，{@ code int}）
 * 则RecyclerView会实例化并在inflate时设置LayoutManager。然后可以从{@link #getProperties（Context，AttributeSet，int，int）}获得最常用的属性。
 */
class LayoutManager {

    //可能被调用多次（1.pre-layout; 2.real-layout）
    //因此需要对pre-layout做判断处理
    fun onLayoutChildren(recycler: Recycler, state: CustomRecyclerView.State) {
        if (state.mInPreLayout) { //handle pre-layout item anim
            //如果child view是可见的，并且我们要移动它，则应该沿相反的方向布置其他项目，以确保新的项目动画效果好，而不是淡入淡出 (pre-layout)
        }
    }
}