package com.gb.poplib.githubclient

import android.app.Application
import com.gb.poplib.githubclient.di.AppComponent
import com.gb.poplib.githubclient.di.DaggerAppComponent
import com.gb.poplib.githubclient.di.module.AppModule
import com.gb.poplib.githubclient.di.module.repos.ReposSubcomponent
import com.gb.poplib.githubclient.di.module.repos.module.IReposScopeContainer
import com.gb.poplib.githubclient.di.module.users.UsersSubcomponent
import com.gb.poplib.githubclient.di.module.users.module.IUsersScopeContainer

class App : Application(), IUsersScopeContainer, IReposScopeContainer {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    var reposSubcomponent: ReposSubcomponent? = null
        private set

    var usersSubcomponent: UsersSubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initUsersSubcomponent() = appComponent.usersSubcomponent().also {
        usersSubcomponent = it
    }

    fun initReposSubcomponent() = usersSubcomponent?.reposSubcomponent().also {
        reposSubcomponent = it
    }

    override fun releaseReposScope() {
        reposSubcomponent = null
    }

    override fun releaseUsersScope() {
        usersSubcomponent = null
    }
}