package id.co.imastudio.architecturecomponent.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull

/**
 * Created by gilan on 2/8/2018.
 */
@Entity(tableName = "user")
class User  {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "id_user")
    var id_user: Int? = null

    @ColumnInfo(name = "nama_user")
    var nama_user: String? = null

    @ColumnInfo(name = "nomer_user")
    var nomer_user : String? = null

    @ColumnInfo(name = "gambar_user")
    var gambar_user : String? = null

    constructor( nama_user: String?, nomer_user: String?, gambar_user: String?) {
        this.nama_user = nama_user
        this.nomer_user = nomer_user
        this.gambar_user = gambar_user
    }
    constructor()
}
