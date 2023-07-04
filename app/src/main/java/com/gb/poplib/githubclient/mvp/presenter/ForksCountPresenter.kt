package com.gb.poplib.githubclient.mvp.presenter

import com.gb.poplib.githubclient.mvp.model.entity.GithubUserRepos
import com.gb.poplib.githubclient.mvp.view.ForksCountView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class ForksCountPresenter() : MvpPresenter<ForksCountView>(){

    @Inject
    lateinit var router: Router

    fun  showForksCount(repos: GithubUserRepos) {
        viewState.countForks(repos.forksCount.toString())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}