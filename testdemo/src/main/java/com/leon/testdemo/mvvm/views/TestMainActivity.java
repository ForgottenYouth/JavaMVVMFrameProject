package com.leon.testdemo.mvvm.views;

import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.leon.base.mvvm.view.BaseActivity;
import com.leon.base.recycleview.CustomRecycleViewDecoration;
import com.leon.base.recycleview.IRecycleViewItemClickListener;
import com.leon.base.recycleview.IRecycleViewItemLongClickListener;
import com.leon.common.aroute.ARoutePath;
import com.leon.testdemo.R;
import com.leon.testdemo.databinding.ActivityTestMainBinding;
import com.leon.testdemo.mvvm.viewmodel.TestMainViewModel;

import java.util.ArrayList;
import java.util.List;


@Route(path = ARoutePath.TEST)
public class TestMainActivity extends BaseActivity<ActivityTestMainBinding, TestMainViewModel, String> {

    @Override
    public TestMainViewModel createViewModel() {
        return new TestMainViewModel();
    }

    @Override
    public void bindViewModel() {
        mDataBinding.setViewmodel(mViewModel);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test_main;
    }

    @Override
    public void initViews() {
        ArrayList<String> dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataList.add("item" + i);
        }
        RecyclerView recyclerView = this.findViewById(R.id.recycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new CustomRecycleViewDecoration(10));
        TestMainAdapter adaper = new TestMainAdapter(this);
//        adaper.setItemClickCallBack(new IRecycleViewItemClickListener<String>() {
//            @Override
//            public void onRecycleViewItemClick(int position, String item) {
//                Toast.makeText(TestMainActivity.this, "当前点击的位置：" + position + "---" + item, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        adaper.setItemLongClickCallBack(new IRecycleViewItemLongClickListener<String>() {
//            @Override
//            public void onRecycleViewItemLongClick(int postion, String item) {
//                Toast.makeText(TestMainActivity.this, "当前点击的View：" + "---" + item, Toast.LENGTH_SHORT).show();
//            }
//        });

        adaper.setDataList(dataList);
        recyclerView.setAdapter(adaper);
    }

    @Override
    public void handleResponseData(List<String> strings) {

    }
}