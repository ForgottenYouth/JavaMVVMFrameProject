/**
 * FileName: BaseDialog
 * Author: shiwenliang
 * Date: 2021/11/12 14:21
 * Description: 自定义对话框
 */
package com.leon.base.mvvm.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;

import com.leon.base.recycleview.BaseViewHolder;

public abstract class BaseDialog<BINDING extends ViewDataBinding,VIEWMODEL extends BaseViewHolder> extends DialogFragment {

    protected BINDING mDataBinding;
    protected VIEWMODEL mViewModel;


    public abstract @LayoutRes int getLayoutID();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutID(), container, false);
        return mDataBinding.getRoot();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }
}
