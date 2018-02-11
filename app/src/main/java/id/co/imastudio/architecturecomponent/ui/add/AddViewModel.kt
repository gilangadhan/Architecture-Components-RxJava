package id.co.imastudio.architecturecomponent.ui.add

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.MainThread
import android.widget.Toast
import id.co.imastudio.architecturecomponent.db.User
import id.co.imastudio.architecturecomponent.db.UserDao
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by gilan on 2/8/2018.
 */
class AddViewModel(private val dataSource: UserDao) : ViewModel() {

    var addState: MutableLiveData<Boolean> = MutableLiveData()

    var compositeDisposable = CompositeDisposable()

    fun addState(user: User) {

        val dispose = Single.fromCallable { dataSource.addUser(user) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    addState.value = true
                }, {
                    addState.value = false
                })
        compositeDisposable.addAll(dispose)
    }

}