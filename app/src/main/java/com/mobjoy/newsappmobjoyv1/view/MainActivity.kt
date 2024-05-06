package com.mobjoy.newsappmobjoyv1.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.mobjoy.newsappmobjoyv1.R
import com.mobjoy.newsappmobjoyv1.databinding.ActivityMainBinding
import com.mobjoy.newsappmobjoyv1.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private var adapter : NewsAdapter = NewsAdapter(listOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



    }

    override fun onStart() {
        viewModel.getNewsFromAPI() //
        super.onStart()
    }
    private fun initViews() {
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewBinding.newsRV.adapter = adapter
        observeToLiveData()
    }

    //observer pattern -> live
    private fun observeToLiveData(){
        viewModel.newsLiveData.observe(this){ item->
            adapter.bindNews(item)
        }
        viewModel.showError.observe(this){errorText ->
            Toast.makeText(this, errorText.toString() , Toast.LENGTH_SHORT).show()
        }
    }





}