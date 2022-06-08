package com.example.project2registration

import android.content.Context
import androidx.lifecycle.LiveData

class UsersRepository(context: Context) {

        var db:UsersDao?=AppDatabase.getInstance(context)?.usersDao()

        fun getAllUsers(): LiveData<List<Users>>?{
            return db?.selectUsers()
        }
        fun insertUsers(users:Users){
            db?.insertUsers(users)
        }
        fun updateUser(users:Users){
            db?.updateUsers(users)
        }
        fun deleteUsers(users:Users){
            db?.deleteUsers(users)
        }
        fun deleteAll(){
            db?.deleteAll()
        }

    }