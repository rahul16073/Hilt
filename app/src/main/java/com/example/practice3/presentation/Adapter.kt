package com.example.practice3.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practice3.R
import com.example.practice3.databinding.RecycleItemBinding
import com.example.practice3.domain.model.Entry

class Adapter(private val mCtx: Context, private val entriesList: List<Entry>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(RecycleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewHolder){
            holder.onBind(mCtx, entriesList[position])
        }
    }

    override fun getItemCount(): Int {
        return entriesList.size
    }
}