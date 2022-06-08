package com.example.project2registration

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UsersViewModel(app : Application) : AndroidViewModel(app) {

    private val repo:UsersRepository

    val allUsers : LiveData<List<Users>>?

    init {
        repo = UsersRepository(app)
        allUsers = repo.getAllUsers()
    }


    fun getAllStudents() {
        repo.getAllUsers()
    }
    fun insertUsers(users: Users){
        repo.insertUsers(users)
    }
    fun updateUsers(users: Users){
        repo.updateUser(users)
    }
    fun deleteUsers(users: Users){
        repo.deleteUsers(users)
    }
    fun deleteAll(){
        repo.deleteAll()
    }


}