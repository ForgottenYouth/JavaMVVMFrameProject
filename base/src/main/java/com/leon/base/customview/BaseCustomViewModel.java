/**
 * FileName: BaseCustomViewModel
 * Author: shiwenliang
 * Date: 2021/10/21 13:46
 * Description:
 */
package com.leon.base.customview;

public abstract class BaseCustomViewModel<DATAMODEL> {

    /**
     * TODO 这里需要将返回的数据类型转化为自定义view自己需要的viewmodel
     * 1.创建一个自定义view的viewModel
     * 2.将传递进来的数据bean里面对应的值赋值给viewModel
     * 3.这个自定义的viewmodel返回即可
     */
    protected abstract BaseCustomViewModel covertDataModelToViewModel(DATAMODEL datamodel);

}
