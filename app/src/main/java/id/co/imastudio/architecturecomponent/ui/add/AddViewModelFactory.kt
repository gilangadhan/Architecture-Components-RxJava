package id.co.imastudio.architecturecomponent.ui.add

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import id.co.imastudio.architecturecomponent.db.UserDao

/**
 * Created by gilan on 2/8/2018.
 */
class AddViewModelFactory (private val dataSource: UserDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddViewModel::class.java)){
            return AddViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}