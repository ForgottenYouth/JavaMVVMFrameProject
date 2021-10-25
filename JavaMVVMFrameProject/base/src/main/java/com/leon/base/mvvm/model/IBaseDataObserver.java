/**
 * FileName: IBaseDataObserver
 * Author: shiwenliang
 * Date: 2021/10/22 13:59
 * Description:
 */
package com.leon.base.mvvm.model;

public interface IBaseDataObserver<DATA> {
    void onSuccess(DATA result, boolean isFromCache);

    void failure(Throwable e);
}
