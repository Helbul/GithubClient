package com.gb.poplib.githubclient.mvp.presenter.list

import com.gb.poplib.githubclient.mvp.view.list.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}