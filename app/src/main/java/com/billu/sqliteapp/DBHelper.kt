package com.billu.sqliteapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Helper class for implementing SQLite database operations
 */
class DBHelper(context: Context) : SQLiteOpenHelper(context,"userData.db",null,1)  {

    private val db: SQLiteDatabase = this.writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE UserDetails(name varchar(20) primary key,contact number(10),dob date)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS UserDetails")
    }

    /**
     * Method to insert data into database
     */
    fun insertData(name: String,contact: String,dob : String): Boolean {
        val contentValues = ContentValues()
        contentValues.put("name",name)
        contentValues.put("contact",contact)
        contentValues.put("dob",dob)
        val result = db.insert("UserDetails",null,contentValues)
        return result != -1L
    }

    /**
     * Method to update data on database
     */
    fun updateData(name: String,contact: String,dob : String): Boolean {
        val contentValues = ContentValues()
        contentValues.put("contact",contact)
        contentValues.put("dob",dob)
        val result = db.update("UserDetails",contentValues,"name=?",Array(1) { name })
        return result != -1
    }

    /**
     * Method to delete data from database
     */
    fun deleteData(name: String): Boolean {
        val result = db.delete("UserDetails","name=?",Array(1) { name })
        return result != -1
    }

    /**
     * Method to get data from database
     */
    fun getData(): Cursor? {
        return db.rawQuery("SELECT * FROM UserDetails",null)
    }
}