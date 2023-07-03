package com.gb.poplib.githubclient.di

import com.gb.poplib.githubclient.di.module.*
import com.gb.poplib.githubclient.mvp.presenter.ForksCountPresenter
import com.gb.poplib.githubclient.mvp.presenter.MainPresenter
import com.gb.poplib.githubclient.mvp.presenter.UsersPresenter
import com.gb.poplib.githubclient.ui.activity.MainActivity
import com.gb.poplib.githubclient.ui.fragment.ForksCountFragment
import com.gb.poplib.githubclient.ui.fragment.ReposFragment
import com.gb.poplib.githubclient.ui.fragment.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        CiceroneModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(forksCountFragment: ForksCountPresenter)

    // убрать эти инъекции
    fun inject(usersFragment: UsersFragment)
    fun inject(forksCountFragment: ForksCountFragment)
    fun inject(reposFragment: ReposFragment)
}