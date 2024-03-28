package com.example.practice3.domain.model

data class Entry(val API: String, val Description: String, val Auth: String,
val HTTPS: Boolean, val Link: Boolean)
data class EntriesList(val entries: ArrayList<Entry>)