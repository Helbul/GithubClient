package com.gb.poplib.githubclient.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUserRepos(
    @Expose val id: String,
    @Expose val name: String,
    @Expose val forksCount: Int
) : Parcelable
