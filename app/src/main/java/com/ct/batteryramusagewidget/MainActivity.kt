package com.ct.batteryramusagewidget

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ct.respurceusageview.CircularLoadProgressView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var i = 0
        val cicView = findViewById<CircularLoadProgressView>(R.id.circle_progress_bar)
        val ha = Handler()
        ha.postDelayed(object : Runnable {
            override fun run() {
                if (i <= 95) {
                    i += 5
                } else if (i >= 1) {
                    i -= 5
                }
                val progress = calculateScaledValue(
                    0,
                    99,
                    i.toLong(),
                    0,
                    360
                )
                cicView.updateProWithProText("$i%", progress)
                ha.postDelayed(this, 1000)

            }
        }, 1000)

    }


    /**
     * Method to calculate the scaled value
     *
     */
    fun calculateScaledValue(
        minValue: Long,
        maxValue: Long,
        currentValue: Long,
        minScaledValue: Int,
        maxScaledValue: Int
    ): Float {
        return ((currentValue - minValue) *
                (maxScaledValue - minScaledValue).toLong() /
                (maxValue - minValue) + 0).toFloat()
    }
}
