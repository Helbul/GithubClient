package com.gb.poplib.githubclient.mvp.model.repo.cache.room

import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.room.Database
import com.gb.poplib.githubclient.mvp.model.entity.room.RoomGithubUser
import com.gb.poplib.githubclient.mvp.model.repo.cache.IUserCache


class RoomUserCache (private val db: Database): IUserCache {

    override fun insertUsersToCache(users: List<GithubUser>) {
        db.userDao.insert(users.map { user ->
            RoomGithubUser(
                user.id ?: "",
                user.login ?: "",
                user.avatarUrl ?: "",
                user.reposUrl ?: "",
                user.name ?: "",
                user.followers ?: 0
            )
        })
    }

    override fun getUsersCache() : List<GithubUser> =
        db.userDao.getAll().map { roomUser ->
            GithubUser(
                roomUser.id,
                roomUser.login,
                roomUser.avatarUrl,
                roomUser.reposUrl,
                roomUser.name,
                roomUser.followers)
        }

}