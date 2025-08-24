package com.example.banglalinkclone

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getUser(): UserEntity?

    @Update
    suspend fun updateUser(user: UserEntity)
}

@Dao
interface PackageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPackages(packages: List<PackageEntity>)

    @Query("SELECT * FROM package WHERE type = :type")
    suspend fun getPackagesByType(type: String): List<PackageEntity>
}

@Dao
interface HistoryDao {
    @Insert
    suspend fun insertHistory(history: HistoryEntity)

    @Query("SELECT * FROM history ORDER BY date DESC")
    suspend fun getAllHistory(): List<HistoryEntity>
}
