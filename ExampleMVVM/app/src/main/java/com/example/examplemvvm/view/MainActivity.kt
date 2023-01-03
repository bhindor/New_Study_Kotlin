package com.example.examplemvvm.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.examplemvvm.R
import com.example.examplemvvm.databinding.ActivityMainBinding
import com.example.examplemvvm.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mbinding: ActivityMainBinding
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mbinding.lifecycleOwner = this
        mbinding.vm = model

        var setMaxnum = findViewById<Button>(R.id.setMaxNumbtn)
        setMaxnum.setOnClickListener{
            var intent = Intent(this, SetMaxActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}