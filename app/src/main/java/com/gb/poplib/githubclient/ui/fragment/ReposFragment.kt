package com.gb.poplib.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.databinding.FragmentReposBinding
import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.presenter.UserReposPresenter
import com.gb.poplib.githubclient.mvp.view.ReposView
import com.gb.poplib.githubclient.ui.activity.BackButtonListener
import com.gb.poplib.githubclient.ui.adapter.ReposRVAdapter
import com.gb.poplib.githubclient.ui.image.GlideImageLoader
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ReposFragment : MvpAppCompatFragment(), ReposView, BackButtonListener {

    companion object {
        const val KEY_REPOS = "KEY_REPOS"

        fun newInstance(user: GithubUser) = ReposFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_REPOS, user)
            }
        }
    }

    private var _binding: FragmentReposBinding? = null
    private val binding
        get() = _binding!!


    private var adapter: ReposRVAdapter? = null

    val presenter: UserReposPresenter by moxyPresenter {
        val user = arguments?.getParcelable(KEY_REPOS) as GithubUser?
        UserReposPresenter(
            user
        ).apply {
            App.instance.initReposSubcomponent()?.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentReposBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init(user: GithubUser) {
        binding.rvRepos?.layoutManager = LinearLayoutManager(context)
        adapter = ReposRVAdapter(presenter.userReposListPresenter)
        binding.rvRepos.adapter = adapter
        binding.tvUserLogin.text = user.login
        binding.tvUserName.text = user.name
        binding.tvUserFollowers.text = user.followers.toString()
        user.avatarUrl?.let {
            GlideImageLoader().loadInto(it, binding.ivUserAvatar)
        }
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}