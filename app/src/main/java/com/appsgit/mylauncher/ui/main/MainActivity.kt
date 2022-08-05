package com.appsgit.mylauncher.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appsgit.mylauncher.R
import com.appsgit.mylauncher.databinding.ActivityMain2Binding
import com.appsgit.mylauncher.databinding.ActivityMainBinding
import com.appsgit.mylauncher.extensions.enableGesture
import com.appsgit.mylauncher.model.AppModel
import com.appsgit.mylauncher.ui.main2.Main2Activity
import com.appsgit.mylauncher.utils.AppUtil

class MainActivity : AppCompatActivity(), ItemClickListener {

    private lateinit var binding: ActivityMainBinding
    lateinit var recyclerView: RecyclerView
    lateinit var view1: FrameLayout
    lateinit var view2: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        recyclerView = binding.recyclerview
        view1 = binding.frameLayout1
        view2 = binding.frameLayout2

        getInstalledApps()

        enableGesture()
    }

    fun onButtonClick(view: View) {
        with(Intent(this, Main2Activity::class.java)) {
            startActivity(this)
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        }
    }

    override fun onItemClick(app: AppModel?) {
        app?.let { AppUtil.launchApp(applicationContext, it) }
    }

    fun getInstalledApps() {
        with(Intent(Intent.ACTION_MAIN)) {
            addCategory(Intent.CATEGORY_LAUNCHER)
            val list = AppUtil.getInstalledApps(this, applicationContext)
            with(recyclerView) {
                layoutManager = GridLayoutManager(this@MainActivity, 4)
                adapter = AppListAdapter(list, this@MainActivity)
            }
        }
    }
}
