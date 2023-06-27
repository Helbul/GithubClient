package com.gb.poplib.githubclient.mvp.model.repo.retrofit

import com.gb.poplib.githubclient.mvp.model.api.IDataSource
import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.GithubUserRepos
import com.gb.poplib.githubclient.mvp.model.repo.IGithubUserReposRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUserReposRepo(val api: IDataSource) : IGithubUserReposRepo {
    override fun getRepos(user: GithubUser) = user.reposUrl?.let {
        api.getRepos(it).subscribeOn(Schedulers.io())
    } ?: Single.error(RuntimeException("No repos url"))
}