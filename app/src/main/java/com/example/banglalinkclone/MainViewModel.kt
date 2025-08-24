package com.example.banglalinkclone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val repo: AppRepository) : ViewModel() {

    private val _user = MutableLiveData<UserEntity?>()
    val user: LiveData<UserEntity?> = _user

    private val _packages = MutableLiveData<List<PackageEntity>>()
    val packages: LiveData<List<PackageEntity>> = _packages

    private val _history = MutableLiveData<List<HistoryEntity>>()
    val history: LiveData<List<HistoryEntity>> = _history

    fun loadUser() {
        viewModelScope.launch {
            _user.postValue(repo.getUser())
        }
    }

    fun loadPackages(type: String) {
        viewModelScope.launch {
            _packages.postValue(repo.getPackagesByType(type))
        }
    }

    fun loadHistory() {
        viewModelScope.launch {
            _history.postValue(repo.getAllHistory())
        }
    }

    fun buyPackage(pkg: PackageEntity) {
        viewModelScope.launch {
            val user = repo.getUser()
            if (user != null && user.balance >= pkg.price) {
                user.balance -= pkg.price
                repo.updateUser(user)
                repo.insertHistory(
                    HistoryEntity(packageName = pkg.name, amount = pkg.price, date = System.currentTimeMillis())
                )
                loadUser()
                loadHistory()
            }
        }
    }
}
