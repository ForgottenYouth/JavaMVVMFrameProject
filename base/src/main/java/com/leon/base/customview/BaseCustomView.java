/**
 * FileName: BaseCustomView
 * Author: shiwenliang
 * Date: 2021/10/21 13:37
 * Description:
 */
package com.leon.base.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseCustomView<BINDING extends ViewDataBinding, DATA extends BaseCustomViewModel> extends LinearLayout implements IBaseCustomView<DATA> {
    public BINDING mBinding;
    protected DATA mViewModel;

    public BaseCustomView(Context context) {
        super(context);
        init();
    }

    public BaseCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BaseCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = DataBindingUtil.inflate(layoutInflater, getLayout(), this, false);
        mBinding.getRoot().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onRootViewClicked(view);
            }
        });
        initChildViews();
        addView(mBinding.getRoot());
    }

    protected void initChildViews(){

    }

    //获取自定义view的布局
    public abstract int getLayout();

    public abstract void onRootViewClicked(View view);

    @Override
    public void setData(DATA data) {
        this.mViewModel = data;
        bindingDataToView(data);
        mBinding.executePendingBindings();
    }

    public abstract void bindingDataToView(DATA data);
}
