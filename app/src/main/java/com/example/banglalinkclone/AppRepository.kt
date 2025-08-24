package com.example.banglalinkclone

class AppRepository(private val db: AppDatabase) {

    suspend fun getUser() = db.userDao().getUser()
    suspend fun insertUser(user: UserEntity) = db.userDao().insertUser(user)
    suspend fun updateUser(user: UserEntity) = db.userDao().updateUser(user)

    suspend fun getPackagesByType(type: String) = db.packageDao().getPackagesByType(type)
    suspend fun insertPackages(packages: List<PackageEntity>) = db.packageDao().insertPackages(packages)

    suspend fun insertHistory(history: HistoryEntity) = db.historyDao().insertHistory(history)
    suspend fun getAllHistory() = db.historyDao().getAllHistory()
}
