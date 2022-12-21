package com.example.qatar2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.qatar2022.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private var mDatas = mutableListOf<Nation>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingView()
    }
    private fun settingView() {
        val flag = intent.getIntExtra("flag", R.drawable.kr)
        val name = intent.getStringExtra("name")
        val group = intent.getStringExtra("group")
        val desc = intent.getStringExtra("desc")

        binding.countryImg.setImageResource(flag)
        binding.titleTv.text = name
        binding.groupTv.text = "${group}조"
        binding.bodyTv.text = desc
    }
}