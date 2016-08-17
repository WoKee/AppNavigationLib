package com.fph.appnavigationlib.mvp.base.presenter;

import com.fph.appnavigationlib.mvp.base.view.BaseView;

/**
 * 项 目 名 Appnavigationlib.
 * 描    述 BaseTPresenter
 * 创 建 人 WoKee.
 * 创建时间 2016 08 16.
 */
public  class BaseTPresenter<T extends BaseView>{

    T mView;

    public BaseTPresenter(T mView){
        this.mView =mView;
        this.mView.setPresenter(this);
    }

}
