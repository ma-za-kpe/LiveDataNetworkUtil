package com.maku.networkutilexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.maku.networkutil.util.NetworkUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handleNetwork()
    }

    private fun handleNetwork() {
        NetworkUtil.getNetworkLiveData(applicationContext).observe(this, { isConnected ->
            if (!isConnected) {
                Toast.makeText(this, "no internet", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "internet available", Toast.LENGTH_SHORT).show()
            }
        })
    }

}