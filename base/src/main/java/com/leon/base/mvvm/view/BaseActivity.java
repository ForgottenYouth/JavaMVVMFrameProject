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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.leon.base.mvvm.viewmodel.BaseViewModel;
import com.leon.base.mvvm.viewmodel.ViewStatus;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseActivity<BINDING extends ViewDataBinding, VIEWMODEL extends BaseViewModel, DATA> extends AppCompatActivity implements Observer {

    protected BINDING mDataBinding;
    protected VIEWMODEL mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = createViewModel();

        //通过databinding 来加载布局
        mDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.getLifecycle().addObserver(mViewModel);
        bindViewModel();

        //加载视图
        initViews();

        mViewModel.mViewStatus.observe(this, this);
    }

    public abstract VIEWMODEL createViewModel();

    //将viewmodel绑定到xml上
    public abstract void bindViewModel();

    //获取布局id
    public abstract int getLayoutId();

    //初始化页面视图
    public abstract void initViews();

    //viewModel通知页面
    @Override
    public void onChanged(Object o) {
        if (o instanceof ViewStatus) {
            switch ((ViewStatus) o) {
                case LOADING:
                    break;
                case EMPTY:
                    break;
                case SHOWCONTENT:

                    break;
                case NOMOREDATA:

                    break;
                case REFRESH_ERROR:

                    break;
                case LOADMOREFAILED:
                    break;
                default:
                    break;
            }
        } else if (o instanceof List) {
            handleResponseData((List<DATA>) o);
        }
    }

    public abstract void handleResponseData(List<DATA> data);
}
