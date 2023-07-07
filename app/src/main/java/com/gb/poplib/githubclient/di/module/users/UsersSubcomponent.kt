package com.gb.poplib.githubclient.di.module.users

import com.gb.poplib.githubclient.di.module.repos.ReposSubcomponent
import com.gb.poplib.githubclient.di.module.users.module.UsersModule
import com.gb.poplib.githubclient.mvp.presenter.UsersPresenter
import com.gb.poplib.githubclient.ui.adapter.UsersRVAdapter
import dagger.Subcomponent

@UsersScope
@Subcomponent(
    modules = [
        UsersModule::class
    ]
)
interface UsersSubcomponent {
    fun reposSubcomponent(): ReposSubcomponent

    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)
}