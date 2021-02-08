package com.konone.review.activity.views.customrecyclerview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

/**
 *
 * RecyclerView的定位:
 * RecyclerView在Adapter和LayoutManager之间引入了另一种抽象级别, 以便能够在布局计算期间批量检测数据集更改。
 * 这样可以避免LayoutManager跟踪适配器更改以计算动画。 这也有助于提高性能。因为所有视图绑定都同时发生，避免了不必要的绑定。
 *
 * 使用RecyclerView的优化思路：
 *  - 数据的处理和View抽离开 (不要在主线程对耗时数据处理做分析)
 *  - 通过布局的优化来避免过度绘制 (尽可能减少布局的层次。 1.如果可以的话，使用代码new View()而非inflate xml的方式来创建item;
 *                  2.针对复杂的嵌套itemView，可以使用自定义View的方式来减少层级)
 *  - 如果item的高度是固定的，可以同时setHasFixedSize(true)来固定item高度，减少requestLayout时(onMeasure)的计算耗时
 *  - 增加mCachedView的缓存大小(setItemViewCacheSize)，以空间换时间的方式，来优化性能 (即减少ViewHolder进入pool的次数，降低bindViewHolder的次数)
 *  - 加入滑动监听的listener (RecyclerView.addOnScrollListener(listener))，在用户快速滑动的过程中，停止数据的加载
 *  - 当多个Recycler的Adapter是一样的时，可以通过共用同一个RecycledViewPool来实现pool缓存共享 (因为put到pool的ViewHolder已经不带有数据绑定等信息了，可以共享缓存的ViewHolder)
 *  - 使用payload提高渲染的效率 (通过指定payload来更新对应的控件，而非full update)
 *      1.在notifyItemChange时传入指定的payload
 *      2.在重写Adapter的onBindViewHolder(@NonNull VH holder, int position, @NonNull List<Object> payloads)时，
 *        根据payloads的具体内容，做对应itemView中子view的更新即可。etc：
 *          if (payloads.isEmpty()) { //payload为空，执行full update
 *              onBindViewHolder()
 *          } else {
 *              //payloads第一个属性控制背景View是否显示，不用重新更新holder中所有view，只需要绘制单个view即可，减少绘制的成本，提高渲染的效率
 *              holder.mBgView.setVisibility(((boolean) payloads.get(0)) ? View.VISIBLE : View.INVISIBLE);
 *          }
 *
 */
class CustomRecyclerView constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
    ViewGroup(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        /**
         * 1. dispatchLayoutStep1
         *  - 适配器更新
         *  - 决定应运行哪个动画
         *  - 保存有关当前视图的信息
         *  - 如有必要(mState.mRunPredictiveAnimations)，运行pre-layout并保存其信息
         *      因此，在第一次布局的时候，并不会触发pre-layout。
         *      只有当notify change (mItemsAddedOrRemoved || mItemsChanged)时才会被触发
         *      pre-layout的目的是：通过saveOldPosition方法将屏幕中各位置上的ViewHolder的坐标记录下来，并在重新布局之后，通过对比实现Item的动画效果
         *
         * 2. dispatchLayoutStep2
         *  - 我们为最终状态进行视图的实际布局计算。 (execute for real-layout)
         *  - 如有必要，此步骤可以多次运行（例如，测量）
         *
         * 3. setMeasuredDimensionFromChildren
         */
        //如果item的高度是固定的，可以通过setHasFixedSize(true)来减少onMeasure时的计算耗时
        /*if (mHasFixedSize) {
            mLayout.onMeasure(mRecycler, mState, widthSpec, heightSpec)
            return
        }*/
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        /**
         * 关键处理基本同onMeasure时的
         * dispatchLayoutStep1
         * dispatchLayoutStep2
         * dispatchLayoutStep3
         *  布局的最后一步，我们保存有关动画视图，触发动画以及进行任何必要清理的信息。
         */
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

    class State {
        val STEP_START = 1
        val STEP_LAYOUT = 1 shl 1
        val STEP_ANIMATIONS = 1 shl 2
        var mInPreLayout = false
    }

    /**
     * ViewHolder描述了项目视图以及有关其在RecyclerView中的位置的元数据。
     * {@link Adapter}实现应将ViewHolder子类化，并添加用于缓存可能昂贵的{@link View＃findViewById（int）}结果的字段。
     * {@link LayoutParams}属于{@link LayoutManager}，而{@link ViewHolder ViewHolders}属于{@link Adapter}。
     *
     * ViewHolder会持有recyclerView的弱引用
     */
    abstract class ViewHolder {
        val itemView: View? = null
        var mNestedRecyclerView: WeakReference<CustomRecyclerView>? = null
        var mPosition = RecyclerView.NO_POSITION
        var mOldPosition = RecyclerView.NO_POSITION
        var mItemId = RecyclerView.NO_ID
        var mItemViewType = RecyclerView.INVALID_TYPE
        var mPreLayoutPosition = RecyclerView.NO_POSITION

        /**
         * 在notifyItemChanged时指定payload，使得onBindViewHolder时只更新指定需要更新的view，提高渲染效率
         */
        var mPayloads: ArrayList<Any>? = null
    }
}