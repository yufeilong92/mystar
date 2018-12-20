package com.example.dell.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: ZiNestedScrollView.java
 * @Package com.example.dell.myapplication
 * @Description: todo
 * @author: YFL
 * @date: 2018/12/19 21:49
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018/12/19 星期三
 * 注意：本内容仅限于学川教育有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class ZiNestedScrollView extends NestedScrollView {
    public static String TAG = "【" + "ZiNestedScrollView.class" + "】==";
    private boolean isScrolledToTop = true;// 初始化的时候设置一下值
    private boolean isScrolledToBottom = false;
    private ISmartScrollChangedListener mSmartScrollChangedListener;

    /** 定义监听接口 */
    public interface ISmartScrollChangedListener {
        void onScrolledToBottom();
        void onScrolledToTop();
    }
    public void setScanScrollChangedListener(ISmartScrollChangedListener smartScrollChangedListener) {
        mSmartScrollChangedListener = smartScrollChangedListener;
    }

    public ZiNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ZiNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ZiNestedScrollView(@NonNull Context context) {
        super(context);
    }
    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (scrollY == 0) {
            isScrolledToTop = clampedY;
            isScrolledToBottom = false;
        } else {
            isScrolledToTop = false;
            isScrolledToBottom = clampedY;
        }
        notifyScrollChangedListeners();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (android.os.Build.VERSION.SDK_INT < 9) {  // API 9及之后走onOverScrolled方法监听
            if (getScrollY() == 0) {    // 小心踩坑1: 这里不能是getScrollY() <= 0
                isScrolledToTop = true;
                isScrolledToBottom = false;
            } else if (getScrollY() + getHeight() - getPaddingTop()-getPaddingBottom() == getChildAt(0).getHeight()) {
                // 小心踩坑2: 这里不能是 >=// 小心踩坑3（可能忽视的细节2）：这里最容易忽视的就是ScrollView上下的padding　
                isScrolledToBottom = true;
                isScrolledToTop = false;
            } else {
                isScrolledToTop = false;
                isScrolledToBottom = false;
            }
            notifyScrollChangedListeners();
        }
        // 有时候写代码习惯了，为了兼容一些边界奇葩情况，上面的代码就会写成<=,>=的情况，结果就出bug了
        // 我写的时候写成这样：getScrollY() + getHeight() >= getChildAt(0).getHeight()
        // 结果发现快滑动到底部但是还没到时，会发现上面的条件成立了，导致判断错误
        // 原因：getScrollY()值不是绝对靠谱的，它会超过边界值，但是它自己会恢复正确，导致上面的计算条件不成立
        // 仔细想想也感觉想得通，系统的ScrollView在处理滚动的时候动态计算那个scrollY的时候也会出现超过边界再修正的情况
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.e(TAG, "onStartNestedScroll: " + nestedScrollAxes);
        return super.onStartNestedScroll(child, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(View target) {
        super.onStopNestedScroll(target);

    }
    private void notifyScrollChangedListeners() {
        if (isScrolledToTop) {
            if (mSmartScrollChangedListener != null) {
                mSmartScrollChangedListener.onScrolledToTop();
            }
        } else if (isScrolledToBottom) {
            if (mSmartScrollChangedListener != null) {
                mSmartScrollChangedListener.onScrolledToBottom();
            }
        }
    }

    public boolean isScrolledToTop() {
        return isScrolledToTop;
    }

    public boolean isScrolledToBottom() {
        return isScrolledToBottom;
    }
}
