/**
 * FileName: BaseActivity
 * Author: shiwenliang
 * Date: 2021/10/21 16:26
 * Description:
 */
package com.leon.base.mvvm.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.leon.base.mvvm.viewmodel.BaseViewModel;

import java.lang.reflect.ParameterizedType;

public abstract class BaseActivity<BINDING extends ViewDataBinding, VIEWMODEL extends BaseViewModel> extends AppCompatActivity {

    protected BINDING mDataBinding;
    protected VIEWMODEL mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取最后一个泛型参数来创建viewmodel的实例
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Class<VIEWMODEL> cls = (Class<VIEWMODEL>) parameterizedType.getActualTypeArguments()[parameterizedType.getActualTypeArguments().length - 1];
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(cls);

        //通过databinding 来加载布局
        mDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        bindViewModel();

        //加载视图
        initViews();
    }

    //将viewmodel绑定到xml上
    public abstract void bindViewModel();

    //获取布局id
    public abstract int getLayoutId();

    //初始化页面视图
    public abstract void initViews();
}
