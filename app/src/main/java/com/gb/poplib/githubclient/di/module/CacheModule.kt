package com.gb.poplib.githubclient.di.module

import androidx.room.Room
import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.mvp.model.entity.room.Database
import com.gb.poplib.githubclient.mvp.model.repo.cache.IReposCache
import com.gb.poplib.githubclient.mvp.model.repo.cache.IUserCache
import com.gb.poplib.githubclient.mvp.model.repo.cache.room.RoomReposCache
import com.gb.poplib.githubclient.mvp.model.repo.cache.room.RoomUserCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
        .build()

    @Singleton
    @Provides
    fun usersCache(database: Database): IUserCache = RoomUserCache(database)

    @Singleton
    @Provides
    fun repositoriesCache(database: Database): IReposCache {
        return RoomReposCache(database)
    }
}