package com.gb.poplib.githubclient.lesson4.presenter

import com.gb.poplib.githubclient.lesson4.navigation.AndroidScreens2
import com.gb.poplib.githubclient.lesson4.view.ConverterFragmentView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class ConverterFragmentPresenter(
    val router: Router,
    val screens: AndroidScreens2
) : MvpPresenter<ConverterFragmentView>() {

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun convert() {
        println("processing convert...")
    }
}