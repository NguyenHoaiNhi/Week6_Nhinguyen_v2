package com.example.myapplication.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var description: String,
    var completed: Boolean?,
    var user_uid: Int?
):Parcelable{
    constructor(): this(null, "", null, null)
}
@Parcelize
@Entity
data class  User(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String? = ""
):Parcelable