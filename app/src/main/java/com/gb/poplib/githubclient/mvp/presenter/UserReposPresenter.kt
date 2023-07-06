package com.gb.poplib.githubclient.mvp.presenter

import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.di.module.repos.module.IReposScopeContainer
import com.gb.poplib.githubclient.di.module.users.module.IUsersScopeContainer
import com.gb.poplib.githubclient.mvp.model.entity.GithubUser
import com.gb.poplib.githubclient.mvp.model.entity.GithubUserRepos
import com.gb.poplib.githubclient.mvp.model.repo.IGithubUserReposRepo
import com.gb.poplib.githubclient.mvp.presenter.list.IUserReposListPresenter
import com.gb.poplib.githubclient.mvp.view.ReposView
import com.gb.poplib.githubclient.mvp.view.list.IReposItemView
import com.gb.poplib.githubclient.navigation.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import javax.inject.Inject

class UserReposPresenter (
    val user: GithubUser?
) : MvpPresenter<ReposView>(){

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var repositoriesRepo: IGithubUserReposRepo

    @Inject
    lateinit var reposScopeContainer: IReposScopeContainer

    private var compositeDisposable = CompositeDisposable()

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
            repositoriesRepo.getRepos(user)
                .observeOn(uiScheduler)
                .subscribe { repos ->
                    userReposListPresenter.repos.clear()
                    userReposListPresenter.repos.addAll(repos)
                    viewState.updateList()
                }.let {
                    compositeDisposable.add(it)
                }
        }
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        reposScopeContainer.releaseReposScope()
        super.onDestroy()
        compositeDisposable.dispose()
    }
}