/**
 * FileName: BaseCustomViewModel
 * Author: shiwenliang
 * Date: 2021/10/21 13:46
 * Description:
 */
package com.leon.base.customview;

import com.leon.base.mvvm.model.BaseDataModel;

public abstract class BaseCustomViewModel<DATAMODEL extends BaseDataModel> {

    protected abstract BaseCustomViewModel covertDataModelToViewModel(DATAMODEL datamodel);

}
