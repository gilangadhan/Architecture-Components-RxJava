package id.co.imastudio.architecturecomponent

import android.content.Context
import id.co.imastudio.architecturecomponent.db.AppDatabase
import id.co.imastudio.architecturecomponent.db.UserDao
import id.co.imastudio.architecturecomponent.ui.add.AddViewModelFactory
import id.co.imastudio.architecturecomponent.ui.main.MainActivityViewModelFactory

/**
 * Created by gilan on 2/8/2018.
 */
object Injection {

    fun provideUserDataSource(context: Context): UserDao {
        val database = AppDatabase.getDatabase(context)
        return database.itemDao()
    }

    fun provideViewModelFactory(context: Context): AddViewModelFactory {
        var dataSource = provideUserDataSource(context)
        return AddViewModelFactory(dataSource)
    }

    fun provideMainViewModelFactory(context: Context): MainActivityViewModelFactory {
        var dataSource = provideUserDataSource(context)
        return MainActivityViewModelFactory(dataSource)
    }

}