package id.co.imastudio.architecturecomponent.ui.main

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import id.co.imastudio.architecturecomponent.db.AppDatabase
import id.co.imastudio.architecturecomponent.db.User
import id.co.imastudio.architecturecomponent.db.UserDao
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableHelper.dispose
import io.reactivex.schedulers.Schedulers

/**
 * Created by gilan on 2/9/2018.
 */
class MainActivityViewModel(private val dataSource: UserDao) : ViewModel() {


    var loadState: MutableLiveData<List<User>> = MutableLiveData()

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }
    var deletestate: MutableLiveData<Boolean> = MutableLiveData()


    fun loadState() {

        val dispose = dataSource.getAllUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ success ->
                    loadState.value = success

                }, {
                    deletestate.value = false
                })

        compositeDisposable.add(dispose)
    }

    fun delete(user: User) {
        val disposebale = Single.fromCallable { dataSource.deleteUser(user) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    deletestate.value = true
                    loadState()
                }, {
                    deletestate.value = false
                })
        addDisposable(disposebale)
    }

    fun addDisposable(subscription: Disposable?) {
        subscription?.let {
            compositeDisposable.add(it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        dispose()
    }

    private fun dispose() {
        if (compositeDisposable.size() > 0) {
            compositeDisposable.clear()
        }
    }


}