package com.gb.poplib.githubclient.navigation

import com.gb.poplib.githubclient.ui.fragment.UserDetailsFragment
import com.gb.poplib.githubclient.ui.fragment.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(id: Int) = FragmentScreen { UserDetailsFragment.newInstance(id) }

}