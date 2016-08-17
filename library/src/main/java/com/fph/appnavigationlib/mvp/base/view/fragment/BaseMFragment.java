package com.fph.appnavigationlib.mvp.base.view.fragment;

import com.fph.appnavigationlib.base.BaseFragment;
import com.fph.appnavigationlib.mvp.base.presenter.BasePresenter;
import com.fph.appnavigationlib.mvp.base.view.BaseView;

/**
 * 项 目 名 Appnavigationlib.
 * 描    述 BaseMFragment
 * 创 建 人 WoKee.
 * 创建时间 2016 08 16.
 */
public abstract  class BaseMFragment<T extends BasePresenter> extends BaseFragment implements BaseView<T> {

    T mPresenter;

    @Override
    public void setPresenter(T presenter) {
        this.mPresenter=presenter;
    }
}
