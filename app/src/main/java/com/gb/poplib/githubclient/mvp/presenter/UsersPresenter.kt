package com.gb.poplib.githubclient.mvp.presenter

import com.gb.poplib.githubclient.App
import com.gb.poplib.githubclient.mvp.model.GithubUser
import com.gb.poplib.githubclient.mvp.model.GithubUsersRepo
import com.gb.poplib.githubclient.mvp.presenter.list.IUserListPresenter
import com.gb.poplib.githubclient.mvp.view.UsersView
import com.gb.poplib.githubclient.mvp.view.list.UserItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UsersPresenter(val usersRepo: GithubUsersRepo, val router: Router) : MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size

    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        loadData()

        usersListPresenter.itemClickListener = {itemView ->
            router.navigateTo(App.androidScreens.user(itemView.pos))
        }
    }

    fun loadData() {
        execFromIterable()
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    private val userObserver = object : Observer<GithubUser> {
        var disposable: Disposable? = null

        override fun onSubscribe(d: Disposable) {
            disposable = d
            println("onSubscribe")

        }

        override fun onNext(t: GithubUser) {
            println("onNext: $t")
            usersListPresenter.users.add(t)
        }

        override fun onError(e: Throwable) {
            println("onError: ${e?.message}")
        }

        override fun onComplete() {
            println("onComplete")
        }
    }

    fun execFromIterable() {
        usersRepo.fromIterable()
            .subscribe(userObserver)
    }
}