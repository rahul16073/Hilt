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
        var result = api.fetchEntryList()
        return result?.body()
    }
}