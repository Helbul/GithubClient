package com.gb.poplib.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.gb.poplib.githubclient.databinding.ItemUserBinding
import com.gb.poplib.githubclient.mvp.presenter.list.IUserListPresenter
import com.gb.poplib.githubclient.mvp.view.IImageLoader
import com.gb.poplib.githubclient.mvp.view.list.UserItemView
import javax.inject.Inject

const val INVALID_INDEX = -1

class UsersRVAdapter(
    val presenter: IUserListPresenter,
    //val imageLoader : IImageLoader<ImageView>
) : RecyclerView.Adapter<UsersRVAdapter.ViewHolder>(){

    @Inject
    lateinit var imageLoader : IImageLoader<ImageView>

    inner class ViewHolder(val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {
        override var pos = INVALID_INDEX

        override fun setLogin(text: String) = with(vb){
            tvLogin.text = text
        }

        override fun loadAvatar(url: String) {
            imageLoader.loadInto(url, vb.ivAvatar)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply {
            pos = position
        })

    override fun getItemCount(): Int = presenter.getCount()
}