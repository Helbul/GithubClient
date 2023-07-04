package com.gb.poplib.githubclient.di.module

import android.widget.ImageView
import com.gb.poplib.githubclient.mvp.view.IImageLoader
import com.gb.poplib.githubclient.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageLoaderModule {

    @Singleton
    @Provides
    fun loadInto(): IImageLoader<ImageView> = GlideImageLoader()
}