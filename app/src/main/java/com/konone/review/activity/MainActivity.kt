package com.konone.review.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.konone.review.activity.utils.LogUtils

class MainActivity : AppCompatActivity() {

    val TAG = LogUtils.getClassTag(this.localClassName)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}