package com.leon.base.mvvm.viewmodel;

public enum ViewStatus {
    LOADING,//加载中
    EMPTY,//空页面
    SHOWCONTENT,//显示内容
    NOMOREDATA,//没有更多数据
    REFRESH_ERROR,//刷新错误
    LOADMOREFAILED,//加载更多失败
}
