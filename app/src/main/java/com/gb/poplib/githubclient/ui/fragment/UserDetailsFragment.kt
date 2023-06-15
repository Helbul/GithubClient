package com.gb.poplib.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.databinding.FragmentUserDetailsBinding
import com.gb.poplib.githubclient.mvp.model.GithubUsersRepo
import com.gb.poplib.githubclient.mvp.presenter.UserDetailsPresenter
import com.gb.poplib.githubclient.mvp.view.UserDetailsView
import com.gb.poplib.githubclient.ui.activity.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDetailsFragment : MvpAppCompatFragment(), UserDetailsView, BackButtonListener {
    private var _binding: FragmentUserDetailsBinding? = null
    private val binding
        get() = _binding!!

    val presenter: UserDetailsPresenter by moxyPresenter {
        UserDetailsPresenter(GithubUsersRepo(), App.instance.router)
    }

    companion object {
        const val KEY_ID = "KEY_ID"

        fun newInstance(id: Int): UserDetailsFragment{
            val args = Bundle()
            args.putInt(KEY_ID, id)
            val fragment = UserDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserDetailsBinding.inflate(inflater, container, false).also {
        _binding = it
        binding.userTextView.text = presenter.usersRepo
            .getUsers()[arguments?.getInt(KEY_ID)?:0].login
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed() = presenter.backPressed()
}