package com.klyxdevs.rickmortyapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characteroftheday")
data class CharacterOfTheDayEntity(
    @PrimaryKey
    val id: String,
    val isAlive: Boolean,
    val image: String,
    val name: String,
    val selectedDate:String
)