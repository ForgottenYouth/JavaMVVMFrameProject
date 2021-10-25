/**
 * FileName: IRecycleViewClickListener
 * Author: shiwenliang
 * Date: 2021/10/21 14:04
 * Description: 列表点击监听
 */
package com.leon.base.recycleview;

import com.leon.base.mvvm.model.BaseDataModel;

public interface IRecycleViewItemClickListener<T extends BaseDataModel> {

    void onRecycleViewItemClick(int position ,T item);
}
