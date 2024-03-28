package com.example.practice3.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practice3.R
import com.example.practice3.databinding.ActivityMainBinding
import com.example.practice3.domain.model.Entry
import com.google.android.material.slider.Slider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ViewModel
    private var entriesList: ArrayList<Entry> = ArrayList()
    private val LOCATION_PERMISSION_REQUEST_CODE = 11
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var adapter: Adapter = Adapter(this, entriesList)
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
        recycler_view.layoutManager = LinearLayoutManager(this)
        viewModel.entriesList.observe(this){
            progress_bar.visibility = View.GONE
            val  startPos = entriesList.size
            entriesList.addAll(it.entries)
            Log.i("rahuls", entriesList.size.toString())
            binding.recyclerView.adapter = adapter
            adapter.notifyItemRangeInserted(startPos, it.entries.size)
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            progress_bar.visibility = View.VISIBLE
            viewModel.fetch("jj")
        }
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstChildPos = layoutManager.findFirstVisibleItemPosition()
                val childCount = layoutManager.childCount
                val itemCount = layoutManager.itemCount
                if(firstChildPos+childCount>=itemCount){
                    binding.progressBar.visibility = View.VISIBLE
                    viewModel.fetch("jj")
                }
            }
        })

        slider.addOnChangeListener(object : Slider.OnChangeListener{
            override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
                binding.progressBar.visibility = View.VISIBLE
                viewModel.fetch("kjdj")
            }

        })

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                binding.progressBar.visibility = View.VISIBLE
                viewModel.fetch("jdj")
            } else {
               Toast.makeText(this, "need permission", Toast.LENGTH_LONG).show()
            }
        }
    }


}