package com.gb.poplib.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.databinding.FragmentUsersBinding
import com.gb.poplib.githubclient.di.module.users.UsersSubcomponent
import com.gb.poplib.githubclient.mvp.presenter.UsersPresenter
import com.gb.poplib.githubclient.mvp.view.UsersView
import com.gb.poplib.githubclient.ui.activity.BackButtonListener
import com.gb.poplib.githubclient.ui.adapter.UsersRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    private var _binding: FragmentUsersBinding? = null
    private val binding
        get() = _binding!!

    var usersSubcomponent: UsersSubcomponent? = null

    var adapter: UsersRVAdapter? = null

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(AndroidSchedulers.mainThread()).apply {
            usersSubcomponent = App.instance.initUsersSubcomponent()
            usersSubcomponent?.inject(this)
        }
    }

    companion object {
        fun newInstance() = UsersFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter).apply {
            usersSubcomponent?.inject(this)
        }
        binding.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}