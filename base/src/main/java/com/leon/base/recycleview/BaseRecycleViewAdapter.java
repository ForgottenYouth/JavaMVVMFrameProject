/**
 * FileName: BaseRecycleViewAdapter
 * Author: shiwenliang
 * Date: 2021/10/21 14:02
 * Description:
 */
package com.leon.base.recycleview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.leon.base.customview.BaseCustomView;
import com.leon.base.customview.BaseCustomViewModel;
import com.leon.base.customview.IBaseCustomView;
import com.leon.base.mvvm.model.BaseDataModel;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecycleViewAdapter<BINDING extends ViewDataBinding, T extends BaseDataModel>
        extends RecyclerView.Adapter<BaseViewHolder> {
    protected Context mContext;

    public void setDataList(List<T> dataList) {
        this.mDataList = dataList;
    }


    public void setItemClickCallBack(IRecycleViewItemClickListener<T> itemClickCallBack) {
        this.mItemClickCallBack = itemClickCallBack;
    }

    //item点击事件
    protected IRecycleViewItemClickListener<T> mItemClickCallBack;

    public void setItemLongClickCallBack(IRecycleViewItemLongClickListener<T> itemLongClickCallBack) {
        this.mItemLongClickCallBack = itemLongClickCallBack;
    }

    //item长按回调
    protected IRecycleViewItemLongClickListener<T> mItemLongClickCallBack;

    public void setItemChildClickCallBack(IRecycleChildViewClickListener<T> itemChildClickCallBack) {
        this.mItemChildClickCallBack = itemChildClickCallBack;
    }

    //item的子视图点击回调
    protected IRecycleChildViewClickListener<T> mItemChildClickCallBack;

    private List<T> mDataList = new ArrayList<>();

    public BaseRecycleViewAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder(getCustomViewHoder());
    }

    public abstract IBaseCustomView getCustomViewHoder();

    public abstract BaseCustomViewModel getViewHolderViewModel(BaseCustomView holder, int position, T dataModel);

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
        BaseCustomViewModel viewHolderViewModel = getViewHolderViewModel((BaseCustomView) holder.itemView, position, mDataList.get(position));
        holder.itemView.setData(viewHolderViewModel);
        ((BaseCustomView) holder.itemView).mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickCallBack != null) {
                    mItemClickCallBack.onRecycleViewItemClick(position, mDataList.get(position));
                }
            }
        });
        ((BaseCustomView) holder.itemView).mBinding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mItemLongClickCallBack != null) {
                    mItemLongClickCallBack.onRecycleViewItemLongClick(position, mDataList.get(position));
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mDataList == null) {
            return 0;
        } else {
            return mDataList.size();
        }
    }
}
