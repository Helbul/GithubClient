package com.gb.poplib.githubclient.di

import com.gb.poplib.githubclient.di.module.*
import com.gb.poplib.githubclient.di.module.users.UsersSubcomponent
import com.gb.poplib.githubclient.mvp.presenter.ForksCountPresenter
import com.gb.poplib.githubclient.mvp.presenter.MainPresenter
import com.gb.poplib.githubclient.mvp.presenter.UserReposPresenter
import com.gb.poplib.githubclient.mvp.presenter.UsersPresenter
import com.gb.poplib.githubclient.ui.activity.MainActivity
import com.gb.poplib.githubclient.ui.adapter.UsersRVAdapter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        CiceroneModule::class,
        ImageLoaderModule::class,
        //RepoModule::class
    ]
)
interface AppComponent {
    fun usersSubcomponent() : UsersSubcomponent

    fun inject(mainActivity: MainActivity)

    fun inject(mainPresenter: MainPresenter)
//    fun inject(usersPresenter: UsersPresenter)
//    fun inject(userReposPresenter: UserReposPresenter)
    fun inject(forksCountPresenter: ForksCountPresenter)
//
//    fun inject(usersRVAdapter: UsersRVAdapter)
}