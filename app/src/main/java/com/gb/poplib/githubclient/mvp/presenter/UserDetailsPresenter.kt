package com.gb.poplib.githubclient.mvp.presenter

import com.gb.poplib.githubclient.mvp.model.entity.GithubUsersRepo
import com.gb.poplib.githubclient.mvp.view.UserDetailsView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserDetailsPresenter(
    val usersRepo: GithubUsersRepo,
    val router: Router
) : MvpPresenter<UserDetailsView>(){
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}