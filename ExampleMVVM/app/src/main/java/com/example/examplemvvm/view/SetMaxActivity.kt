package com.example.examplemvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.examplemvvm.R
import com.example.examplemvvm.databinding.ActivitySetMaxBinding
import com.example.examplemvvm.viewmodel.MainViewModel

class SetMaxActivity : AppCompatActivity() {
    private lateinit var mbind : ActivitySetMaxBinding

    private val model : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbind = DataBindingUtil.setContentView(this,R.layout.activity_set_max)

        mbind.lifecycleOwner = this
        mbind.viewmodelSetMaxNum = MainViewModel()

        var max : EditText = findViewById(R.id.maxnum)
        var completeBtn : Button = findViewById(R.id.complete)

        completeBtn.setOnClickListener{
            model.setMaxNum(Integer.parseInt(max.text.toString()))
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}