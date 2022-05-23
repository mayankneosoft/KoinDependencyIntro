package com.koin.dependencyIntro.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.koin.dependencyIntro.R
import com.koin.dependencyIntro.databinding.ActivityMainBinding
import com.koin.dependencyIntro.ui.adapter.MainAdapter
import com.koin.dependencyIntro.ui.viewmodel.MainViewModel
import com.koin.dependencyIntro.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModel()
    lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        setupUI()
        setupObserver()

    }

    private fun setupUI() {
        binding.rvList.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        binding.rvList.addItemDecoration(
            DividerItemDecoration(
                binding.rvList.context,
                (binding.rvList.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.rvList.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.users.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvList.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { userdata ->
                        adapter.addData(userdata)
                        adapter.notifyDataSetChanged()
                    }
                    binding.rvList.visibility = View.VISIBLE
                }

                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }


        })

    }


}
