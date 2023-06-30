package com.gb.poplib.githubclient.mvp.model.repo.cache

import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IUserCache {
    //    fun insertUsersToCache(users: List<GithubUser>) : Completable
    fun insertUsersToCache(users: List<GithubUser>)

    //    fun getUsersCache() : Single<List<GithubUser>>
    fun getUsersCache(): List<GithubUser>
}