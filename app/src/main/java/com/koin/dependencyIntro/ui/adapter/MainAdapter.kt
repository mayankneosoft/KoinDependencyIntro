package com.koin.dependencyIntro.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.koin.dependencyIntro.R
import com.koin.dependencyIntro.data.model.User
import com.koin.dependencyIntro.databinding.RowItemLayoutBinding

class MainAdapter(private val userList: ArrayList<User>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RowItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(userList[position]){
                binding.textViewUserName.text = this.name
                binding.textViewUserEmail.text = this.email

                Glide.with(binding.imageViewAvatar.context)
                    .load(this.avatar)
                    .placeholder(R.drawable.defaultperson)
                    .into(binding.imageViewAvatar)
            }
        }
    }

    override fun getItemCount(): Int = userList.size


    fun addData(list: List<User>) {
        userList.addAll(list)
    }

}