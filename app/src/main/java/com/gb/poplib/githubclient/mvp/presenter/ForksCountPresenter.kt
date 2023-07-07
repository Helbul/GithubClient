package com.gb.poplib.githubclient.mvp.presenter

import com.gb.poplib.githubclient.mvp.model.entity.GithubUserRepos
import com.gb.poplib.githubclient.mvp.view.ForksCountView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class ForksCountPresenter(val repos: GithubUserRepos) : MvpPresenter<ForksCountView>(){

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.countForks(repos.forksCount.toString())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}