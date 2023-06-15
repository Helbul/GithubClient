package com.gb.poplib.githubclient.mvp.presenter

import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.mvp.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(val router: Router) : MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(App.androidScreens.users())
    }

    fun backClicked() {
        router.exit()
    }
}