/**
 * FileName: TestMainViewModel
 * Author: shiwenliang
 * Date: 2021/10/22 15:16
 * Description:
 */
package com.leon.testdemo.mvvm.viewmodel;

import com.leon.base.mvvm.viewmodel.BaseViewModel;
import com.leon.testdemo.mvvm.model.TestDataModel;

public class TestMainViewModel extends BaseViewModel<TestDataModel,String> {
    @Override
    public TestDataModel createDataModel() {
        return new TestDataModel(false,"TestActivityViewModel");
    }
}
