package br.com.devteam.spotmusick.repository.room.entity

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.*

@Entity(tableName = "user_profile")
data class UserProfileBasic(
    @PrimaryKey() var id: String,
    @ColumnInfo(name = "token") var token: String? = null,
    @ColumnInfo(name = "displayName") var displayName: String? = null,
    @ColumnInfo(name = "email") var email: String? = null
)

@Dao
public interface UserDao {
    @Query("SELECT * FROM user_profile WHERE id = :id ")
    public fun loadUser(id: String): UserProfileBasic

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserProfileBasic)

    @Update
    fun updateUser(user: UserProfileBasic)
}

