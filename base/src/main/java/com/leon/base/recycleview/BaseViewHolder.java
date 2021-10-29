/**
 * FileName: BaseViewHolder
 * Author: shiwenliang
 * Date: 2021/10/21 13:58
 * Description: 列表viewHolder的基类
 */
package com.leon.base.recycleview;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leon.base.customview.BaseCustomViewModel;
import com.leon.base.customview.IBaseCustomView;

public class BaseViewHolder extends RecyclerView.ViewHolder  {
    protected IBaseCustomView itemView;

    public BaseViewHolder(@NonNull IBaseCustomView itemView) {
        super((View) itemView);
        this.itemView = itemView;
    }

    public void bind(BaseCustomViewModel viewModel) {
        this.itemView.setData(viewModel);
    }
}
