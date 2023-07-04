package com.gb.poplib.githubclient.navigation

import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.GithubUserRepos
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun repos(user: GithubUser): Screen
    fun forks(user: GithubUserRepos): Screen
}