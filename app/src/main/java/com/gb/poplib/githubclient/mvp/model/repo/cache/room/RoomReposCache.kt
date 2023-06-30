package com.gb.poplib.githubclient.mvp.model.repo.cache.room

import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.GithubUserRepos
import com.gb.poplib.githubclient.mvp.model.entity.room.Database
import com.gb.poplib.githubclient.mvp.model.entity.room.RoomGithubRepository
import com.gb.poplib.githubclient.mvp.model.repo.cache.IReposCache

class RoomReposCache (val db: Database) : IReposCache {

    override fun insertReposToCache(repos: List<GithubUserRepos>, user: GithubUser) {

        val roomUser = user.login?.let {
            db.userDao.findByLogin(it)
        } ?: throw RuntimeException("No such user in cache")

        val roomRepos = repos.map {
            RoomGithubRepository(it.id ?: "", it.name ?: "", it.forksCount ?: 0, user.id)
        }

        db.repositoryDao.insert(roomRepos)
    }


    override fun getReposCache(user: GithubUser) : List<GithubUserRepos> {

        val roomUser = user.login?.let {
            db.userDao.findByLogin(it)
        } ?: throw RuntimeException("No such user in cache")

        return db.repositoryDao.findForUser(roomUser.id).map {
            GithubUserRepos(
                it.id, it.name, it.forksCount
            )
        }
    }
}