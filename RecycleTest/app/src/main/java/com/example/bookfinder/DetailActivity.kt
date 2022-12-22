package com.example.bookfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookfinder.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingView()
    }

    private fun settingView() {
        val image = intent.getIntExtra("image", R.drawable.eb13)
        binding.imgMember.setImageResource(image)
        binding.tvName.text = intent.getStringExtra("name")
        binding.tvNick.text = intent.getStringExtra("nick")
    }
}