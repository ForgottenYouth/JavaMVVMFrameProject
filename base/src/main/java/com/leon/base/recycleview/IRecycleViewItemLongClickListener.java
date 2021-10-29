/**
 * FileName: IRecycleViewLongClickListener
 * Author: shiwenliang
 * Date: 2021/10/21 14:07
 * Description: 列表项的长按事件监听
 */
package com.leon.base.recycleview;

public interface IRecycleViewItemLongClickListener<T> {

    void onRecycleViewItemLongClick(int postion, T item);
}
