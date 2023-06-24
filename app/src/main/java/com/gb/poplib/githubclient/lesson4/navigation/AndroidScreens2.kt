package com.gb.poplib.githubclient.lesson4.navigation

import com.gb.poplib.githubclient.lesson4.ui.ConverterFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen


class AndroidScreens2 : IScreens2{
    override fun converter(): Screen = FragmentScreen {ConverterFragment.newInstance()}
}