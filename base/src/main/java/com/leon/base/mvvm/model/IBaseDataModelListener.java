/**
 * FileName: IBaseDataModelListener
 * Author: shiwenliang
 * Date: 2021/10/22 10:33
 * Description: 通知ViewModel 数据加载成功或失败
 */
package com.leon.base.mvvm.model;

public interface IBaseDataModelListener<RESULT_DATA> {

    //加载成功
    void loadSuccess(BaseDataModel dataModel, RESULT_DATA resultData, PageResult... pageResults);

    //加载失败
    void loadFail(BaseDataModel dataModel, final String errorMessage, PageResult... pageResults);
}
