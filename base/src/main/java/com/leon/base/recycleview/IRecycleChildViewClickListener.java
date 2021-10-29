/**
 * FileName: IRecycleItemViewClickListener
 * Author: shiwenliang
 * Date: 2021/10/21 14:09
 * Description: 列表项的某一个视图点击事件
 */
package com.leon.base.recycleview;

import android.view.View;

import com.leon.base.mvvm.model.BaseDataModel;

public interface IRecycleChildViewClickListener<T> {

    void onRecycleViewChildClick(int position, T item, View view);
}
