package com.example.listenertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var tv:TextView? = findViewById(R.id.tv_test)
    var clickCount = 0
    val btn_left: Button = findViewById(R.id.btn_left)
    val btn_right: Button = findViewById(R.id.btn_right)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tv?.text = "Hello Android"

        val btn: Button = findViewById(R.id.btn_right)
//        btn.setOnClickListener {
//            clickCount++
//            tv?.text = "$clickCount 번 클릭"
//        }
    }
    private fun listenerSetting() {

        btn_left.setOnClickListener(this)
        btn_right.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            btn_left -> {
                tv?.text = "left"
            }
            btn_right -> {
                tv?.text = "right"
            }
        }
    }
}