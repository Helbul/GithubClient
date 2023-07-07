package com.gb.poplib.githubclient.di.module.repos

import com.gb.poplib.githubclient.di.module.repos.module.ReposModule
import com.gb.poplib.githubclient.mvp.presenter.ForksCountPresenter
import com.gb.poplib.githubclient.mvp.presenter.UserReposPresenter
import com.gb.poplib.githubclient.mvp.presenter.UsersPresenter
import dagger.Subcomponent

@ReposScope
@Subcomponent(
    modules = [
        ReposModule::class
    ]
)

interface ReposSubcomponent {
    fun inject(userReposPresenter: UserReposPresenter)
    fun inject(forksCountPresenter: ForksCountPresenter)
}