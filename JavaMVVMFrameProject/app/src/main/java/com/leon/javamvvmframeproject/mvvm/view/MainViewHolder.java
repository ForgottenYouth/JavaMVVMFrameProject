/**
 * FileName: TitleView
 * Author: shiwenliang
 * Date: 2021/10/21 13:51
 * Description:
 */
package com.leon.javamvvmframeproject.mvvm.view;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.leon.base.customview.BaseCustomView;
import com.leon.javamvvmframeproject.R;
import com.leon.javamvvmframeproject.databinding.ListitemMainLayoutBinding;
import com.leon.javamvvmframeproject.mvvm.viewmodel.MainViewHolderViewModel;

public class MainViewHolder extends BaseCustomView<ListitemMainLayoutBinding, MainViewHolderViewModel> {
    public MainViewHolder(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.listitem_main_layout;
    }

    @Override
    public void onRootViewClicked(View view) {
        Toast.makeText(getContext(), "Item click", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initChildViews() {
        super.initChildViews();
        mBinding.btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void bindingDataToView(MainViewHolderViewModel mainViewHolderViewModel) {
        mBinding.setViewModel(mainViewHolderViewModel);
    }
}
