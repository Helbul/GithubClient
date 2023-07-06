package com.gb.poplib.githubclient.di.module

import com.gb.poplib.githubclient.mvp.model.api.IDataSource
import com.gb.poplib.githubclient.mvp.model.network.INetworkStatus
import com.gb.poplib.githubclient.mvp.model.repo.IGithubUserReposRepo
import com.gb.poplib.githubclient.mvp.model.repo.IGithubUsersRepo
import com.gb.poplib.githubclient.mvp.model.repo.cache.IReposCache
import com.gb.poplib.githubclient.mvp.model.repo.cache.IUserCache
import com.gb.poplib.githubclient.mvp.model.repo.retrofit.RetrofitGithubUserReposRepo
import com.gb.poplib.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

//@Module
//class RepoModule {
//
//    @Singleton
//    @Provides
//    fun usersRepo(
//        api: IDataSource,
//        networkStatus:
//        INetworkStatus,
//        cache: IUserCache
//    ) : IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)
//
//    @Singleton
//    @Provides
//    fun repositoriesRepo(
//        api: IDataSource,
//        networkStatus: INetworkStatus,
//        cache: IReposCache
//    ) : IGithubUserReposRepo = RetrofitGithubUserReposRepo(api, networkStatus, cache)
//}