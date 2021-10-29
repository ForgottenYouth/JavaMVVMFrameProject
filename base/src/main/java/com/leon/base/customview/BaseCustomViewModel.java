/**
 * FileName: BaseCustomViewModel
 * Author: shiwenliang
 * Date: 2021/10/21 13:46
 * Description:
 */
package com.leon.base.customview;

public abstract class BaseCustomViewModel<DATAMODEL> {

    protected abstract BaseCustomViewModel covertDataModelToViewModel(DATAMODEL datamodel);

}
