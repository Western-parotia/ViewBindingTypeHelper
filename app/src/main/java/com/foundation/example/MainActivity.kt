package com.foundation.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.foundation.example.databinding.ActivityMainBinding
import com.foundation.widget.binding.ViewBindingHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val message = ViewBindingHelper.getViewBindingInstance<ActivityMainBinding>(
            V1(),
            layoutInflater,
            null
        )
        message.toString().toast()
    }
}