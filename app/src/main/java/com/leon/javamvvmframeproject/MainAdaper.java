/**
 * FileName: MainAdaper
 * Author: shiwenliang
 * Date: 2021/10/21 14:49
 * Description:
 */
package com.leon.javamvvmframeproject;

import android.content.Context;
import android.view.View;

import com.leon.base.customview.BaseCustomView;
import com.leon.base.customview.BaseCustomViewModel;
import com.leon.base.customview.IBaseCustomView;
import com.leon.base.recycleview.BaseViewHolder;
import com.leon.javamvvmframeproject.mvvm.model.AddressDataModel;
import com.leon.base.recycleview.BaseRecycleViewAdapter;
import com.leon.javamvvmframeproject.mvvm.view.MainViewHolder;
import com.leon.javamvvmframeproject.mvvm.viewmodel.MainViewHolderViewModel;
import com.leon.javamvvmframeproject.databinding.ListitemMainLayoutBinding;

public class MainAdaper extends BaseRecycleViewAdapter<ListitemMainLayoutBinding, AddressDataModel> {
    public MainAdaper(Context context) {
        super(context);
    }

    @Override
    public IBaseCustomView getCustomViewHoder() {
        return new MainViewHolder(mContext);
    }

    @Override
    public BaseCustomViewModel getViewHolderViewModel(BaseCustomView holder, int position, AddressDataModel dataModel) {
        ((MainViewHolder) holder).mBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemChildClickCallBack != null) {
                    mItemChildClickCallBack.onRecycleViewChildClick(position, dataModel, v);
                }
            }
        });
        return new MainViewHolderViewModel(dataModel);
    }
}
