package com.tashariko.imageinfo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tashariko.imageinfo.R
import com.tashariko.imageinfo.data.ImageInfoViewModel
import com.tashariko.imageinfo.rv.ImageInfoAdapter

class MainActivity: AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    lateinit var viewModel: ImageInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ImageInfoViewModel::class.java)
        recyclerView = findViewById(R.id.recyclerView)

        val adapter =
            ImageInfoAdapter(applicationContext)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.imgListLiveData.observe(this, Observer {
            adapter.submitList(it)

        })

        viewModel.getData(this)

    }


}