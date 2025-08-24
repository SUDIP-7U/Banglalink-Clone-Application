package com.example.banglalinkclone

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val phoneNumber: String,
    val name: String,
    var balance: Double
)

@Entity(tableName = "package")
data class PackageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val type: String, // e.g., Internet, Minute, SMS
    val price: Double,
    val validity: String,
    val description: String
)

@Entity(tableName = "history")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val packageName: String,
    val amount: Double,
    val date: Long
)
