/**
 * FileName: TestMainAdapter
 * Author: shiwenliang
 * Date: 2021/10/25 15:07
 * Description:
 */
package com.leon.testdemo.mvvm.views;

import android.content.Context;

import com.leon.base.customview.BaseCustomView;
import com.leon.base.customview.BaseCustomViewModel;
import com.leon.base.customview.IBaseCustomView;
import com.leon.base.recycleview.BaseRecycleViewAdapter;
import com.leon.testdemo.mvvm.viewmodel.ItemViewModel;

public class TestMainAdapter extends BaseRecycleViewAdapter<String> {
    public TestMainAdapter(Context context) {
        super(context);
    }

    @Override
    public IBaseCustomView createCustomView() {
        return new ItemView(mContext);
    }

    @Override
    public BaseCustomViewModel getViewHolderViewModel(BaseCustomView holder, int position, String dataModel) {
        return new ItemViewModel();
    }
}
