package com.konone.review.activity.motionlayout

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.konone.review.activity.R

class LottieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie)

        val lottieAnimView1: LottieAnimationView = findViewById(R.id.lottie_anim_view_1)
        val lottieAnimView2: LottieAnimationView = findViewById(R.id.lottie_anim_view_2)

        lottieAnimView1.setOnClickListener {
            Log.i(TAG, "onClick: lottieAnimView2 isAnimating ? ${lottieAnimView2.isAnimating}")
            if (lottieAnimView2.isAnimating) {
                lottieAnimView2.progress = 1f
            }
            lottieAnimView1.playAnimation()
        }
        lottieAnimView1.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                Log.i(TAG, "lottieView1 onAnimationEnd: ")
            }

            override fun onAnimationStart(animation: Animator?) {
                Log.i(TAG, "lottieView1 onAnimationStart: ")
            }
        })

        lottieAnimView2.setOnClickListener {
            Log.i(TAG, "onClick: lottieAnimView1 isAnimating ? ${lottieAnimView1.isAnimating}")
            if (lottieAnimView1.isAnimating) {
                lottieAnimView1.cancelAnimation()
            }
            lottieAnimView2.playAnimation()
        }

        lottieAnimView2.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                Log.i(TAG, "lottieView2 onAnimationEnd: ")
            }

            override fun onAnimationStart(animation: Animator?) {
                Log.i(TAG, "lottieView2 onAnimationStart: ")
            }
        })
    }

    companion object {
        const val TAG = "LottieActivity"
    }
}