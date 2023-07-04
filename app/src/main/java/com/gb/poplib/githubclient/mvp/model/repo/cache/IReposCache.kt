package com.gb.poplib.githubclient.mvp.model.repo.cache

import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.GithubUserRepos
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IReposCache {

    fun insertReposToCache(repos: List <GithubUserRepos>, user: GithubUser)

    fun getReposCache(user: GithubUser) : List<GithubUserRepos>
}