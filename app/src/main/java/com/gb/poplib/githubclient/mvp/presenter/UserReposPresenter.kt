package com.gb.poplib.githubclient.mvp.presenter

import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.GithubUserRepos
import com.gb.poplib.githubclient.mvp.model.repo.IGithubUserReposRepo
import com.gb.poplib.githubclient.mvp.presenter.list.IUserReposListPresenter
import com.gb.poplib.githubclient.mvp.view.ReposView
import com.gb.poplib.githubclient.mvp.view.list.IReposItemView
import com.gb.poplib.githubclient.navigation.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UserReposPresenter (
    val user: GithubUser?,
    val uiScheduler: Scheduler,
    val usersRepo: IGithubUserReposRepo,
    val router: Router,
    val screens: IScreens
) : MvpPresenter<ReposView>(){

    class UserReposListPresenter : IUserReposListPresenter {
        val repos = mutableListOf<GithubUserRepos>()

        override var itemClickListener: ((IReposItemView) -> Unit)? = null

        override fun bindView(view: IReposItemView) {
            val repo = repos[view.pos]
            repo.name?.let {
                view.setNameRepo(it)
            }
        }

        override fun getCount(): Int = repos.size
    }

    val userReposListPresenter = UserReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        user?.let {
            viewState.init(user)
        }
        loadData()

        userReposListPresenter.itemClickListener = {itemView ->
            router.navigateTo(screens.forks(userReposListPresenter.repos[itemView.pos]))
        }
    }

    fun loadData() {
        user?.let {
            usersRepo.getRepos(user)
                .observeOn(uiScheduler)
                .subscribe {repos ->
                    userReposListPresenter.repos.clear()
                    userReposListPresenter.repos.addAll(repos)
                    viewState.updateList()
                }
        }
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}