package com.sosnowskydevelop.saratovmonuments.data

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.sosnowskydevelop.saratovmonuments.utilities.DatabaseHelper
import java.io.IOException

class CategoriesRepository(context: Context) {
    private var categories: Array<Category> = arrayOf()

    fun getCategories(): Array<Category> = categories

    fun getCategory(categoryId: Long): Category = categories[categoryId.toInt() - 1]

    init {
        val databaseHelper = DatabaseHelper(context)
        val database: SQLiteDatabase

        try {
            databaseHelper.updateDataBase()
        } catch (mIOException: IOException) {
            throw Error("UnableToUpdateDatabase")
        }

        try {
            database = databaseHelper.readableDatabase
        } catch (mSQLException: SQLException) {
            throw mSQLException
        }

        val cursor: Cursor = database.query(
            "Category",
            arrayOf(
                "_id",
                "_name",
            ),
            null, null, null, null, null
        )
        var isEntryNotEmpty: Boolean = cursor.moveToFirst()
        while (isEntryNotEmpty) {
            categories += Category(
                _id = cursor.getLong(cursor.getColumnIndex("_id")),
                _name = cursor.getString(cursor.getColumnIndex("_name")),
            )
            isEntryNotEmpty = cursor.moveToNext()
        }
        cursor.close()
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: CategoriesRepository? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CategoriesRepository(context = context).also { instance = it }
            }
    }
}