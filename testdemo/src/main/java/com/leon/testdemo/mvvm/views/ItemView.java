/**
 * FileName: ItemView
 * Author: shiwenliang
 * Date: 2021/10/25 14:59
 * Description:
 */
package com.leon.testdemo.mvvm.views;

import android.content.Context;
import android.view.View;

import com.leon.base.customview.BaseCustomView;
import com.leon.testdemo.R;
import com.leon.testdemo.databinding.ListitemMainLayoutBinding;
import com.leon.testdemo.mvvm.viewmodel.ItemViewModel;

public class ItemView extends BaseCustomView<ListitemMainLayoutBinding, ItemViewModel> {
    public ItemView(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.listitem_main_layout;
    }

    @Override
    public void onRootViewClicked(View view) {

    }

    @Override
    public void bindingDataToView(ItemViewModel itemViewModel) {
        setData(itemViewModel);
    }

}
