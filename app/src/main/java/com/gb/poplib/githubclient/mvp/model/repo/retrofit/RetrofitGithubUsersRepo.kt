package com.gb.poplib.githubclient.mvp.model.repo.retrofit

import com.gb.poplib.githubclient.mvp.model.api.IDataSource
import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.room.Database
import com.gb.poplib.githubclient.mvp.model.entity.room.RoomGithubUser
import com.gb.poplib.githubclient.mvp.model.network.INetworkStatus
import com.gb.poplib.githubclient.mvp.model.repo.IGithubUsersRepo
import com.gb.poplib.githubclient.mvp.model.repo.cache.IUserCache
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo (
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: IUserCache
) : IGithubUsersRepo {

    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline->
        if (isOnline) {
            api.getUsers().flatMap { users ->
                Single.fromCallable {
                    cache.insertUsersToCache(users)
                    users
                }
            }
        } else {
            Single.fromCallable {
                cache.getUsersCache()
            }
        }
    }.subscribeOn(Schedulers.io())
}