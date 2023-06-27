package com.gb.poplib.githubclient.mvp.presenter

import com.gb.poplib.githubclient.mvp.model.entity.GithubUserRepos
import com.gb.poplib.githubclient.mvp.view.ForksCountView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class ForksCountPresenter(
    val router: Router
) : MvpPresenter<ForksCountView>(){

    fun  showForksCount(repos: GithubUserRepos) {
        viewState.countForks(repos.forksCount.toString())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}