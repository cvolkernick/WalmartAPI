package com.example.admin.walmartapi.view.base;

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();
}
