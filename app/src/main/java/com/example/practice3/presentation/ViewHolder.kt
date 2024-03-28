package com.example.practice3.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.practice3.databinding.RecycleItemBinding
import com.example.practice3.domain.model.Entry
import kotlinx.android.synthetic.main.recycle_item.view.*

class ViewHolder(private val binding: RecycleItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun onBind(mCtx: Context, entry: Entry){
    binding.api.text = entry.API
    binding.desc.text = entry.Description
    binding.auth.text = entry.Auth
    binding.https.text = entry.HTTPS.toString()
    binding.link.text = entry.Link.toString()
    binding.link.setOnClickListener{
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(entry.Link.toString()))
        mCtx.startActivity(intent)

    }
    }

}