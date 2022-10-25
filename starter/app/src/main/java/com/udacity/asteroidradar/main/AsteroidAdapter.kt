package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.databinding.AsteroidItemBinding

class AsteroidAdapter (val asteroidClickListener:AsteroidClickListener):ListAdapter<Asteroid,AsteroidAdapter.ViewHolder>
    (AsteroidDiffCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.inflatingLayout(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!,asteroidClickListener)
    }


    class ViewHolder private constructor(val binding: AsteroidItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: Asteroid, asteroidClickListener: AsteroidClickListener) {
            binding.asteroidObject=item
            binding.clickListener=asteroidClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun inflatingLayout(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding=AsteroidItemBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }


    class AsteroidDiffCallBack:DiffUtil.ItemCallback<Asteroid>(){
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
              return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
             return oldItem==newItem
        }
    }

    class AsteroidClickListener(val clickListener: (item:Asteroid) -> Unit) {
        fun onClick(item:Asteroid) = clickListener(item)
    }
}