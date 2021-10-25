package com.leon.javamvvmframeproject;

import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leon.base.mvvm.view.BaseActivity;
import com.leon.base.recycleview.CustomRecycleViewDecoration;
import com.leon.javamvvmframeproject.databinding.ActivityMainBinding;
import com.leon.javamvvmframeproject.mvvm.model.AddressDataModel;
import com.leon.javamvvmframeproject.mvvm.viewmodel.MainActivityViewModel;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainActivityViewModel> {

    @Override
    public void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {

    }
}