package com.gb.poplib.githubclient.navigation

import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.GithubUserRepos
import com.gb.poplib.githubclient.ui.fragment.ForksCountFragment
import com.gb.poplib.githubclient.ui.fragment.ReposFragment
import com.gb.poplib.githubclient.ui.fragment.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {

    override fun users() = FragmentScreen { UsersFragment.newInstance() }

    override fun repos(user: GithubUser) = FragmentScreen { ReposFragment.newInstance(user) }

    override fun forks(repos: GithubUserRepos) = FragmentScreen { ForksCountFragment.newInstance(repos) }

}