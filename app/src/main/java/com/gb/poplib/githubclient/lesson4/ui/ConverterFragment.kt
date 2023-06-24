package com.gb.poplib.githubclient.lesson4.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.databinding.FragmentConverterBinding
import com.gb.poplib.githubclient.lesson4.presenter.ConverterFragmentPresenter
import com.gb.poplib.githubclient.lesson4.view.ConverterFragmentView
import com.gb.poplib.githubclient.ui.activity.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ConverterFragment : MvpAppCompatFragment(), ConverterFragmentView, BackButtonListener {
    private var _binding: FragmentConverterBinding? = null
    private val binding
        get() = _binding!!

    val presenter: ConverterFragmentPresenter by moxyPresenter {
        ConverterFragmentPresenter(App.instance.router, App.instance.androidScreens2)
    }

    companion object {
        fun newInstance(): ConverterFragment = ConverterFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConverterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed() = presenter.backPressed()

    override fun setButtonConvert() {
        presenter.convert()
    }
}