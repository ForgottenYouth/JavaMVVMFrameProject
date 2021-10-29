/**
 * FileName: IBaseDataModel
 * Author: shiwenliang
 * Date: 2021/10/21 14:05
 * Description: 序列化数据模型
 */
package com.leon.base.mvvm.model;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.leon.base.customexception.CustomException;
import com.leon.base.utils.GenericUtils;
import com.tencent.mmkv.MMKV;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * TODO 说明这里建议每一个接口定义一个datamodel ,在viewModel中可以有多个dataModel
 */
public abstract class BaseDataModel<ORG_DATA, RESULT_DATA> implements IBaseDataObserver<ORG_DATA> {

    private CompositeDisposable compositeDisposable;

    protected WeakReference<IBaseDataModelListener> mReferenceBaseDataModelListener;//保存监听者

    private int mPageIndex = 0;
    private int mPageSize = 10;//默认分页一次性加载10条数据
    private boolean mIsNeedPage;//记录是否需要分页
    private final int INIT_PAGE_DEFAULT_NUMBER;

    private boolean mIsLoading;//标记是否在加载中

    private String mCachedMMKVKey;//保存在MMKV中的缓存数据的key

    public boolean isNeedPage() {
        return mIsNeedPage;
    }

    /**
     * TODO 参数说明：
     * 1. isNeedPage:  标记是否需要分页
     * 2. cachedKey: 如果不为空，那么就使用缓存，并将该key作为缓存的key,否则则不使用缓存
     * 3. initPageIndex:可变参数，只有需要分页的时候，需要传入默认的起始页
     */
    public BaseDataModel(boolean isNeedPage, String cachedKey, int... initPageIndex) {
        mIsNeedPage = isNeedPage;
        if (isNeedPage && initPageIndex != null && initPageIndex.length > 0) {
            INIT_PAGE_DEFAULT_NUMBER = initPageIndex[0];
        } else {
            INIT_PAGE_DEFAULT_NUMBER = -1;
        }

        mCachedMMKVKey = cachedKey;
    }

    //regist listener for data load result
    public void registerListener(IBaseDataModelListener listener) {
        if (null != listener) {
            mReferenceBaseDataModelListener = new WeakReference<>(listener);
        }
    }

    //set default pagesize
    public final void setDefaultPageSize(int defaultPageSize) {
        mPageSize = defaultPageSize;
    }

    public void refresh() throws CustomException {
        if (mReferenceBaseDataModelListener == null || mReferenceBaseDataModelListener.get() == null) {
            throw new CustomException(this.getClass().toString()
                    + "not regist Listener,need to call function:registerListener");
        } else {
            if (!mIsLoading) {
                if (mIsNeedPage) {
                    mPageIndex = INIT_PAGE_DEFAULT_NUMBER;
                }
                mIsLoading = true;
                loadData();
            }
        }
    }

    protected abstract void loadData();

    protected boolean isNeedToUpdate(long cachedTimeSlot) {
        return true;
    }

    public void loadNextPage() throws CustomException {
        if (mReferenceBaseDataModelListener == null || mReferenceBaseDataModelListener.get() == null) {
            throw new CustomException(this.getClass().toString()
                    + "not regist Listener,need to call function:registerListener");
        } else {
            if (!mIsLoading) {
                mIsLoading = true;
                loadData();
            }
        }
    }

    //加载成功后通知监听者
    protected void notifyLoadSuccessResultToListener(ORG_DATA orgData, RESULT_DATA resultData) {
        IBaseDataModelListener listener = mReferenceBaseDataModelListener.get();
        if (listener != null) {
            //通知监听者
            if (mIsNeedPage) {
                PageResult pageResult = new PageResult(mPageIndex == INIT_PAGE_DEFAULT_NUMBER,
                        ((List) resultData).size() == mPageSize,
                        resultData == null ? true : ((List) resultData).isEmpty());
                listener.loadSuccess(this, resultData, pageResult);

                //更新页码
                if (!pageResult.mIsEmpty && pageResult.mHasNextPage) {
                    mPageIndex++;
                }
            } else {
                listener.loadSuccess(this, resultData);
            }

            //缓存数据
            if (!TextUtils.isEmpty(mCachedMMKVKey)) {
                if (mIsNeedPage) {
                    if (mPageIndex == INIT_PAGE_DEFAULT_NUMBER) {
                        savaDataToCache(orgData);
                    }
                } else {
                    savaDataToCache(orgData);
                }
            }
        }
        mIsLoading = false;
    }

    //加载失败后，通知监听者
    protected void notifyLoadFailResultToListener(final String errorMessage) {
        IBaseDataModelListener listener = mReferenceBaseDataModelListener.get();
        if (listener != null) {
            if (mIsNeedPage) {
                PageResult pageResult = new PageResult(mPageIndex == INIT_PAGE_DEFAULT_NUMBER,
                        false,
                        true);
                listener.loadFail(this, errorMessage, pageResult);
            } else {
                listener.loadFail(this, errorMessage);
            }
        }
        mIsLoading = false;
    }

    public void getCachedAndLoad() {
        if (!mIsLoading) {
            mIsLoading = true;
            if (!TextUtils.isEmpty(mCachedMMKVKey)) {
                String cacheDataStr = MMKV.defaultMMKV().getString(mCachedMMKVKey, "");
                if (!TextUtils.isEmpty(cacheDataStr)) {
                    try {
                        ORG_DATA cachedData = new Gson().fromJson(new JSONObject(cacheDataStr).getString("data"),
                                (Class<ORG_DATA>) GenericUtils.getGenericType(this));
                        if (cachedData != null) {
                            onSuccess(cachedData, true);
                        }
                        long lastUpdateTime = Long.parseLong(new JSONObject(cacheDataStr).getString("lastUpdateTime"));
                        if (isNeedToUpdate(lastUpdateTime)) {
                            loadData();
                            return;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            loadData();
        }
    }

    /**
     * 这里只能保存原始数据，结果数据保存后，我们读取出来因为是使用基类的类型存储数据，无法反序列化成真正的类型数据
     *
     * @param orgData
     */
    protected void savaDataToCache(ORG_DATA orgData) {
        if (null != orgData) {
            BaseCachedData<ORG_DATA> cachedData = new BaseCachedData<>();
            cachedData.lastUpdateTime = System.currentTimeMillis();
            cachedData.data = orgData;
            MMKV.defaultMMKV().encode(mCachedMMKVKey, new Gson().toJson(cachedData));
        }
    }

    public void cancel() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void addDisposable(Disposable d) {
        if (d == null) {
            return;
        }

        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }

        compositeDisposable.add(d);
    }
}
