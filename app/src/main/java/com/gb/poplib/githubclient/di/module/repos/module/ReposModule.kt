package com.gb.poplib.githubclient.di.module.repos.module

import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.di.module.repos.ReposScope
import com.gb.poplib.githubclient.mvp.model.api.IDataSource
import com.gb.poplib.githubclient.mvp.model.entity.room.Database
import com.gb.poplib.githubclient.mvp.model.network.INetworkStatus
import com.gb.poplib.githubclient.mvp.model.repo.IGithubUserReposRepo
import com.gb.poplib.githubclient.mvp.model.repo.cache.IReposCache
import com.gb.poplib.githubclient.mvp.model.repo.cache.room.RoomReposCache
import com.gb.poplib.githubclient.mvp.model.repo.retrofit.RetrofitGithubUserReposRepo
import dagger.Module
import dagger.Provides

@Module
class ReposModule {
    @Provides
    fun repositoriesCache(database: Database): IReposCache {
        return RoomReposCache(database)
    }

    @Provides
    fun repositoriesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IReposCache
    ) : IGithubUserReposRepo = RetrofitGithubUserReposRepo(api, networkStatus, cache)

    @ReposScope
    @Provides
    open fun scopeContainer(app: App): IReposScopeContainer = app
}
