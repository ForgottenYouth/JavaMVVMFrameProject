/**
 * FileName: BaseFragment
 * Author: shiwenliang
 * Date: 2021/10/25 11:34
 * Description:
 */
package com.leon.base.mvvm.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.leon.base.mvvm.viewmodel.BaseViewModel;
import com.leon.base.mvvm.viewmodel.ViewStatus;

import java.util.List;

public abstract class BaseFragment<BINDING extends ViewDataBinding, VIEWMODEL extends BaseViewModel,DATA> extends Fragment implements Observer {
    protected BINDING mDataBinding;
    protected VIEWMODEL mViewModel;


    public abstract @LayoutRes
    int getLayoutID();

    public abstract VIEWMODEL createViewModel();

    public abstract void initViews();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutID(), container, false);
        return mDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = createViewModel();
        this.getLifecycle().addObserver(mViewModel);
        mViewModel.mViewStatus.observe(this, this);
        mViewModel.mDataList.observe(this, this);
        initViews();
    }

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
