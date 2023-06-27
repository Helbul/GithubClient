package com.gb.poplib.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gb.poplib.githubclient.databinding.ItemReposBinding
import com.gb.poplib.githubclient.mvp.presenter.list.IUserReposListPresenter
import com.gb.poplib.githubclient.mvp.view.list.IReposItemView

const val INVALID_INDEX_REPO = -1

class ReposRVAdapter(
    val presenter: IUserReposListPresenter
) : RecyclerView.Adapter<ReposRVAdapter.ViewHolder>(){

    inner class ViewHolder(val vb: ItemReposBinding) : RecyclerView.ViewHolder(vb.root),
            IReposItemView {

        override fun setNameRepo(name: String) {
            vb.reposName.text = name
        }

        override var pos = INVALID_INDEX_REPO

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemReposBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply {
            pos = position
        })

    }

    override fun getItemCount(): Int = presenter.getCount()
}