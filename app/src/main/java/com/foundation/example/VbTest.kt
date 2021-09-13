package com.foundation.example

import androidx.viewbinding.ViewBinding
import com.foundation.example.databinding.ActivityMainBinding

open class VbTest<T : ViewBinding> {
    fun ttt() {
        println(1)
    }
}

class V1 : VbTest<ActivityMainBinding>() {

}