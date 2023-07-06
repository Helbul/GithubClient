package com.gb.poplib.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.databinding.FragmentForksCountBinding
import com.gb.poplib.githubclient.mvp.model.entity.GithubUserRepos
import com.gb.poplib.githubclient.mvp.presenter.ForksCountPresenter
import com.gb.poplib.githubclient.mvp.view.ForksCountView
import com.gb.poplib.githubclient.ui.activity.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ForksCountFragment() : MvpAppCompatFragment(), ForksCountView, BackButtonListener {

    companion object {
        private const val KEY_FORKS_COUNT = "KEY_FORKS_COUNT"

        fun newInstance(repos: GithubUserRepos): ForksCountFragment {
            val args = Bundle()
            args.putParcelable(KEY_FORKS_COUNT, repos)
            val fragment = ForksCountFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentForksCountBinding? = null

    private val binding
        get() = _binding!!

    val presenter: ForksCountPresenter by moxyPresenter {
        val repos = arguments?.getParcelable<GithubUserRepos>(KEY_FORKS_COUNT) as GithubUserRepos
        ForksCountPresenter(repos).apply {
            App.instance.reposSubcomponent?.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentForksCountBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        arguments?.getParcelable<GithubUserRepos>(KEY_FORKS_COUNT)?.let {
//            presenter.showForksCount(it)
//        }
//    }

    override fun countForks(forks: String) {
        binding.tvForksCount.text = forks
    }

    override fun backPressed() = presenter.backPressed()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}