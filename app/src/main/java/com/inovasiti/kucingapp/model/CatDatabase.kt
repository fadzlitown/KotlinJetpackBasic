package com.inovasiti.kucingapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// use the @Database with class name & version defined
// make it as abstract (coz no implementation here only method) & extends with RoomDatabase
// this call will allow to be access
@Database(entities = arrayOf(CatSiam::class), version = 1)
abstract class CatDatabase : RoomDatabase() {

    // need to use singleton to avoid from multiple screen / module to access a single db from multiple threads
    // only 1 instance of object of this class to be used
    abstract fun catDao(): CatDao

    //create static variable
    //companion object = a singleton
    companion object{
        //this @Volatile are immediately made visible to other threads
        @Volatile private var instance: CatDatabase ? =null
        private val LOCK = Any()

        //when called the CatDatabase singleton with context
        //either we will be able to get an instance (where it's been created / sync when multiple threads try
        // to access this synchronized block of code, only lock 1 for 1 thread be able to access
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            //check if instance has been created or not
            instance ?: buildDatabase(context).also{
                instance = it
            }
        }

        // init the database
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CatDatabase::class.java,
            "catdatabase")
            .build()


    }


}