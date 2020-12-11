package com.konone.review.activity.motionlayout

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.konone.review.activity.R

class LottieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie)

        val lottieAnimView1: LottieAnimationView = findViewById(R.id.lottie_anim_view_1)
        val lottieAnimView2: LottieAnimationView = findViewById(R.id.lottie_anim_view_2)

        val flashLottieView: LottieAnimationView = findViewById(R.id.lottie_flash_view)
        val hdrLottieView: LottieAnimationView = findViewById(R.id.lottie_hdr_view)

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

        var flashOn = false
        flashLottieView.setAnimation("flash_turn_on.json")
        flashLottieView.setOnClickListener {
            if (flashLottieView.isAnimating) {
                flashLottieView.progress = 1f
            }
            flashOn = !flashOn
            Log.i(TAG, "flashLottieView click, flashOn = $flashOn")
            if (flashOn) {
                flashLottieView.setAnimation("flash_turn_on.json")
            } else {
                flashLottieView.setAnimation("flash_turn_off.json")
            }
            flashLottieView.playAnimation()
        }

        var hdrOn = false
        hdrLottieView.setAnimation("hdr_turn_on.json")
        hdrLottieView.setOnClickListener {
            if (hdrLottieView.isAnimating) {
                hdrLottieView.progress = 1f
            }
            hdrOn = !hdrOn
            Log.i(TAG, "hdrLottieView click, hdrOn = $hdrOn")
            if (hdrOn) {
                hdrLottieView.setAnimation("hdr_turn_on.json")
            } else {
                hdrLottieView.setAnimation("hdr_turn_off.json")
            }
            hdrLottieView.playAnimation()
        }
    }

    companion object {
        const val TAG = "LottieActivity"
    }
}