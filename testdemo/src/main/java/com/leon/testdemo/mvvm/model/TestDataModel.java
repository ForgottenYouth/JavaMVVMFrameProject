/**
 * FileName: TestDataModel
 * Author: shiwenliang
 * Date: 2021/10/25 13:36
 * Description:
 */
package com.leon.testdemo.mvvm.model;

import com.leon.base.mvvm.model.BaseDataModel;

public class TestDataModel extends BaseDataModel<String ,String> {
    /**
     * TODO 参数说明：
     * 1. isNeedPage:  标记是否需要分页
     * 2. cachedKey: 如果不为空，那么就使用缓存，并将该key作为缓存的key,否则则不使用缓存
     * 3. initPageIndex:可变参数，只有需要分页的时候，需要传入默认的起始页
     *
     * @param isNeedPage
     * @param cachedKey
     * @param initPageIndex
     */
    public TestDataModel(boolean isNeedPage, String cachedKey, int... initPageIndex) {
        super(isNeedPage, cachedKey, initPageIndex);
    }

    @Override
    protected void loadData() {
        //这里执行网络请求
    }

    @Override
    public void onSuccess(String result, boolean isFromCache) {
    }

    @Override
    public void failure(Throwable e) {

    }
}
