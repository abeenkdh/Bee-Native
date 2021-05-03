package com.bstudio.beenativeexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import com.bstudio.beenative.BeeNative

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BeeNative.loadNativeAds(this, "ca-app-pub-3940256099942544/2247696110"){
            val frame = findViewById<FrameLayout>(R.id.fl_adplaceholder)
            BeeNative.displayNativeAd(frame)
        }
    }
}