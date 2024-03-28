package com.example.practice3.data.remote

import com.example.practice3.domain.model.EntriesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("entries")
    suspend fun fetchEntryList(
//        @Query("") query1: String,
//        @Query("") query2: String,
//        @Query("") query3: String,
//        @Query("") query4: String
        ):
            Response<EntriesList>
}