package id.co.imastudio.architecturecomponent.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import io.reactivex.Flowable
import io.reactivex.Single
import org.intellij.lang.annotations.Flow

/**
 * Created by gilan on 2/8/2018.
 */

@Dao
interface UserDao{

    @Insert(onConflict = REPLACE)
    fun addUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("select * from user")
    fun getAllUser() :Single<List<User>>

    @Query("select * from user where id_user = :id")
    fun getUserByID(id : String) :User
}