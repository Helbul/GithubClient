package com.gb.poplib.githubclient.mvp.model.entity

import io.reactivex.rxjava3.core.Observable

class GithubUsersRepo {
    private var users = listOf (
        GithubUser("", "login1"),
        GithubUser("","login2"),
        GithubUser("", "login3"),
        GithubUser("","login4"),
        GithubUser("","login5")
    )

    fun getUsers() : List<GithubUser> {
        return users
    }

}