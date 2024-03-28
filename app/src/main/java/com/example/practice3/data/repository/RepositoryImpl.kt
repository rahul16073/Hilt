package com.example.practice3.data.repository

import android.util.Log
import com.example.practice3.data.remote.Api
import com.example.practice3.domain.model.EntriesList
import com.example.practice3.domain.repository.Repository
import retrofit2.Response
import java.lang.Exception

class RepositoryImpl(private val api: Api): Repository {
    override suspend fun fetchEntryList(
        query1: String,
        query2: String,
        query3: String,
        query4: String
    ): EntriesList? {
        var result: Response<EntriesList>? = null
        try {
            result = api.fetchEntryList(/*query1, query2, query3, query4*/)
            Log.i("rahul", result.code().toString())
        }
        catch (e: Exception){
            e.printStackTrace()
        }
        return result?.body()
    }
}