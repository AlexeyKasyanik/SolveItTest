package com.gmail.lyohakasianik.solveittest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.lyohakasianik.solveittest.repository.entity.Response
import com.gmail.lyohakasianik.solveittest.repository.entity.ResponseForDb
import com.gmail.lyohakasianik.solveittest.utils.RESPONSE_PERSON_TABLE_NAME


@Dao
interface PersonResponseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(response: ResponseForDb)

    @Query("SELECT * FROM $RESPONSE_PERSON_TABLE_NAME")
    fun get(): ResponseForDb

}