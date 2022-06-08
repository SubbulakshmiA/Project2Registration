package com.example.project2registration

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UsersDao {
    @Insert
    fun insertUsers(users: Users)

    @Query("select * from users ")
    fun selectUsers(): LiveData<List<Users>>

    @Update
    fun updateUsers(users: Users)

    @Delete
    fun deleteUsers(users: Users)

    @Query("delete from users")
    fun deleteAll()
}