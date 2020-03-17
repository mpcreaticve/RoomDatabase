package com.mpcreative.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log

class SecoundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secound)
        Log.e("Tag1", "onCreate")

    }

    override fun onStart() {
        super.onStart()
        Log.e("Tag1", "onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.e("Tag1", "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("Tag1", "onRestart")

    }

    override fun onPause() {
        super.onPause()
        Log.e("Tag1", "onPause")

    }
}
