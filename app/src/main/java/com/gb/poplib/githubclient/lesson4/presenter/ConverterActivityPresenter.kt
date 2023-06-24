package com.gb.poplib.githubclient.lesson4.presenter

import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.lesson4.navigation.AndroidScreens2
import com.gb.poplib.githubclient.lesson4.view.ConverterActivityView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class ConverterActivityPresenter(val router: Router, val screens: AndroidScreens2) : MvpPresenter<ConverterActivityView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        //router.replaceScreen(screens.converter())
        router.replaceScreen(App.instance.androidScreens2.converter())
    }

    fun backClicked() {
        router.exit()
    }
}