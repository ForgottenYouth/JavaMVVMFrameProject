/**
 * FileName: IBaseViewModel
 * Author: shiwenliang
 * Date: 2021/10/21 16:28
 * Description:
 */
package com.leon.base.mvvm.viewmodel;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.leon.base.customexception.CustomException;
import com.leon.base.mvvm.model.BaseDataModel;
import com.leon.base.mvvm.model.IBaseDataModelListener;
import com.leon.base.mvvm.model.PageResult;

import java.util.List;

public abstract class BaseViewModel<DATAMODEL extends BaseDataModel, DATA> extends ViewModel implements LifecycleObserver, IBaseDataModelListener<List<DATA>> {
    /**
     * TODO 1.每一次网络请求都可以看成返回是一个List ,对于不需要分页的，那么List的size ==1
     */
    public MutableLiveData<List<DATA>> mDataList = new MutableLiveData<>();
    protected DATAMODEL mDataModel;
    public MutableLiveData<ViewStatus> mViewStatus = new MutableLiveData<>();
    public MutableLiveData<String> mErrorMsg = new MutableLiveData<>();//记录错误信息

    public BaseViewModel() {

    }

    public abstract DATAMODEL createDataModel();

    private void createDataModelAndRegist() throws CustomException {
        if (mDataModel == null) {
            mDataModel = createDataModel();
            if (mDataModel != null) {
                mDataModel.registerListener(this);
            } else {
                throw new CustomException(this.getClass().toString() + "can not create DataModel");
            }
        } else {
            mDataModel.registerListener(this);
        }
    }

    public void getCachedAndLoad() throws CustomException {
        mViewStatus.postValue(ViewStatus.LOADING);
        createDataModelAndRegist();
        if (mDataModel != null) {
            mDataModel.getCachedAndLoad();
        }
    }

    public void refresh() throws CustomException {
        mViewStatus.postValue(ViewStatus.LOADING);
        createDataModelAndRegist();
        if (mDataModel != null) {
            mDataModel.refresh();
        }
    }

    public void loadNextPage() throws CustomException {
        createDataModelAndRegist();
        if (mDataModel != null) {
            mDataModel.loadNextPage();
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    @Override
    public void loadSuccess(BaseDataModel dataModel, List<DATA> data, PageResult... pageResults) {
        if (dataModel.isNeedPage()) {
            if (pageResults[0].mIsEmpty) {
                if (pageResults[0].mIsFirstPage) {
                    mViewStatus.postValue(ViewStatus.EMPTY);
                } else {
                    mViewStatus.postValue(ViewStatus.NOMOREDATA);
                }
            } else {
                if (pageResults[0].mIsFirstPage) {
                    mDataList.postValue(data);
                } else {
                    mDataList.getValue().addAll(data);
                    mDataList.postValue(mDataList.getValue());
                }
            }
        }
    }

    @Override
    public void loadFail(BaseDataModel dataModel, String errorMessage, PageResult... pageResults) {
        mErrorMsg.postValue(errorMessage);
        if (dataModel.isNeedPage()) {
            if (pageResults[0].mIsFirstPage) {
                mViewStatus.postValue(ViewStatus.REFRESH_ERROR);
            } else {
                mViewStatus.postValue(ViewStatus.LOADMOREFAILED);
            }
        }
    }


    @OnLifecycleEvent(value = Lifecycle.Event.ON_RESUME)
    private void lifeCycleLoadData() throws CustomException {
        if (mDataList == null || mDataList.getValue() == null || mDataList.getValue().size() == 0) {
            createDataModelAndRegist();
            mDataModel.getCachedAndLoad();
        } else {
            mDataList.postValue(mDataList.getValue());
            mViewStatus.postValue(mViewStatus.getValue());
        }
    }
}
