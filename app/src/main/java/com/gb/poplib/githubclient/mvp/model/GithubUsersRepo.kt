package com.gb.poplib.githubclient.mvp.model

import io.reactivex.rxjava3.core.Observable

class GithubUsersRepo {
    private val users = listOf (
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    fun getUsers() : List<GithubUser> {
        return users
    }

    fun fromIterable(): Observable<GithubUser> {
        return Observable.fromIterable(users)
    }

}