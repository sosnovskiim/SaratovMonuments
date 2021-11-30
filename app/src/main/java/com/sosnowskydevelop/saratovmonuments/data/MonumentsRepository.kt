package com.sosnowskydevelop.saratovmonuments.data

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.sosnowskydevelop.saratovmonuments.utilities.DatabaseHelper
import java.io.IOException

class MonumentsRepository(context: Context) {
    private var monuments: Array<Monument> = arrayOf()

    fun getMonuments(categoryId: Long): Array<Monument> {
        var result: Array<Monument> = arrayOf()
        monuments.forEach { monument ->
            if (monument.categoryId == categoryId) {
                result += monument
            }
        }
        result.sortBy { it.name }
        return result
    }

    fun getMonumentsBySearchRequest(
        searchRequest: String,
        categoryId: Long?,
    ): Array<Monument> {
        var result: Array<Monument> = arrayOf()

        val cursor: Cursor = if (categoryId == null) {
            database.rawQuery(
                "SELECT _id, _categoryId, _name, _photoName, _installationDate, _authors, _description, _links, _pointLatitude, _pointLongitude FROM Monument WHERE UPPER(_name) LIKE UPPER('%$searchRequest%')",
                null
            )
        } else {
            database.rawQuery(
                "SELECT _id, _categoryId, _name, _photoName, _installationDate, _authors, _description, _links, _pointLatitude, _pointLongitude FROM Monument WHERE UPPER(_name) LIKE UPPER('%$searchRequest%') AND _categoryId = $categoryId",
                null
            )
        }

        var isEntryNotEmpty: Boolean = cursor.moveToFirst()
        while (isEntryNotEmpty) {
            result += Monument(
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

        result.sortBy { it.name }

        return result
    }

    fun getMonument(monumentId: Long): Monument? {
        monuments.forEach { monument ->
            if (monument.id == monumentId) {
                return monument
            }
        }
        return null
    }

    private val database: SQLiteDatabase

    init {
        val databaseHelper = DatabaseHelper(context)

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