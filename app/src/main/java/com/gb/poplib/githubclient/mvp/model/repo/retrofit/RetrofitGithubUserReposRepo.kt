package com.gb.poplib.githubclient.mvp.model.repo.retrofit

import com.gb.poplib.githubclient.mvp.model.api.IDataSource
import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.GithubUserRepos
import com.gb.poplib.githubclient.mvp.model.network.INetworkStatus
import com.gb.poplib.githubclient.mvp.model.repo.IGithubUserReposRepo
import com.gb.poplib.githubclient.mvp.model.repo.cache.IReposCache
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUserReposRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: IReposCache
) : IGithubUserReposRepo {

    override fun getRepos(user: GithubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            user.reposUrl?.let { url->
                api.getRepos(url).flatMap {repositories->
                    Single.fromCallable {
                        cache.insertReposToCache(repositories, user)
                        repositories
                    }
                }
            } ?: Single.error<List<GithubUserRepos>>(RuntimeException("User has no repos url")).subscribeOn(
                Schedulers.io())
        } else {
            Single.fromCallable {
                cache.getReposCache(user)
            }
        }
    }.subscribeOn(Schedulers.io())

}