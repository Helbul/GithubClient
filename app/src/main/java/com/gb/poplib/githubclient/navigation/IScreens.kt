package com.gb.poplib.githubclient.navigation

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(id: Int): Screen
}