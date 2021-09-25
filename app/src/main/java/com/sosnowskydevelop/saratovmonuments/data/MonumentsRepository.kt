package com.sosnowskydevelop.saratovmonuments.data

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.sosnowskydevelop.saratovmonuments.utilities.DatabaseHelper
import java.io.IOException

class MonumentsRepository(context: Context) {
    private var monuments: Array<Monument> = arrayOf()

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
            "Monument",
            arrayOf(
                "_id",
                "_categoryId",
                "_name",
                "_photoName",
                "_installationDate",
                "_authors",
                "_description",
                "_links",
                "_pointLatitude",
                "_pointLongitude",
            ),
            null, null, null, null, null
        )
        var isEntryNotEmpty: Boolean = cursor.moveToFirst()
        while (isEntryNotEmpty) {
            monuments += Monument(
                _id = cursor.getLong(cursor.getColumnIndex("_id")),
                _categoryId = cursor.getLong(cursor.getColumnIndex("_categoryId")),
                _name = cursor.getString(cursor.getColumnIndex("_name")),
                _photoName = cursor.getString(cursor.getColumnIndex("_photoName")),
                _installationDate = cursor.getString(cursor.getColumnIndex("_installationDate")),
                _authors = cursor.getString(cursor.getColumnIndex("_authors")),
                _description = cursor.getString(cursor.getColumnIndex("_description")),
                _links = cursor.getString(cursor.getColumnIndex("_links")),
                _pointLatitude = cursor.getDouble(cursor.getColumnIndex("_pointLatitude")),
                _pointLongitude = cursor.getDouble(cursor.getColumnIndex("_pointLongitude")),
            )
            isEntryNotEmpty = cursor.moveToNext()
        }
        cursor.close()
    }

    fun getMonuments(categoryId: Long): Array<Monument> {
        var res: Array<Monument> = arrayOf()
        monuments.forEach { monument ->
            if (monument.categoryId == categoryId) {
                res += monument
            }
        }
        return res
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: MonumentsRepository? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: MonumentsRepository(context = context).also { instance = it }
            }
    }
}