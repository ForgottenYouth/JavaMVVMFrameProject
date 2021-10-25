package com.leon.testdemo;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.leon.base.mvvm.view.BaseActivity;
import com.leon.common.aroute.ARoutePath;
import com.leon.testdemo.databinding.ActivityTestMainBinding;
import com.leon.testdemo.mvvm.viewmodel.TestMainViewModel;


@Route(path = ARoutePath.TEST)
public class TestMainActivity extends BaseActivity<ActivityTestMainBinding, TestMainViewModel> {

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
//        ArrayList<AddressDataModel> dataList = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            AddressDataModel addressDataModel = new AddressDataModel();
//            addressDataModel.province += i;
//            dataList.add(addressDataModel);
//        }
//
//        RecyclerView recyclerView = this.findViewById(R.id.recycleview);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.addItemDecoration(new CustomRecycleViewDecoration(10));
//        MainAdaper adaper = new MainAdaper(this);
//        adaper.setItemClickCallBack((position, item) ->
//                Toast.makeText(MainActivity.this, "当前点击的位置：" + position + "---" + item.province, Toast.LENGTH_SHORT).show());
//        adaper.setItemChildClickCallBack((position, item, view) ->
//                Toast.makeText(MainActivity.this, "当前点击的View：" + view.getTag() + "---" + item.province, Toast.LENGTH_SHORT).show());
//        adaper.setDataList(dataList);
//        recyclerView.setAdapter(adaper);
    }
}