package com.gb.poplib.githubclient.mvp.model.api

import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface IDataSource {
    @GET("/users")
    fun getUsers() : Single<List<GithubUser>>
}