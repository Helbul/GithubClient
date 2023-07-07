package com.gb.poplib.githubclient.di.module.users.module

import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.di.module.users.UsersScope
import com.gb.poplib.githubclient.mvp.model.api.IDataSource
import com.gb.poplib.githubclient.mvp.model.entity.room.Database
import com.gb.poplib.githubclient.mvp.model.network.INetworkStatus
import com.gb.poplib.githubclient.mvp.model.repo.IGithubUsersRepo
import com.gb.poplib.githubclient.mvp.model.repo.cache.IUserCache
import com.gb.poplib.githubclient.mvp.model.repo.cache.room.RoomUserCache
import com.gb.poplib.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides

@Module
class UsersModule {
    @Provides
    fun usersCache(database: Database): IUserCache = RoomUserCache(database)

    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus:
        INetworkStatus,
        cache: IUserCache
    ) : IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

    @UsersScope
    @Provides
    open fun scopeContainer(app: App): IUsersScopeContainer = app
}