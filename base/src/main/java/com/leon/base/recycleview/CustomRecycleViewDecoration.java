/**
 * FileName: CustomRecycleViewDecoration
 * Author: shiwenliang
 * Date: 2021/10/21 15:17
 * Description:
 */
package com.leon.base.recycleview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomRecycleViewDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private Paint paint;
    private int mType = 1;

    /*
     * TODO 线条的宽度，2的倍数
     */
    public CustomRecycleViewDecoration(int space) {
        this.space = space/2;
        paint = new Paint();
        paint.setColor(Color.WHITE);
    }

    /*
     * TODO 线条的宽度，2的倍数
     */
    public CustomRecycleViewDecoration(int space, @ColorInt int color, int type) {
        this.space = space/2;
        paint = new Paint();
        paint.setColor(Color.WHITE);
    }

    public CustomRecycleViewDecoration setColor(@ColorInt int color) {
        paint.setColor(color);
        return this;
    }

    public CustomRecycleViewDecoration setType(int type) {
        mType = type;
        return this;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        if (mType == 1) {
            //横线
            outRect.set(0, space, 0, space);
        } else if (mType == 2) {
            //竖线
            outRect.set(space, 0, space, 0);
        } else if (mType == 3) {
            //格子线
            outRect.set(space, space, space, space);
        }

    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mType == 1) {
            //横线
            drawHorizontalLine(c, parent, state);
        } else if (mType == 2) {
            //竖线
            drawVerticalLine(c, parent, state);
        } else if (mType == 3) {
            //格子线
            drawGridLine(c, parent, state);
        }
    }

    /*
     * TODO 画横线, 这里的parent其实是显示在屏幕显示的这部分
     */
    private void drawHorizontalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            //绘制顶线
            int topT = child.getTop() - params.topMargin - space;
            int topB = topT + space;
            c.drawRect(child.getLeft(), topT, child.getLeft()+child.getWidth(), topB, paint);

            //获得child的布局信息  绘制底线
            final int bottomT = child.getTop()+child.getHeight()+params.bottomMargin;
            final int bottomB = bottomT + space;
            c.drawRect(child.getLeft(), bottomT, child.getLeft()+child.getWidth(), bottomB, paint);
        }
    }

    /*
     * TODO 画竖线
     */
    private void drawVerticalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            //左侧一半的分割线
            final int leftL = child.getLeft() + params.leftMargin - space;
            final int leftR = leftL + space;
            c.drawRect(leftL, top, leftR, bottom, paint);

            //右侧一半的分割线
            final int rightL = child.getRight() + params.rightMargin;
            final int rightR = rightL + space;
            c.drawRect(rightL, top, rightR, bottom, paint);
        }
    }

    /*
     * TODO 上下左右划线
     */
    private void drawGridLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            //绘制顶线
            int topT = child.getTop() - params.topMargin - space;
            int topB = topT + space;
            c.drawRect(child.getLeft(), topT, child.getLeft() + child.getWidth(), topB, paint);

            //获得child的布局信息  绘制底线
            final int bottomT = child.getBottom() + params.bottomMargin;
            final int bottomB = bottomT + space;
            c.drawRect(child.getLeft(), bottomT, child.getLeft() + child.getWidth(), bottomB, paint);

            //左侧一半的分割线
            final int leftL = child.getLeft() + params.leftMargin - space;
            final int leftR = leftL + space;
            c.drawRect(leftL, child.getTop(), leftR, child.getTop()+child.getHeight(), paint);

            //右侧一半的分割线
            final int rightL = child.getRight() + params.rightMargin;
            final int rightR = rightL + space;
            c.drawRect(rightL, child.getTop(), rightR, child.getTop()+child.getHeight(), paint);
        }
    }

}