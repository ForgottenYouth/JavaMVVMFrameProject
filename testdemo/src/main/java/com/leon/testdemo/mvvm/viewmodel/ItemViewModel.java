/**
 * FileName: ItemViewModel
 * Author: shiwenliang
 * Date: 2021/10/25 15:02
 * Description:
 */
package com.leon.testdemo.mvvm.viewmodel;

import com.leon.base.customview.BaseCustomViewModel;

public class ItemViewModel extends BaseCustomViewModel<String> {
    @Override
    protected BaseCustomViewModel covertDataModelToViewModel(String s) {
        return new ItemViewModel();
    }
}
