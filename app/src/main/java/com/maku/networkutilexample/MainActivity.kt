package com.maku.networkutilexample

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.maku.networkutil.util.NetworkUtil
import com.maku.networkutilexample.databinding.ActivityMainBinding
import com.maku.networkutilexample.util.hide
import com.maku.networkutilexample.util.show

class MainActivity : AppCompatActivity() {

    //databinding
    private lateinit var mViewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initialize the binding
        mViewBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        handleNetwork()
    }

    private fun handleNetwork() {
        NetworkUtil.getNetworkLiveData(applicationContext).observe(this, { isConnected ->
            if (!isConnected) {
                //Toast.makeText(this, "no internet", Toast.LENGTH_SHORT).show()
                mViewBinding.textViewNetworkStatus.text = getString(R.string.text_no_connectivity)
                mViewBinding.networkStatusLayout.apply {
                    alpha = 0f
                    show()
                    setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorStatusNotConnected
                        ))
                    animate()
                        .alpha(1f)
                        .setDuration(ANIMATION_DURATION)
                        .setListener(null)
                }
            } else {
                //Toast.makeText(this, "internet available", Toast.LENGTH_SHORT).show()
                mViewBinding.textViewNetworkStatus.text = getString(R.string.text_connectivity)
                mViewBinding.networkStatusLayout.apply {
                    setBackgroundColor( ContextCompat.getColor(
                        context,
                        R.color.colorStatusConnected
                    ))

                    animate()
                        .alpha(0f)
                        .setStartDelay(ANIMATION_DURATION)
                        .setDuration(ANIMATION_DURATION)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                hide()
                            }
                        })
                }
            }
        })
    }

    /**
     * Companion object
     */
    companion object {
        const val ANIMATION_DURATION = 1000.toLong()
    }

}