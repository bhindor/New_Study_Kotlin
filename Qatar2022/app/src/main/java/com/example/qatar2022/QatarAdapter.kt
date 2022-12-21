package com.example.qatar2022

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.qatar2022.databinding.RecycleViewBinding

class QatarAdapter(val context: Context): RecyclerView.Adapter<QatarAdapter.MyViewHolder>() {
    var dataList = mutableListOf<Nation>()

    inner class MyViewHolder(private val binding: RecycleViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(nation: Nation){
            binding.countryImg.setImageResource(nation.flag)
            binding.countryTv.text = nation.name
            binding.groupTv.text = "${nation.group}조"
            binding.itemList.setOnClickListener {
                Intent(context, DetailActivity::class.java).apply {
                    putExtra("flag", nation.flag)
                    putExtra("name", nation.name)
                    putExtra("group", nation.group)
                    putExtra("desc", nation.desc)
                }.run { context.startActivity(this) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecycleViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

}