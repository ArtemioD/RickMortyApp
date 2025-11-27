package org.example.project.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import org.example.project.data.database.dao.UserPreferencesDAO
import org.example.project.data.database.entity.CharacterOfTheDayEntity

const val DATABASE_NAME = "rm_app_database.db"

expect object RickMortyCTor: RoomDatabaseConstructor<RickMortyDatabase>

@Database(entities = [CharacterOfTheDayEntity::class], version = 1)
@ConstructedBy(RickMortyCTor::class)
abstract class RickMortyDatabase : RoomDatabase() {

    abstract fun getPreferencesDao(): UserPreferencesDAO

}