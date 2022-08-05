package com.appsgit.mylauncher.ui.main2

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.widget.LinearLayout
import com.appsgit.mylauncher.R
import com.appsgit.mylauncher.databinding.ActivityMain2Binding

class Main2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var wrapperLayout: LinearLayout

    @SuppressLint("ClickableViewAccessibility")
    private val viewTouchListener = View.OnTouchListener { _, motionEvent ->

        println(motionEvent.action)
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                finish()
                //overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                return@OnTouchListener true
            }
            MotionEvent.ACTION_UP -> {
                //hide the content...
                return@OnTouchListener true
            }
            else -> {
            }
        }
        false
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        wrapperLayout = binding.wrapperLayout
        wrapperLayout.setOnTouchListener(viewTouchListener)
    }

}