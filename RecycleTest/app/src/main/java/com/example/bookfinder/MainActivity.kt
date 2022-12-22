package com.example.bookfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookfinder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerAdapter: RecyclerAdapter

    private val mDats = mutableListOf<Twice>(
        Twice(R.drawable.animal, "고양이", "ㄱㄴ", R.drawable.animal),
        Twice(R.drawable.eb13, "ㅎㅅㅌ", "ㅎㅉ", R.drawable.eb13),
        Twice(R.drawable.getimage, "ㅁㄹ", "ㅁㄹㅁㄹ", R.drawable.getimage),
        Twice(R.drawable.lion, "ㄹㅇㄴ", "ㄹㅇㄴ", R.drawable.lion)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val members:ArrayList<Twice> = ArrayList()
//        members.add(mDats)
        initRecyclerView()

    }
    private fun initRecyclerView() {
        val adapter = RecyclerAdapter(this)
        adapter.dataList = mDats
        binding.recyclerImg.adapter = adapter
        binding.recyclerImg.layoutManager = LinearLayoutManager(this)
        val deco = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.recyclerImg.addItemDecoration(deco)

    }
}