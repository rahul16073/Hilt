package com.example.practice3.domain.repository

import com.example.practice3.domain.model.EntriesList

interface Repository {
    suspend fun fetchEntryList(query1: String, query2: String, query3: String,
    query4: String): EntriesList?
}