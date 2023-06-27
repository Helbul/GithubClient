package com.gb.poplib.githubclient.mvp.model.repo

import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.GithubUserRepos
import io.reactivex.rxjava3.core.Single

interface IGithubUserReposRepo {
    fun getRepos(user: GithubUser): Single<List<GithubUserRepos>>
}