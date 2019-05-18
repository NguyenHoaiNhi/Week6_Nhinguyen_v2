package com.example.myapplication.room


import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy




@Dao
interface TaskDAO {
    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Query("SELECT *FROM task WHERE description LIKE :description")
    fun findByName(description: String) :Task

    @Query("SELECT * FROM task WHERE id =:id")
    fun findById(id: Int): Task

    @Insert
    fun insertAll(vararg todo: Task) : List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(obj: Task): Long

    @Delete
    fun delete(todo: Task)

    @Update
    fun update( Task: Task)

    @Query("DELETE FROM task")
    fun deleteAllTask()

}


@Dao
interface UserDAO{
    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    @Query("SELECT *FROM User WHERE name LIKE :name")
    fun findByName(name: String) :User

    @Query("SELECT * FROM task WHERE id =:id")
    fun findById(id: Int): User

    @Insert
    fun insertAll(vararg todo: User) : List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(obj: User): Long

    @Delete
    fun delete(todo: User)

    @Update
    fun update( Task: User)

    @Query("DELETE FROM User")
    fun deleteAllUser()
}