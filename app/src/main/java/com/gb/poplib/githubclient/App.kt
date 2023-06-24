package com.gb.poplib.githubclient

import android.app.Application
import com.gb.poplib.githubclient.lesson4.navigation.AndroidScreens2
import com.gb.poplib.githubclient.navigation.AndroidScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    val androidScreens = AndroidScreens()
    val androidScreens2 = AndroidScreens2()

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }


    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}