package br.com.devteam.spotmusick.repository.room

import android.content.Context
import androidx.room.*
import androidx.room.RoomDatabase
import br.com.devteam.spotmusick.repository.room.entity.UserDao
import br.com.devteam.spotmusick.repository.room.entity.UserProfileBasic


@Database(entities = [(UserProfileBasic::class)], version = 1,  exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun romDao(): UserDao
}

class RoomDatabase(context: Context) {

    private val appDB: AppDatabase

    init {
        appDB = Room.databaseBuilder(context, AppDatabase::class.java, "room-database")
            .allowMainThreadQueries().build()
    }

    fun getRoomDatabaseRef(): AppDatabase {
        return appDB
    }

}