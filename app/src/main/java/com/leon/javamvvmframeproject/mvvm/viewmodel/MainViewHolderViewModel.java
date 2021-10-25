/**
 * FileName: TitleViewModel
 * Author: shiwenliang
 * Date: 2021/10/21 13:52
 * Description:
 */
package com.leon.javamvvmframeproject.mvvm.viewmodel;

import com.leon.base.customview.BaseCustomViewModel;
import com.leon.javamvvmframeproject.mvvm.model.AddressDataModel;
import com.leon.base.mvvm.model.BaseDataModel;

public class MainViewHolderViewModel extends BaseCustomViewModel {
    public String province;
    public String city;
    public String detail;

    public MainViewHolderViewModel(BaseDataModel dataModel)
    {
        covertDataModelToViewModel(dataModel);
    }

    @Override
    protected BaseCustomViewModel covertDataModelToViewModel(BaseDataModel dataModel) {
        if (dataModel instanceof AddressDataModel) {
            this.province = ((AddressDataModel) dataModel).province;
            this.city = ((AddressDataModel) dataModel).city;
            this.detail = ((AddressDataModel) dataModel).detail;
        }
        return this;
    }
}
