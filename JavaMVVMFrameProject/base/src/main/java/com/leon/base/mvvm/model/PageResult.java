/**
 * FileName: PageResult
 * Author: shiwenliang
 * Date: 2021/10/22 10:29
 * Description:
 */
package com.leon.base.mvvm.model;

public final class PageResult {
    public boolean mIsFirstPage;//标记是否是第一页
    public boolean mHasNextPage;//是否有下一页
    public boolean mIsEmpty;//是否为空

    public PageResult(boolean isFirstPage, boolean hasNextPage, boolean isEmpty) {
        this.mIsFirstPage = isFirstPage;
        this.mHasNextPage = hasNextPage;
        this.mIsEmpty = isEmpty;
    }
}
