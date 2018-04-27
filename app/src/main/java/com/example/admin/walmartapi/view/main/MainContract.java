package com.example.admin.walmartapi.view.main;

import com.example.admin.walmartapi.view.base.BasePresenter;
import com.example.admin.walmartapi.view.base.BaseView;

import org.json.JSONArray;

public interface MainContract {

    interface View extends BaseView {

        void onGetItems(JSONArray items);
    }

    interface  Presenter extends BasePresenter<View> {

        void getItems();
    }
}
