package com.gb.poplib.githubclient.lesson4.ui

import android.os.Bundle
import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.R
import com.gb.poplib.githubclient.databinding.ActivityConverterBinding
import com.gb.poplib.githubclient.lesson4.presenter.ConverterActivityPresenter
import com.gb.poplib.githubclient.lesson4.view.ConverterActivityView
import com.gb.poplib.githubclient.ui.activity.BackButtonListener
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class ConverterActivity : MvpAppCompatActivity(), ConverterActivityView {
    private var binding: ActivityConverterBinding? = null

    private val navigator = AppNavigator(this, R.id.container2)

    private val presenter by moxyPresenter {
        ConverterActivityPresenter(App.instance.router, App.instance.androidScreens2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityConverterBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()

        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()

        App.instance.navigatorHolder.removeNavigator()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }

        presenter.backClicked()
    }

}