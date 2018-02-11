package id.co.imastudio.architecturecomponent.ui.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import id.co.imastudio.architecturecomponent.db.UserDao
import id.co.imastudio.architecturecomponent.ui.add.AddViewModel

/**
 * Created by gilan on 2/9/2018.
 */
class MainActivityViewModelFactory (private val dataSource: UserDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}